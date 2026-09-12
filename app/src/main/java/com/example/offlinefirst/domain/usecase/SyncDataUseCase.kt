package com.example.offlinefirst.domain.usecase

import com.example.offlinefirst.domain.model.OperationType
import com.example.offlinefirst.domain.model.SyncOperation
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.repository.SyncRepository
import com.example.offlinefirst.domain.repository.UserRepository
import com.example.offlinefirst.domain.repository.PreferencesRepository
import com.example.offlinefirst.domain.repository.ContentRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class SyncDataUseCase @Inject constructor(
    private val syncRepository: SyncRepository,
    private val userRepository: UserRepository,
    private val preferencesRepository: PreferencesRepository,
    private val contentRepository: ContentRepository
) {
    suspend operator fun invoke(): Resource<SyncRepository.SyncOperationResult> {
        return syncRepository.executeSync()
    }
    
    suspend fun syncUserData(): Resource<Int> {
        return try {
            val pendingUsers = userRepository.getUsersBySyncStatus(SyncStatus.PENDING)
                .first()
            
            var syncedCount = 0
            
            for (user in pendingUsers) {
                try {
                    val result = userRepository.syncUserToRemote(user)
                    if (result is Resource.Success) {
                        val syncedUser = user.markAsSynced()
                            .copy(lastSyncAt = System.currentTimeMillis())
                        userRepository.saveUser(syncedUser)
                        syncedCount++
                    } else {
                        val failedUser = user.copy(
                            syncStatus = SyncStatus.ERROR,
                            errorMessage = (result as? Resource.Error)?.message
                        )
                        userRepository.saveUser(failedUser)
                    }
                } catch (e: Exception) {
                    val failedUser = user.copy(
                        syncStatus = SyncStatus.ERROR,
                        errorMessage = e.message
                    )
                    userRepository.saveUser(failedUser)
                }
            }
            
            Resource.Success(syncedCount)
        } catch (e: Exception) {
            Resource.Error("Error al sincronizar usuarios: ${e.message}")
        }
    }
    
    suspend fun syncPreferences(): Resource<Int> {
        return try {
            val pendingPrefs = preferencesRepository.getPreferencesBySyncStatus(SyncStatus.PENDING)
                .first()
            
            var syncedCount = 0
            
            for (pref in pendingPrefs) {
                try {
                    val result = preferencesRepository.syncPreferenceToRemote(pref)
                    if (result is Resource.Success) {
                        val syncedPref = pref.markAsSynced()
                            .copy(lastSyncAt = System.currentTimeMillis())
                        preferencesRepository.savePreference(syncedPref)
                        syncedCount++
                    }
                } catch (e: Exception) {
                    // Log error but continue with next preference
                }
            }
            
            Resource.Success(syncedCount)
        } catch (e: Exception) {
            Resource.Error("Error al sincronizar preferencias: ${e.message}")
        }
    }
    
    suspend fun syncContent(): Resource<Int> {
        return try {
            val pendingContent = contentRepository.getContentBySyncStatus(SyncStatus.PENDING)
                .first()
            
            var syncedCount = 0
            
            for (content in pendingContent) {
                try {
                    val result = contentRepository.syncContentToRemote(content)
                    if (result is Resource.Success) {
                        val syncedContent = content.markAsSynced()
                            .copy(lastSyncAt = System.currentTimeMillis())
                        contentRepository.saveContent(syncedContent)
                        syncedCount++
                    }
                } catch (e: Exception) {
                    // Log error but continue
                }
            }
            
            Resource.Success(syncedCount)
        } catch (e: Exception) {
            Resource.Error("Error al sincronizar contenido: ${e.message}")
        }
    }
    
    suspend fun getPendingOperationsCount(): Resource<Int> {
        return syncRepository.getPendingCount()
    }
    
    fun observeSyncStatus(): Flow<SyncStatus> {
        return syncRepository.observeSyncStatus()
    }
    
    fun observePendingOperations(): Flow<List<SyncOperation>> {
        return syncRepository.getPendingOperations()
    }
    
    suspend fun retryFailedOperations(): Resource<List<SyncOperation>> {
        return syncRepository.retryFailedOperations()
    }
    
    suspend fun processOperation(operation: SyncOperation): Resource<Unit> {
        return try {
            when (operation.entityType) {
                "USER" -> processUserOperation(operation)
                "PREFERENCES" -> processPreferencesOperation(operation)
                "CONTENT" -> processContentOperation(operation)
                else -> Resource.Error("Tipo de operación desconocido: ${operation.entityType}")
            }
        } catch (e: Exception) {
            syncRepository.markOperationAsFailed(operation.id, e.message ?: "Error desconocido")
            Resource.Error("Error al procesar operación: ${e.message}")
        }
    }
    
    private suspend fun processUserOperation(operation: SyncOperation): Resource<Unit> {
        val user = userRepository.getUserById(operation.entityId).firstOrNull()
            ?: return Resource.Error("Usuario no encontrado")
        
        return when (operation.operationType) {
            OperationType.CREATE, OperationType.UPDATE -> {
                val result = userRepository.syncUserToRemote(user)
                if (result is Resource.Success) {
                    syncRepository.markOperationAsCompleted(operation.id)
                    Resource.Success(Unit)
                } else {
                    syncRepository.markOperationAsFailed(
                        operation.id, 
                        (result as? Resource.Error)?.message ?: "Error de sincronización"
                    )
                    Resource.Error("Error al sincronizar usuario")
                }
            }
            OperationType.DELETE -> {
                val result = userRepository.deleteUserFromRemote(operation.entityId)
                if (result is Resource.Success) {
                    syncRepository.markOperationAsCompleted(operation.id)
                    Resource.Success(Unit)
                } else {
                    Resource.Error("Error al eliminar usuario remoto")
                }
            }
        }
    }
    
    private suspend fun processPreferencesOperation(operation: SyncOperation): Resource<Unit> {
        val pref = preferencesRepository.getPreferenceById(operation.entityId).firstOrNull()
            ?: return Resource.Error("Preferencia no encontrada")
        
        return when (operation.operationType) {
            OperationType.CREATE, OperationType.UPDATE -> {
                val result = preferencesRepository.syncPreferenceToRemote(pref)
                if (result is Resource.Success) {
                    syncRepository.markOperationAsCompleted(operation.id)
                    Resource.Success(Unit)
                } else {
                    Resource.Error("Error al sincronizar preferencia")
                }
            }
            OperationType.DELETE -> {
                syncRepository.markOperationAsCompleted(operation.id)
                Resource.Success(Unit)
            }
        }
    }
    
    private suspend fun processContentOperation(operation: SyncOperation): Resource<Unit> {
        val content = contentRepository.getContentById(operation.entityId).firstOrNull()
            ?: return Resource.Error("Contenido no encontrado")
        
        return when (operation.operationType) {
            OperationType.CREATE, OperationType.UPDATE -> {
                val result = contentRepository.syncContentToRemote(content)
                if (result is Resource.Success) {
                    syncRepository.markOperationAsCompleted(operation.id)
                    Resource.Success(Unit)
                } else {
                    Resource.Error("Error al sincronizar contenido")
                }
            }
            OperationType.DELETE -> {
                syncRepository.markOperationAsCompleted(operation.id)
                Resource.Success(Unit)
            }
        }
    }
}