package com.example.offlinefirst.data.queue

import android.content.Context
import androidx.room.Room
import com.example.offlinefirst.data.local.database.OfflineFirstDatabase
import com.example.offlinefirst.data.local.entity.SyncOperationEntity
import com.example.offlinefirst.domain.model.SyncOperation
import com.example.offlinefirst.domain.model.SyncOperationType
import com.example.offlinefirst.domain.model.SyncStatus
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OperationQueueManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val database: OfflineFirstDatabase
) {
    private val syncOperationDao = database.syncOperationDao()
    private val mutex = Mutex()

    private val _isProcessing = MutableStateFlow(false)
    val isProcessing: Flow<Boolean> = _isProcessing.asStateFlow()

    private val _queueSize = MutableStateFlow(0)
    val queueSize: Flow<Int> = _queueSize.asStateFlow()

    suspend fun enqueueOperation(operation: SyncOperation): Result<Long> = withContext(Dispatchers.IO) {
        mutex.withLock {
            try {
                val entity = operation.toEntity()
                val id = syncOperationDao.insert(operation)
                updateQueueSize()
                Result.success(id)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun enqueueOperations(operations: List<SyncOperation>): Result<List<Long>> = withContext(Dispatchers.IO) {
        mutex.withLock {
            try {
                val entities = operations.map { it.toEntity() }
                val ids = syncOperationDao.insertAll(operations)
                updateQueueSize()
                Result.success(ids)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getPendingOperations(): List<SyncOperation> = withContext(Dispatchers.IO) {
        syncOperationDao.getPendingOperations()
            .map { it.toDomain() }
    }

    suspend fun getOperationsByEntity(entityId: String): List<SyncOperation> = withContext(Dispatchers.IO) {
        syncOperationDao.getOperationsByEntityId(entityId)
            .map { it.toDomain() }
    }

    suspend fun markAsCompleted(operationId: Long): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            syncOperationDao.updateStatus(operationId, SyncStatus.SYNCED.name)
            updateQueueSize()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun markAsFailed(operationId: Long, errorMessage: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            syncOperationDao.updateStatusWithError(operationId, SyncStatus.ERROR.name, errorMessage)
            updateQueueSize()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun markAsProcessing(operationId: Long): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            syncOperationDao.updateStatus(operationId, SyncStatus.SYNCING.name)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun retryOperation(operationId: Long): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            syncOperationDao.updateStatusWithError(operationId, SyncStatus.PENDING.name, null)
            syncOperationDao.incrementRetryCount(operationId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteOperation(operationId: Long): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            syncOperationDao.deleteById(operationId)
            updateQueueSize()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun clearCompletedOperations(): Result<Int> = withContext(Dispatchers.IO) {
        try {
            val deleted = syncOperationDao.deleteCompleted()
            updateQueueSize()
            Result.success(deleted)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun clearAllOperations(): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            syncOperationDao.deleteAll()
            updateQueueSize()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getOperationById(operationId: Long): SyncOperation? = withContext(Dispatchers.IO) {
        syncOperationDao.getById(operationId)?.toDomain()
    }

    suspend fun hasPendingOperations(): Boolean = withContext(Dispatchers.IO) {
        syncOperationDao.getPendingCount() > 0
    }

    private suspend fun updateQueueSize() {
        val count = syncOperationDao.getPendingCount()
        _queueSize.value = count
    }

    suspend fun processQueue(
        processor: suspend (SyncOperation) -> Result<Unit>
    ): Result<Int> = withContext(Dispatchers.IO) {
        if (_isProcessing.value) {
            return@withContext Result.failure(IllegalStateException("Queue is already being processed"))
        }

        _isProcessing.value = true
        var processedCount = 0

        try {
            val pendingOps = getPendingOperations()
            for (operation in pendingOps) {
                if (operation.retryCount >= MAX_RETRY_COUNT) {
                    continue
                }

                markAsProcessing(operation.id)
                val result = processor(operation)

                if (result.isSuccess) {
                    markAsCompleted(operation.id)
                    processedCount++
                } else {
                    markAsFailed(operation.id, result.exceptionOrNull()?.message ?: "Unknown error")
                }
            }
            Result.success(processedCount)
        } catch (e: Exception) {
            Result.failure(e)
        } finally {
            _isProcessing.value = false
        }
    }

    companion object {
        private const val MAX_RETRY_COUNT = 3
    }
}

private fun SyncOperation.toEntity(): SyncOperationEntity = SyncOperationEntity(
    id = id,
    entityId = entityId,
    entityType = entityType,
    operationType = operationType.name,
    payload = payload,
    status = status.name,
    retryCount = retryCount,
    errorMessage = errorMessage,
    createdAt = createdAt,
    updatedAt = updatedAt
)

private fun SyncOperationEntity.toDomain(): SyncOperation = SyncOperation(
    id = id,
    entityId = entityId,
    entityType = entityType,
    operationType = SyncOperationType.valueOf(operationType),
    payload = payload,
    status = SyncStatus.valueOf(status),
    retryCount = retryCount,
    errorMessage = errorMessage,
    createdAt = createdAt,
    updatedAt = updatedAt
)