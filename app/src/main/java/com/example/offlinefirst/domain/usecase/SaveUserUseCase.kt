package com.example.offlinefirst.domain.usecase

import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.domain.model.UserValidationResult
import com.example.offlinefirst.domain.repository.SyncRepository
import com.example.offlinefirst.domain.repository.UserRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val syncRepository: SyncRepository
) {
    suspend operator fun invoke(user: User): Resource<User> {
        val validationResult = validateUser(user)
        if (!validationResult.isValid()) {
            return Resource.Error(
                "Validación fallida: ${validationResult.getErrors().joinToString(", ")}"
            )
        }
        
        return try {
            val existingUser = userRepository.getUserById(user.id).first()
            
            val userToSave = if (existingUser != null) {
                val hasLocalChanges = existingUser.hasPendingChanges() && 
                    existingUser.updatedAt > (existingUser.lastSyncAt ?: 0L)
                
                if (hasLocalChanges) {
                    val mergedUser = resolveConflict(existingUser, user)
                    mergedUser.markAsPending()
                } else {
                    user.withUpdatedTimestamp().markAsPending()
                }
            } else {
                user.withUpdatedTimestamp().markAsPending()
            }
            
            userRepository.saveUser(userToSave)
            
            queueSyncOperation(userToSave)
            
            Resource.Success(userToSave)
        } catch (e: Exception) {
            Resource.Error("Error al guardar usuario: ${e.message}")
        }
    }
    
    private fun validateUser(user: User): UserValidationResult {
        val errors = mutableListOf<String>()
        
        if (user.id.isBlank()) {
            errors.add("El ID del usuario no puede estar vacío")
        }
        
        if (user.username.isBlank()) {
            errors.add("El nombre de usuario no puede estar vacío")
        }
        
        if (user.email.isBlank()) {
            errors.add("El correo electrónico no puede estar vacío")
        }
        
        if (!user.email.contains("@")) {
            errors.add("El correo electrónico debe ser válido")
        }
        
        return if (errors.isEmpty()) {
            UserValidationResult.Valid
        } else {
            UserValidationResult.Invalid(errors)
        }
    }
    
    private fun resolveConflict(local: User, remote: User): User {
        return if (local.updatedAt > (remote.updatedAt ?: 0L)) {
            local
        } else {
            remote
        }
    }
    
    private suspend fun queueSyncOperation(user: User) {
        val operation = com.example.offlinefirst.domain.model.SyncOperation(
            id = "sync_user_${user.id}_${System.currentTimeMillis()}",
            entityId = user.id,
            entityType = "USER",
            operationType = com.example.offlinefirst.domain.model.OperationType.UPDATE,
            payload = mapOf(
                "username" to user.username,
                "email" to user.email,
                "displayName" to user.displayName,
                "phone" to (user.phone ?: ""),
                "isEmailVerified" to user.isEmailVerified.toString(),
                "isPhoneVerified" to user.isPhoneVerified.toString()
            ),
            syncStatus = SyncStatus.PENDING,
            retryCount = 0,
            maxRetries = 3,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis(),
            lastSyncAt = null,
            errorMessage = null
        )
        
        syncRepository.queueOperation(operation)
    }
    
    suspend fun saveUserOffline(user: User): Resource<User> {
        return try {
            val offlineUser = user.markAsPending()
            userRepository.saveUser(offlineUser)
            Resource.Success(offlineUser)
        } catch (e: Exception) {
            Resource.Error("Error al guardar offline: ${e.message}")
        }
    }
    
    fun observeUserSave(userId: String): Flow<Resource<User>> {
        return kotlinx.coroutines.flow.flow {
            emit(Resource.Loading())
            userRepository.getUserById(userId).collect { user ->
                if (user != null) {
                    emit(Resource.Success(user))
                }
            }
        }
    }
}