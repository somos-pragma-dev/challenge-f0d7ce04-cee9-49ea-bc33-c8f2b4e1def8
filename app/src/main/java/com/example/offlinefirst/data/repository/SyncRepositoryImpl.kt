package com.example.offlinefirst.data.repository

import com.example.offlinefirst.data.local.dao.SyncOperationDao
import com.example.offlinefirst.data.local.entity.SyncOperationEntity
import com.example.offlinefirst.domain.model.SyncOperation
import com.example.offlinefirst.domain.model.SyncOperationStatus
import com.example.offlinefirst.domain.model.SyncOperationType
import com.example.offlinefirst.domain.repository.SyncRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncRepositoryImpl @Inject constructor(
    private val syncOperationDao: SyncOperationDao
) : SyncRepository {

    override fun getOperationById(id: String): Flow<Resource<SyncOperation>> = flow {
        emit(Resource.Loading())
        try {
            val entity = syncOperationDao.getOperationById(id)
            if (entity != null) {
                emit(Resource.Success(entity.toDomain()))
            } else {
                emit(Resource.Error("Sync operation not found with id: $id"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getAllOperations(): Flow<Resource<List<SyncOperation>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = syncOperationDao.getAllOperations()
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPendingOperations(): Flow<Resource<List<SyncOperation>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = syncOperationDao.getPendingOperations()
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getOperationsByType(type: SyncOperationType): Flow<Resource<List<SyncOperation>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = syncOperationDao.getOperationsByType(type.name)
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getOperationsByStatus(status: SyncOperationStatus): Flow<Resource<List<SyncOperation>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = syncOperationDao.getOperationsByStatus(status.name)
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getFailedOperations(): Flow<Resource<List<SyncOperation>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = syncOperationDao.getFailedOperations()
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun saveOperation(operation: SyncOperation): Flow<Resource<SyncOperation>> = flow {
        emit(Resource.Loading())
        try {
            val entity = operation.toEntity()
            syncOperationDao.insertOrUpdate(entity)
            
            val savedEntity = syncOperationDao.getOperationById(operation.id)
            if (savedEntity != null) {
                emit(Resource.Success(savedEntity.toDomain()))
            } else {
                emit(Resource.Error("Failed to save sync operation"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun saveOperations(operations: List<SyncOperation>): Flow<Resource<List<SyncOperation>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = operations.map { it.toEntity() }
            syncOperationDao.insertAll(entities)
            
            val ids = operations.map { it.id }
            val savedEntities = syncOperationDao.getOperationsByIds(ids)
            emit(Resource.Success(savedEntities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun updateOperationStatus(
        id: String,
        status: SyncOperationStatus,
        errorMessage: String?
    ): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            syncOperationDao.updateStatus(id, status.name, errorMessage, System.currentTimeMillis())
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsCompleted(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val timestamp = System.currentTimeMillis()
            syncOperationDao.updateStatus(
                id,
                SyncOperationStatus.COMPLETED.name,
                null,
                timestamp
            )
            syncOperationDao.updateCompletedAt(id, timestamp)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsFailed(id: String, errorMessage: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            syncOperationDao.updateStatus(
                id,
                SyncOperationStatus.FAILED.name,
                errorMessage,
                System.currentTimeMillis()
            )
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsInProgress(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            syncOperationDao.updateStatus(
                id,
                SyncOperationStatus.IN_PROGRESS.name,
                null,
                System.currentTimeMillis()
            )
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun deleteOperation(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            syncOperationDao.deleteById(id)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun deleteCompletedOperations(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = syncOperationDao.deleteCompletedOperations()
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun deleteOldOperations(olderThanTimestamp: Long): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = syncOperationDao.deleteOldOperations(olderThanTimestamp)
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getOperationCount(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = syncOperationDao.getOperationCount()
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPendingCount(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = syncOperationDao.getPendingCount()
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getFailedCount(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = syncOperationDao.getFailedCount()
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun retryFailedOperation(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            syncOperationDao.updateStatus(
                id,
                SyncOperationStatus.PENDING.name,
                null,
                System.currentTimeMillis()
            )
            syncOperationDao.incrementRetryCount(id)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun retryAllFailedOperations(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = syncOperationDao.retryAllFailed()
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getOperationsByEntityId(entityId: String): Flow<Resource<List<SyncOperation>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = syncOperationDao.getOperationsByEntityId(entityId)
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    private fun SyncOperationEntity.toDomain(): SyncOperation {
        return SyncOperation(
            id = id,
            entityId = entityId,
            entityType = entityType,
            operationType = SyncOperationType.valueOf(operationType),
            payload = payload,
            status = SyncOperationStatus.valueOf(status),
            errorMessage = errorMessage,
            retryCount = retryCount,
            maxRetries = maxRetries,
            createdAt = createdAt,
            updatedAt = updatedAt,
            startedAt = startedAt,
            completedAt = completedAt
        )
    }

    private fun SyncOperation.toEntity(): SyncOperationEntity {
        return SyncOperationEntity(
            id = id,
            entityId = entityId,
            entityType = entityType,
            operationType = operationType.name,
            payload = payload,
            status = status.name,
            errorMessage = errorMessage,
            retryCount = retryCount,
            maxRetries = maxRetries,
            createdAt = createdAt,
            updatedAt = updatedAt,
            startedAt = startedAt,
            completedAt = completedAt
        )
    }
}