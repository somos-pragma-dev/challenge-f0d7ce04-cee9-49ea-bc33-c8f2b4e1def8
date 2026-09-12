package com.example.offlinefirst.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.offlinefirst.data.local.entity.UserPreferencesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserPreferencesDao {
    fun getUserPreferences(): Flow<UserPreferencesEntity?>
    suspend fun getUserPreferencesSync(): UserPreferencesEntity?
    suspend fun getPreferences(): UserPreferencesEntity?
    suspend fun insertPreferences(preferences: UserPreferencesEntity)
    suspend fun insertOrUpdatePreferences(preferences: UserPreferencesEntity)
    suspend fun updatePreferences(preferences: UserPreferencesEntity)
    suspend fun updateSyncStatus(syncStatus: String)
    suspend fun markAsSynced(timestamp: Long)
    suspend fun deletePreferences()
    suspend fun clearAllPreferences()
    suspend fun getSyncStatus(): String?
    suspend fun getLastSyncTimestamp(): Long?
    suspend fun updateThemeMode(themeMode: String, updatedAt: Long)
    suspend fun updateNotificationsEnabled(enabled: Boolean, updatedAt: Long)
    suspend fun updateAutoSyncEnabled(enabled: Boolean, updatedAt: Long)
    suspend fun updateSyncOnWifiOnly(wifiOnly: Boolean, updatedAt: Long)
    suspend fun updateDefaultPaymentMethod(paymentMethod: String, updatedAt: Long)
    suspend fun updateLanguage(language: String, updatedAt: Long)
}