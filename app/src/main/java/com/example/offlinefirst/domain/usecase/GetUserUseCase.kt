package com.example.offlinefirst.domain.usecase

import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.domain.repository.UserRepository
import com.example.offlinefirst.util.Resource
import com.example.offlinefirst.util.Resource.Loading
import com.example.offlinefirst.util.Resource.Success
import com.example.offlinefirst.util.Resource.Error
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(userId: String): Flow<Resource<User>> = flow {
        emit(Loading())
        
        try {
            val cachedUser = userRepository.getUserById(userId).first()
            
            if (cachedUser != null) {
                emit(Success(cachedUser))
                
                if (cachedUser.needsSync()) {
                    try {
                        val remoteUser = userRepository.refreshUserFromRemote(userId)
                        remoteUser?.let { remote ->
                            val mergedUser = mergeUserData(cachedUser, remote)
                            userRepository.saveUser(mergedUser)
                            emit(Success(mergedUser))
                        }
                    } catch (e: Exception) {
                        emit(Success(cachedUser))
                    }
                }
            } else {
                emit(Loading())
                
                try {
                    val remoteUser = userRepository.refreshUserFromRemote(userId)
                    if (remoteUser != null) {
                        userRepository.saveUser(remoteUser)
                        emit(Success(remoteUser))
                    } else {
                        emit(Error("Usuario no encontrado"))
                    }
                } catch (e: Exception) {
                    emit(Error("Error al obtener usuario: ${e.message}"))
                }
            }
        } catch (e: Exception) {
            emit(Error("Error al leer usuario: ${e.message}"))
        }
    }
    
    private fun mergeUserData(cached: User, remote: User): User {
        return when {
            cached.syncStatus == SyncStatus.SYNCED -> remote
            remote.lastSyncAt != null && cached.lastSyncAt != null && 
                remote.lastSyncAt > cached.lastSyncAt -> remote
            cached.hasPendingChanges() -> cached.withUpdatedTimestamp()
            else -> remote
        }
    }
    
    fun getCachedUser(userId: String): Flow<User?> {
        return userRepository.getUserById(userId)
    }
    
    suspend fun getUserSync(userId: String): Resource<User> {
        return try {
            val user = userRepository.getUserById(userId).first()
            if (user != null) {
                Success(user)
            } else {
                Error("Usuario no encontrado en caché")
            }
        } catch (e: Exception) {
            Error("Error al obtener usuario: ${e.message}")
        }
    }
    
    fun observeUser(userId: String): Flow<Resource<User>> {
        return userRepository.getUserById(userId).let { userFlow ->
            flow {
                userFlow.collect { user ->
                    if (user != null) {
                        emit(Success(user))
                    } else {
                        emit(Loading())
                    }
                }
            }
        }
    }
}