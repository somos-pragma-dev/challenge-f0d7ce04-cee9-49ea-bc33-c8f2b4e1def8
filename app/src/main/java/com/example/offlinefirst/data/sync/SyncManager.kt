package com.example.offlinefirst.data.sync

import com.example.offlinefirst.data.local.dao.ContentDao
import com.example.offlinefirst.data.local.dao.PreferencesDao
import com.example.offlinefirst.data.local.dao.SyncOperationDao
import com.example.offlinefirst.data.local.dao.UserDao
import com.example.offlinefirst.data.queue.OperationQueueManager
import com.example.offlinefirst.data.remote.ApiClient
import com.example.offlinefirst.data.remote.ApiService
import com.example.offlinefirst.data.repository.ContentRepositoryImpl
import com.example.offlinefirst.data.repository.PreferencesRepositoryImpl
import com.example.offlinefirst.data.repository.SyncRepositoryImpl
import com.example.offlinefirst.data.repository.UserRepositoryImpl
import com.example.offlinefirst.domain.model.SyncOperation
import com.example.offlinefirst.domain.model.SyncOperationType
import com.example.offlinefirst.domain.model.SyncResult
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.domain.repository.SyncRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncManager @Inject constructor(
    @ApplicationContext private val context: android.content.Context,
    private val apiClient: ApiClient,
    private val networkMonitor: NetworkMonitor,
    private val operationQueueManager: OperationQueueManager,
    private val conflictResolver: ConflictResolver,
    private val userRepository: UserRepositoryImpl,
    private val preferencesRepository: PreferencesRepositoryImpl,
    private val contentRepository: ContentRepositoryImpl,
    private val syncRepository: SyncRepositoryImpl,
    private val userDao: UserDao,
    private val preferencesDao: PreferencesDao,
    private val contentDao: ContentDao,
    private val syncOperationDao: SyncOperationDao
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _syncState = MutableStateFlow<SyncState>(SyncState.Idle)
    val syncState: StateFlow<SyncState> = _syncState.asStateFlow()

    private val _lastSyncTime = MutableStateFlow<Long?>(null)
    val lastSyncTime: StateFlow<Long?> = _lastSyncTime.asStateFlow()

    private val _pendingChangesCount = MutableStateFlow(0)
    val pendingChangesCount: StateFlow<Int> = _pendingChangesCount.asStateFlow()

    init {
        observeNetworkAndSync()
    }

    private fun observeNetworkAndSync() {
        scope.launch {
            networkMonitor.isOnline.collect { isOnline ->
                if (isOnline && hasPendingChanges()) {
                    triggerSync()
                }
            }
        }
    }

    suspend fun triggerSync(): Result<SyncResult> {
        if (_syncState.value is SyncState.Syncing) {
            return Result.failure(IllegalStateException("Sync already in progress"))
        }

        if (!networkMonitor.isOnline.value) {
            return Result.failure(IllegalStateException("No network connection"))
        }

        _syncState.value = SyncState.Syncing
        val startTime = System.currentTimeMillis()

        return try {
            val result = performSync()
            val duration = System.currentTimeMillis() - startTime

            _lastSyncTime.value = System.currentTimeMillis()
            _syncState.value = if (result.isSuccess) {
                SyncState.Completed(result.getOrNull()!!, duration)
            } else {
                SyncState.Failed(result.exceptionOrNull()!!)
            }

            result
        } catch (e: Exception) {
            _syncState.value = SyncState.Failed(e)
            Result.failure(e)
        }
    }

    private suspend fun performSync(): Result<SyncResult> = withContext(Dispatchers.IO) {
        var uploadedCount = 0
        var downloadedCount = 0
        var conflictsResolved = 0
        var failedCount = 0

        try {
            val pendingOperations = operationQueueManager.getPendingOperations()

            for (operation in pendingOperations) {
                val result = processOperation(operation)
                if (result.isSuccess) {
                    uploadedCount++
                } else if (result.exceptionOrNull() is ConflictException) {
                    val conflictResult = conflictResolver.resolveConflict(operation)
                    if (conflictResult.isSuccess) {
                        conflictsResolved++
                        uploadedCount++
                    } else {
                        failedCount++
                    }
                } else {
                    failedCount++
                }
            }

            val remoteUsers = fetchRemoteUsers()
            for (remoteUser in remoteUsers) {
                val localUser = userRepository.getUserById(remoteUser.id)
                if (localUser != null) {
                    val merged = conflictResolver.resolveUserConflict(localUser, remoteUser)
                    userRepository.saveUser(merged)
                } else {
                    userRepository.saveUser(remoteUser)
                }
                downloadedCount++
            }

            val syncRecord = SyncResult(
                uploadedCount = uploadedCount,
                downloadedCount = downloadedCount,
                conflictsResolved = conflictsResolved,
                failedCount = failedCount,
                timestamp = System.currentTimeMillis()
            )

            syncRepository.recordSync(syncRecord)
            Result.success(syncRecord)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun processOperation(operation: SyncOperation): Result<Unit> {
        return when (operation.operationType) {
            SyncOperationType.CREATE -> processCreate(operation)
            SyncOperationType.UPDATE -> processUpdate(operation)
            SyncOperationType.DELETE -> processDelete(operation)
        }
    }

    private suspend fun processCreate(operation: SyncOperation): Result<Unit> {
        return try {
            when (operation.entityType) {
                "User" -> {
                    val user = operation.deserializePayload<User>()
                    apiClient.apiService.createUser(user)
                }
                "Preferences" -> {
                    val prefs = operation.deserializePayload<com.example.offlinefirst.domain.model.Preferences>()
                    apiClient.apiService.savePreferences(prefs)
                }
                "DownloadedContent" -> {
                    val content = operation.deserializePayload<com.example.offlinefirst.domain.model.DownloadedContent>()
                    apiClient.apiService.syncContent(content)
                }
            }
            Result.success(Unit)
        } catch (e: Exception) {
            if (e is ConflictException) Result.failure(e)
            else Result.failure(e)
        }
    }

    private suspend fun processUpdate(operation: SyncOperation): Result<Unit> {
        return try {
            when (operation.entityType) {
                "User" -> {
                    val user = operation.deserializePayload<User>()
                    val response = apiClient.apiService.updateUser(user.id, user)
                    if (response.version != user.version) {
                        throw ConflictException("Version conflict for user ${user.id}")
                    }
                }
                "Preferences" -> {
                    val prefs = operation.deserializePayload<com.example.offlinefirst.domain.model.Preferences>()
                    apiClient.apiService.updatePreferences(prefs.id, prefs)
                }
                "DownloadedContent" -> {
                    val content = operation.deserializePayload<com.example.offlinefirst.domain.model.DownloadedContent>()
                    apiClient.apiService.updateContent(content.id, content)
                }
            }
            Result.success(Unit)
        } catch (e: Exception) {
            if (e is ConflictException) Result.failure(e)
            else Result.failure(e)
        }
    }

    private suspend fun processDelete(operation: SyncOperation): Result<Unit> {
        return try {
            when (operation.entityType) {
                "User" -> apiClient.apiService.deleteUser(operation.entityId)
                "Preferences" -> apiClient.apiService.deletePreferences(operation.entityId)
                "DownloadedContent" -> apiClient.apiService.deleteContent(operation.entityId)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun fetchRemoteUsers(): List<User> {
        return try {
            val response = apiClient.apiService.getUsers()
            response
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun hasPendingChanges(): Boolean {
        return operationQueueManager.hasPendingOperations()
    }

    suspend fun queueOperation(operation: SyncOperation): Result<Long> {
        _pendingChangesCount.value++
        return operationQueueManager.enqueueOperation(operation)
    }

    suspend fun getSyncStatus(): Flow<SyncState> = syncState

    suspend fun cancelSync() {
        _syncState.value = SyncState.Idle
    }
}

sealed class SyncState {
    data object Idle : SyncState()
    data class Syncing(val progress: Float = 0f) : SyncState()
    data class Completed(val result: SyncResult, val duration: Long) : SyncState()
    data class Failed(val error: Throwable) : SyncState()
}

class ConflictException(message: String) : Exception(message)

private inline fun <reified T> SyncOperation.deserializePayload(): T {
    val moshi = com.squareup.moshi.Moshi.Builder().build()
    val adapter = moshi.adapter(T::class.java)
    return adapter.fromJson(payload)!!
}