package com.example.offlinefirst.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.offlinefirst.data.local.entity.PreferencesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PreferencesDao {

    @Query("SELECT * FROM preferences WHERE id = :preferenceId")
    suspend fun getPreferenceById(preferenceId: String): PreferencesEntity?

    @Query("SELECT * FROM preferences WHERE id = :preferenceId")
    fun observePreferenceById(preferenceId: String): Flow<PreferencesEntity?>

    @Query("SELECT * FROM preferences WHERE `key` = :key LIMIT 1")
    suspend fun getPreferenceByKey(key: String): PreferencesEntity?

    @Query("SELECT * FROM preferences WHERE `key` = :key AND (user_id = :userId OR (user_id IS NULL AND :userId IS NULL)) LIMIT 1")
    suspend fun getPreferenceByKeyAndUser(key: String, userId: String?): PreferencesEntity?

    @Query("SELECT * FROM preferences WHERE category = :category ORDER BY `key` ASC")
    suspend fun getPreferencesByCategory(category: String): List<PreferencesEntity>

    @Query("SELECT * FROM preferences WHERE category = :category")
    fun observePreferencesByCategory(category: String): Flow<List<PreferencesEntity>>

    @Query("SELECT * FROM preferences WHERE user_id = :userId OR (user_id IS NULL AND is_global = 1) ORDER BY `key` ASC")
    suspend fun getPreferencesForUser(userId: String?): List<PreferencesEntity>

    @Query("SELECT * FROM preferences WHERE user_id = :userId OR (user_id IS NULL AND is_global = 1)")
    fun observePreferencesForUser(userId: String?): Flow<List<PreferencesEntity>>

    @Query("SELECT * FROM preferences WHERE sync_status = :status")
    suspend fun getPreferencesBySyncStatus(status: String): List<PreferencesEntity>

    @Query("SELECT * FROM preferences WHERE sync_status IN ('PENDING', 'CONFLICT', 'ERROR')")
    suspend fun getPreferencesWithPendingSync(): List<PreferencesEntity>

    @Query("SELECT * FROM preferences WHERE needs_sync = 1")
    suspend fun getPreferencesNeedingSync(): List<PreferencesEntity>

    @Query("SELECT * FROM preferences ORDER BY updated_at DESC")
    fun observeAllPreferences(): Flow<List<PreferencesEntity>>

    @Query("SELECT * FROM preferences ORDER BY updated_at DESC LIMIT :limit OFFSET :offset")
    suspend fun getPreferencesPaginated(limit: Int, offset: Int): List<PreferencesEntity>

    @Query("SELECT COUNT(*) FROM preferences")
    suspend fun getPreferenceCount(): Int

    @Query("SELECT COUNT(*) FROM preferences WHERE category = :category")
    suspend fun getPreferenceCountByCategory(category: String): Int

    @Query("SELECT COUNT(*) FROM preferences WHERE sync_status = :status")
    suspend fun getPreferenceCountBySyncStatus(status: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreference(preference: PreferencesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreferences(preferences: List<PreferencesEntity>)

    @Update
    suspend fun updatePreference(preference: PreferencesEntity)

    @Delete
    suspend fun deletePreference(preference: PreferencesEntity)

    @Query("DELETE FROM preferences WHERE id = :preferenceId")
    suspend fun deletePreferenceById(preferenceId: String)

    @Query("DELETE FROM preferences WHERE category = :category")
    suspend fun deletePreferencesByCategory(category: String)

    @Query("DELETE FROM preferences WHERE user_id = :userId AND is_global = 0")
    suspend fun deletePreferencesForUser(userId: String)

    @Query("DELETE FROM preferences")
    suspend fun deleteAllPreferences()

    @Query("UPDATE preferences SET sync_status = :status, last_sync_at = :syncTimestamp WHERE id = :preferenceId")
    suspend fun updateSyncStatus(preferenceId: String, status: String, syncTimestamp: Long)

    @Query("UPDATE preferences SET sync_status = 'PENDING', updated_at = :timestamp WHERE id = :preferenceId")
    suspend fun markPreferenceAsPending(preferenceId: String, timestamp: Long)

    @Query("UPDATE preferences SET sync_status = 'ERROR', error_message = :errorMessage WHERE id = :preferenceId")
    suspend fun markPreferenceAsError(preferenceId: String, errorMessage: String)

    @Transaction
    suspend fun upsertPreference(preference: PreferencesEntity) {
        val existing = getPreferenceById(preference.id)
        if (existing != null) {
            updatePreference(preference)
        } else {
            insertPreference(preference)
        }
    }

    @Query("""
        SELECT * FROM preferences 
        WHERE (:query IS NULL OR `key` LIKE '%' || :query || '%' OR value LIKE '%' || :query || '%')
        AND (:category IS NULL OR category = :category)
        AND (:userId IS NULL OR user_id = :userId OR (user_id IS NULL AND is_global = 1))
        ORDER BY 
            CASE WHEN :sortBy = 'key' THEN `key` END ASC,
            CASE WHEN :sortBy = 'category' THEN category END ASC,
            CASE WHEN :sortBy = 'updated_at' THEN updated_at END DESC,
            CASE WHEN :sortBy = 'created_at' THEN created_at END DESC
        LIMIT :limit OFFSET :offset
    """)
    suspend fun searchPreferences(query: String?, category: String?, userId: String?, sortBy: String, limit: Int, offset: Int): List<PreferencesEntity>

    @Query("SELECT DISTINCT category FROM preferences ORDER BY category ASC")
    suspend fun getAllCategories(): List<String>

    @Query("SELECT * FROM preferences WHERE is_encrypted = 1")
    suspend fun getEncryptedPreferences(): List<PreferencesEntity>
}