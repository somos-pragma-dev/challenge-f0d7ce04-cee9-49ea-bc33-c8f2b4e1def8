package com.example.offlinefirst.data.repository

import com.example.offlinefirst.data.local.dao.UserDao
import com.example.offlinefirst.data.local.entity.UserEntity
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.domain.model.UserValidationResult
import com.example.offlinefirst.domain.model.Valid
import com.example.offlinefirst.domain.model.Invalid
import com.example.offlinefirst.domain.repository.UserRepository
import com.example.offlinefirst.util.Error
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUser(userId: String): User? {
        return try {
            val entity = userDao.getUserById(userId)
            entity?.toDomain()
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting user: ${e.message}", e)
            null
        }
    }

    override fun getUserFlow(userId: String): Flow<User?> {
        return userDao.observeUserById(userId).map { it?.toDomain() }
    }

    override fun getCurrentUser(): Flow<User?> {
        return userDao.observeAllUsers().map { entities ->
            entities.firstOrNull()?.toDomain()
        }
    }

    override suspend fun saveUser(user: User): Result<User> {
        return try {
            val validation = user.validate()
            if (!validation.isValid()) {
                return Result.failure(IllegalArgumentException(validation.getErrors().joinToString(", ")))
            }
            val entity = user.toEntity()
            userDao.insertUser(entity)
            Result.success(user)
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error saving user: ${e.message}", e)
            Result.failure(e)
        }
    }

    override suspend fun updateUser(user: User): Result<User> {
        return try {
            val existingEntity = userDao.getUserById(user.id)
            if (existingEntity == null) {
                return Result.failure(IllegalArgumentException("User not found: ${user.id}"))
            }
            val updatedUser = user.withUpdatedTimestamp()
            val validation = updatedUser.validate()
            if (!validation.isValid()) {
                return Result.failure(IllegalArgumentException(validation.getErrors().joinToString(", ")))
            }
            val entity = updatedUser.toEntity()
            userDao.updateUser(entity)
            Result.success(updatedUser)
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error updating user: ${e.message}", e)
            Result.failure(e)
        }
    }

    override suspend fun deleteUser(userId: String): Result<Unit> {
        return try {
            userDao.deleteUserById(userId)
            Result.success(Unit)
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error deleting user: ${e.message}", e)
            Result.failure(e)
        }
    }

    override suspend fun getAllUsers(): List<User> {
        return try {
            userDao.observeAllUsers().first().map { it.toDomain() }
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting all users: ${e.message}", e)
            emptyList()
        }
    }

    override fun getAllUsersFlow(): Flow<List<User>> {
        return userDao.observeAllUsers().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getUsersBySyncStatus(syncStatus: SyncStatus): List<User> {
        return try {
            userDao.getUsersBySyncStatus(syncStatus.name).first().map { it.toDomain() }
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting users by sync status: ${e.message}", e)
            emptyList()
        }
    }

    override fun getUsersBySyncStatusFlow(syncStatus: SyncStatus): Flow<List<User>> {
        return userDao.getUsersBySyncStatus(syncStatus.name).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getPendingSyncUsers(): List<User> {
        return try {
            userDao.getUsersWithPendingSync().first().map { it.toDomain() }
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting pending sync users: ${e.message}", e)
            emptyList()
        }
    }

    override suspend fun markAsSynced(userId: String): Result<Unit> {
        return try {
            val currentTime = System.currentTimeMillis()
            userDao.updateSyncStatus(userId, SyncStatus.SYNCED.name, currentTime)
            Result.success(Unit)
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error marking user as synced: ${e.message}", e)
            Result.failure(e)
        }
    }

    override suspend fun markAsPending(userId: String): Result<Unit> {
        return try {
            val currentTime = System.currentTimeMillis()
            userDao.updateSyncStatus(userId, SyncStatus.PENDING.name, currentTime)
            Result.success(Unit)
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error marking user as pending: ${e.message}", e)
            Result.failure(e)
        }
    }

    override suspend fun markAsConflict(userId: String): Result<Unit> {
        return try {
            val currentTime = System.currentTimeMillis()
            userDao.updateSyncStatus(userId, SyncStatus.CONFLICT.name, currentTime)
            Result.success(Unit)
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error marking user as conflict: ${e.message}", e)
            Result.failure(e)
        }
    }

    override suspend fun getUserCount(): Int {
        return try {
            userDao.getUserCount()
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting user count: ${e.message}", e)
            0
        }
    }

    override suspend fun searchUsers(query: String): List<User> {
        return try {
            userDao.searchUsers(query, "username", 50, 0).first().map { it.toDomain() }
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error searching users: ${e.message}", e)
            emptyList()
        }
    }

    override fun searchUsersFlow(query: String): Flow<List<User>> {
        return userDao.searchUsers(query, "username", 50, 0).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getUserByEmail(email: String): User? {
        return try {
            userDao.getUserByEmail(email)?.toDomain()
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting user by email: ${e.message}", e)
            null
        }
    }

    override suspend fun getUserByUsername(username: String): User? {
        return try {
            userDao.getUserByUsername(username)?.toDomain()
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error getting user by username: ${e.message}", e)
            null
        }
    }

    private fun UserEntity.toDomain(): User {
        return User(
            id = id,
            username = username,
            email = email,
            displayName = displayName,
            phone = phone,
            avatarUrl = avatarUrl,
            isEmailVerified = isEmailVerified,
            isPhoneVerified = isPhoneVerified,
            syncStatus = SyncStatus.valueOf(syncStatus),
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt
        )
    }

    private fun User.toEntity(): UserEntity {
        return UserEntity(
            id = id,
            username = username,
            email = email,
            displayName = displayName,
            phone = phone,
            avatarUrl = avatarUrl,
            isEmailVerified = isEmailVerified,
            isPhoneVerified = isPhoneVerified,
            syncStatus = syncStatus.name,
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt
        )
    }

    private fun User.validate(): UserValidationResult {
        val errors = mutableListOf<String>()
        if (username.isBlank()) errors.add("Username cannot be blank")
        if (email.isBlank()) errors.add("Email cannot be blank")
        if (!email.contains("@")) errors.add("Email must be valid")
        if (displayName.isBlank()) errors.add("Display name cannot be blank")
        return if (errors.isEmpty()) {
            Valid
        } else {
            Invalid(errors)
        }
    }
}