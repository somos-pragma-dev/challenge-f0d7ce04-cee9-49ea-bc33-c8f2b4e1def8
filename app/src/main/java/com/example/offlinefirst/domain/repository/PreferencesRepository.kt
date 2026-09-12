package com.example.offlinefirst.domain.repository

import com.example.offlinefirst.data.repository.PreferencesRepositoryImpl
import com.example.offlinefirst.domain.model.PreferenceCategory
import com.example.offlinefirst.domain.model.PreferenceValidationResult
import com.example.offlinefirst.domain.model.Preferences
import com.example.offlinefirst.domain.model.PreferenceGroup
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    fun getAllPreferences(): Flow<List<Preferences>>
    
    fun getPreferenceById(preferenceId: String): Flow<Resource<Preferences>>
    
    fun getPreferenceByKey(key: String, userId: String? = null): Flow<Resource<Preferences>>
    
    fun getPreferencesByCategory(category: PreferenceCategory): Flow<List<Preferences>>
    
    fun getPreferencesByUser(userId: String): Flow<List<Preferences>>
    
    fun getGlobalPreferences(): Flow<List<Preferences>>
    
    suspend fun savePreference(preference: Preferences): Resource<Preferences>
    
    suspend fun savePreferences(preferences: List<Preferences>): Resource<List<Preferences>>
    
    suspend fun updatePreference(preference: Preferences): Resource<Preferences>
    
    suspend fun deletePreference(preferenceId: String): Resource<Unit>
    
    suspend fun deleteAllPreferences(): Resource<Unit>
    
    suspend fun deleteUserPreferences(userId: String): Resource<Unit>
    
    fun getPendingSyncPreferences(): Flow<List<Preferences>>
    
    fun getSyncedPreferences(): Flow<List<Preferences>>
    
    fun getPreferencesWithConflicts(): Flow<List<Preferences>>
    
    suspend fun markPreferenceAsSynced(preferenceId: String): Resource<Preferences>
    
    suspend fun markPreferenceAsPending(preferenceId: String): Resource<Preferences>
    
    suspend fun markPreferenceAsConflict(preferenceId: String): Resource<Preferences>
    
    suspend fun syncPreference(preferenceId: String): Resource<Preferences>
    
    suspend fun syncAllPendingPreferences(): Resource<List<Preferences>>
    
    suspend fun resolvePreferenceConflict(preferenceId: String, resolution: PreferenceConflictResolution): Resource<Preferences>
    
    suspend fun getPreferenceValue(key: String, userId: String? = null): Any?
    
    suspend fun setPreferenceValue(key: String, value: String, userId: String? = null, category: PreferenceCategory = PreferenceCategory.GENERAL): Resource<Preferences>
    
    fun getPreferenceGroups(): Flow<List<PreferenceGroup>>
    
    suspend fun validatePreference(key: String, value: String, isGlobal: Boolean = true, userId: String? = null): PreferenceValidationResult
    
    suspend fun importPreferences(preferences: List<Preferences>): Resource<List<Preferences>>
    
    suspend fun exportPreferences(userId: String? = null): Resource<List<Preferences>>
    
    fun observePreferenceSyncStatus(preferenceId: String): Flow<SyncStatus>
    
    suspend fun getLastSyncTime(preferenceId: String): Long?
    
    suspend fun updateLastSyncTime(preferenceId: String, timestamp: Long)
    
    suspend fun resetToDefaults(userId: String? = null): Resource<Unit>
}

sealed class PreferenceConflictResolution {
    data class UseLocal(val preference: Preferences) : PreferenceConflictResolution()
    data class UseRemote(val remotePreference: Preferences) : PreferenceConflictResolution()
    data class Merge(val localPreference: Preferences, val remotePreference: Preferences) : PreferenceConflictResolution()
    data object Discard : PreferenceConflictResolution()
}

interface PreferencesCacheManager {
    suspend fun cachePreference(preference: Preferences)
    
    suspend fun cachePreferences(preferences: List<Preferences>)
    
    suspend fun getCachedPreference(preferenceId: String): Preferences?
    
    suspend fun getCachedPreferenceByKey(key: String, userId: String? = null): Preferences?
    
    suspend fun getCachedPreferences(): List<Preferences>
    
    suspend fun invalidateCache()
    
    suspend fun invalidatePreference(preferenceId: String)
    
    suspend fun invalidateUserPreferences(userId: String)
    
    fun isCacheValid(): Boolean
    
    suspend fun getCacheSize(): Long
}

interface PreferencesRemoteDataSource {
    suspend fun fetchPreference(preferenceId: String): Resource<Preferences>
    
    suspend fun fetchAllPreferences(userId: String? = null): Resource<List<Preferences>>
    
    suspend fun fetchPreferencesByCategory(category: PreferenceCategory, userId: String? = null): Resource<List<Preferences>>
    
    suspend fun createPreference(preference: Preferences): Resource<Preferences>
    
    suspend fun updatePreference(preference: Preferences): Resource<Preferences>
    
    suspend fun deletePreference(preferenceId: String): Resource<Unit>
    
    suspend fun syncPreferences(preferences: List<Preferences>): Resource<List<Preferences>>
}

object PreferencesRepositoryFactory {
    fun create(localDataSource: PreferencesLocalDataSource, remoteDataSource: PreferencesRemoteDataSource): PreferencesRepository {
        return PreferencesRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }
}

interface PreferencesLocalDataSource {
    fun getAllPreferences(): Flow<List<Preferences>>
    
    fun getPreferenceById(preferenceId: String): Flow<Preferences?>
    
    fun getPreferenceByKey(key: String, userId: String?): Flow<Preferences?>
    
    fun getPreferencesByCategory(category: PreferenceCategory): Flow<List<Preferences>>
    
    fun getPreferencesByUser(userId: String): Flow<List<Preferences>>
    
    fun getGlobalPreferences(): Flow<List<Preferences>>
    
    suspend fun insertPreference(preference: Preferences)
    
    suspend fun insertPreferences(preferences: List<Preferences>)
    
    suspend fun updatePreference(preference: Preferences)
    
    suspend fun deletePreference(preferenceId: String)
    
    suspend fun deleteAllPreferences()
    
    suspend fun deleteUserPreferences(userId: String)
    
    fun getPendingSyncPreferences(): Flow<List<Preferences>>
    
    fun getSyncedPreferences(): Flow<List<Preferences>>
    
    fun getPreferencesWithConflicts(): Flow<List<Preferences>>
    
    suspend fun updateSyncStatus(preferenceId: String, status: SyncStatus)
    
    fun getPreferenceGroups(): Flow<List<PreferenceGroup>>
}