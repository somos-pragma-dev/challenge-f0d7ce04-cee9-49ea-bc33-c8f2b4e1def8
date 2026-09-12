package com.example.offlinefirst.domain.repository

import com.example.offlinefirst.data.repository.UserRepositoryImpl
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.domain.model.UserValidationResult
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<List<User>>
    
    fun getUserById(userId: String): Flow<Resource<User>>
    
    fun getUserByEmail(email: String): Flow<Resource<User>>
    
    fun getUserByUsername(username: String): Flow<Resource<User>>
    
    suspend fun saveUser(user: User): Resource<User>
    
    suspend fun saveUsers(users: List<User>): Resource<List<User>>
    
    suspend fun updateUser(user: User): Resource<User>
    
    suspend fun deleteUser(userId: String): Resource<Unit>
    
    suspend fun deleteAllUsers(): Resource<Unit>
    
    fun getPendingSyncUsers(): Flow<List<User>>
    
    fun getSyncedUsers(): Flow<List<User>>
    
    fun getUsersWithConflicts(): Flow<List<User>>
    
    suspend fun markUserAsSynced(userId: String): Resource<User>
    
    suspend fun markUserAsPending(userId: String): Resource<User>
    
    suspend fun markUserAsConflict(userId: String): Resource<User>
    
    suspend fun syncUser(userId: String): Resource<User>
    
    suspend fun syncAllPendingUsers(): Resource<List<User>>
    
    suspend fun resolveConflict(userId: String, resolution: ConflictResolution): Resource<User>
    
    fun searchUsers(query: String): Flow<List<User>>
    
    fun getUserCount(): Flow<Int>
    
    suspend fun validateUser(email: String?, username: String?, displayName: String?): UserValidationResult
    
    suspend fun getUserByCredentials(email: String, password: String): Resource<User>
    
    suspend fun updateUserProfile(userId: String, displayName: String?, phone: String?, avatarUrl: String?): Resource<User>
    
    suspend fun verifyUserEmail(userId: String, verified: Boolean): Resource<User>
    
    suspend fun verifyUserPhone(userId: String, verified: Boolean): Resource<User>
    
    suspend fun refreshUserFromRemote(userId: String): Resource<User>
    
    fun observeUserSyncStatus(userId: String): Flow<SyncStatus>
    
    suspend fun getLastSyncTime(userId: String): Long?
    
    suspend fun updateLastSyncTime(userId: String, timestamp: Long)
}

sealed class ConflictResolution {
    data class UseLocal(val user: User) : ConflictResolution()
    data class UseRemote(val remoteUser: User) : ConflictResolution()
    data class Merge(val localUser: User, val remoteUser: User) : ConflictResolution()
    data object Discard : ConflictResolution()
}

interface UserCacheManager {
    suspend fun cacheUser(user: User)
    
    suspend fun cacheUsers(users: List<User>)
    
    suspend fun getCachedUser(userId: String): User?
    
    suspend fun getCachedUsers(): List<User>
    
    suspend fun invalidateCache()
    
    suspend fun invalidateUser(userId: String)
    
    fun isCacheValid(): Boolean
    
    suspend fun getCacheSize(): Long
}

interface UserRemoteDataSource {
    suspend fun fetchUser(userId: String): Resource<User>
    
    suspend fun fetchAllUsers(): Resource<List<User>>
    
    suspend fun fetchUserByEmail(email: String): Resource<User>
    
    suspend fun createUser(user: User): Resource<User>
    
    suspend fun updateUser(user: User): Resource<User>
    
    suspend fun deleteUser(userId: String): Resource<Unit>
    
    suspend fun searchUsers(query: String): Resource<List<User>>
    
    suspend fun authenticateUser(email: String, password: String): Resource<User>
    
    suspend fun verifyEmail(userId: String): Resource<Boolean>
    
    suspend fun verifyPhone(userId: String): Resource<Boolean>
}

object UserRepositoryFactory {
    fun create(localDataSource: UserLocalDataSource, remoteDataSource: UserRemoteDataSource): UserRepository {
        return UserRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }
}

interface UserLocalDataSource {
    fun getAllUsers(): Flow<List<User>>
    
    fun getUserById(userId: String): Flow<User?>
    
    fun getUserByEmail(email: String): Flow<User?>
    
    fun getUserByUsername(username: String): Flow<User?>
    
    suspend fun insertUser(user: User)
    
    suspend fun insertUsers(users: List<User>)
    
    suspend fun updateUser(user: User)
    
    suspend fun deleteUser(userId: String)
    
    suspend fun deleteAllUsers()
    
    fun getPendingSyncUsers(): Flow<List<User>>
    
    fun getSyncedUsers(): Flow<List<User>>
    
    fun getUsersWithConflicts(): Flow<List<User>>
    
    suspend fun updateSyncStatus(userId: String, status: SyncStatus)
    
    fun searchUsers(query: String): Flow<List<User>>
    
    fun getUserCount(): Flow<Int>
}