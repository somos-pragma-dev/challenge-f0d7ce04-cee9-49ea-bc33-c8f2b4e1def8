package com.example.offlinefirst.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.offlinefirst.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: String): UserEntity?

    @Query("SELECT * FROM users WHERE id = :userId")
    fun observeUserById(userId: String): Flow<UserEntity?>

    @Query("SELECT * FROM users WHERE sync_status = :status")
    suspend fun getUsersBySyncStatus(status: String): List<UserEntity>

    @Query("SELECT * FROM users WHERE sync_status IN ('PENDING', 'CONFLICT', 'ERROR')")
    suspend fun getUsersWithPendingSync(): List<UserEntity>

    @Query("SELECT * FROM users WHERE needs_sync = 1")
    suspend fun getUsersNeedingSync(): List<UserEntity>

    @Query("SELECT * FROM users ORDER BY updated_at DESC")
    fun observeAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users ORDER BY updated_at DESC LIMIT :limit OFFSET :offset")
    suspend fun getUsersPaginated(limit: Int, offset: Int): List<UserEntity>

    @Query("SELECT COUNT(*) FROM users")
    suspend fun getUserCount(): Int

    @Query("SELECT COUNT(*) FROM users WHERE sync_status = :status")
    suspend fun getUserCountBySyncStatus(status: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUserById(userId: String)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    @Query("UPDATE users SET sync_status = :status, last_sync_at = :syncTimestamp WHERE id = :userId")
    suspend fun updateSyncStatus(userId: String, status: String, syncTimestamp: Long)

    @Query("UPDATE users SET sync_status = 'PENDING', updated_at = :timestamp WHERE id = :userId")
    suspend fun markUserAsPending(userId: String, timestamp: Long)

    @Query("UPDATE users SET sync_status = 'ERROR', error_message = :errorMessage WHERE id = :userId")
    suspend fun markUserAsError(userId: String, errorMessage: String)

    @Transaction
    suspend fun upsertUser(user: UserEntity) {
        val existing = getUserById(user.id)
        if (existing != null) {
            updateUser(user)
        } else {
            insertUser(user)
        }
    }

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): UserEntity?

    @Query("""
        SELECT * FROM users 
        WHERE (:query IS NULL OR username LIKE '%' || :query || '%' OR email LIKE '%' || :query || '%' OR display_name LIKE '%' || :query || '%')
        ORDER BY 
            CASE WHEN :sortBy = 'username' THEN username END ASC,
            CASE WHEN :sortBy = 'email' THEN email END ASC,
            CASE WHEN :sortBy = 'updated_at' THEN updated_at END DESC,
            CASE WHEN :sortBy = 'created_at' THEN created_at END DESC
        LIMIT :limit OFFSET :offset
    """)
    suspend fun searchUsers(query: String?, sortBy: String, limit: Int, offset: Int): List<UserEntity>
}