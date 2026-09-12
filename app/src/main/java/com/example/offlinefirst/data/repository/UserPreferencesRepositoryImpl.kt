package com.example.offlinefirst.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.offlinefirst.data.local.dao.UserPreferencesDao
import com.example.offlinefirst.data.local.entity.UserPreferencesEntity
import com.example.offlinefirst.domain.model.UserPreferences
import com.example.offlinefirst.domain.repository.UserPreferencesRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val userPreferencesDao: UserPreferencesDao
) : UserPreferencesRepository {

    companion object {
        private val KEY_USER_ID = stringPreferencesKey("user_id")
        private val KEY_USER_NAME = stringPreferencesKey("user_name")
        private val KEY_USER_EMAIL = stringPreferencesKey("user_email")
        private val KEY_NOTIFICATIONS_ENABLED = booleanPreferencesKey("notifications_enabled")
        private val KEY_DARK_MODE = booleanPreferencesKey("dark_mode")
        private val KEY_AUTO_SYNC = booleanPreferencesKey("auto_sync")
        private val KEY_SYNC_ON_WIFI_ONLY = booleanPreferencesKey("sync_on_wifi_only")
        private val KEY_LAST_SYNC_TIME = stringPreferencesKey("last_sync_time")
        private val KEY_SELECTED_CURRENCY = stringPreferencesKey("selected_currency")
        private val KEY_SELECTED_LANGUAGE = stringPreferencesKey("selected_language")
    }

    override fun getUserPreferences(): Flow<Resource<UserPreferences>> = flow {
        emit(Resource.Loading())
        
        try {
            val preferences = dataStore.data.first()
            val userPreferences = UserPreferences(
                userId = preferences[KEY_USER_ID] ?: "",
                userName = preferences[KEY_USER_NAME] ?: "",
                userEmail = preferences[KEY_USER_EMAIL] ?: "",
                notificationsEnabled = preferences[KEY_NOTIFICATIONS_ENABLED] ?: true,
                darkModeEnabled = preferences[KEY_DARK_MODE] ?: false,
                autoSyncEnabled = preferences[KEY_AUTO_SYNC] ?: true,
                syncOnWifiOnly = preferences[KEY_SYNC_ON_WIFI_ONLY] ?: false,
                lastSyncTime = preferences[KEY_LAST_SYNC_TIME]?.toLongOrNull() ?: 0L,
                selectedCurrency = preferences[KEY_SELECTED_CURRENCY] ?: "USD",
                selectedLanguage = preferences[KEY_SELECTED_LANGUAGE] ?: "es"
            )
            
            emit(Resource.Success(userPreferences))
        } catch (e: Exception) {
            emit(Resource.Error("Error al cargar preferencias: ${e.localizedMessage}"))
        }
    }

    override suspend fun saveUserPreferences(preferences: UserPreferences): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                preferences.userId.let { prefs[KEY_USER_ID] = it }
                preferences.userName.let { prefs[KEY_USER_NAME] = it }
                preferences.userEmail.let { prefs[KEY_USER_EMAIL] = it }
                preferences.notificationsEnabled.let { prefs[KEY_NOTIFICATIONS_ENABLED] = it }
                preferences.darkModeEnabled.let { prefs[KEY_DARK_MODE] = it }
                preferences.autoSyncEnabled.let { prefs[KEY_AUTO_SYNC] = it }
                preferences.syncOnWifiOnly.let { prefs[KEY_SYNC_ON_WIFI_ONLY] = it }
                preferences.lastSyncTime.toString().let { prefs[KEY_LAST_SYNC_TIME] = it }
                preferences.selectedCurrency.let { prefs[KEY_SELECTED_CURRENCY] = it }
                preferences.selectedLanguage.let { prefs[KEY_SELECTED_LANGUAGE] = it }
            }
            
            userPreferencesDao.insertOrUpdatePreferences(preferences.toEntity())
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al guardar preferencias: ${e.localizedMessage}")
        }
    }

    override suspend fun updateDarkMode(enabled: Boolean): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_DARK_MODE] = enabled
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar modo oscuro: ${e.localizedMessage}")
        }
    }

    override suspend fun updateNotifications(enabled: Boolean): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_NOTIFICATIONS_ENABLED] = enabled
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar notificaciones: ${e.localizedMessage}")
        }
    }

    override suspend fun updateAutoSync(enabled: Boolean): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_AUTO_SYNC] = enabled
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar sincronización automática: ${e.localizedMessage}")
        }
    }

    override suspend fun updateSyncOnWifiOnly(enabled: Boolean): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_SYNC_ON_WIFI_ONLY] = enabled
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar sincronización WiFi: ${e.localizedMessage}")
        }
    }

    override suspend fun updateLastSyncTime(timestamp: Long): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_LAST_SYNC_TIME] = timestamp.toString()
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar tiempo de sincronización: ${e.localizedMessage}")
        }
    }

    override suspend fun updateCurrency(currency: String): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_SELECTED_CURRENCY] = currency
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar moneda: ${e.localizedMessage}")
        }
    }

    override suspend fun updateLanguage(language: String): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_SELECTED_LANGUAGE] = language
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar idioma: ${e.localizedMessage}")
        }
    }

    override fun observeDarkMode(): Flow<Boolean> {
        return dataStore.data.map { prefs ->
            prefs[KEY_DARK_MODE] ?: false
        }
    }

    override fun observeAutoSync(): Flow<Boolean> {
        return dataStore.data.map { prefs ->
            prefs[KEY_AUTO_SYNC] ?: true
        }
    }

    override suspend fun getCachedPreferences(): UserPreferences? {
        return try {
            val cached = userPreferencesDao.getPreferences()
            cached?.toDomain()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun clearPreferences(): Resource<Unit> {
        return try {
            dataStore.edit { it.clear() }
            userPreferencesDao.clearAllPreferences()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al limpiar preferencias: ${e.localizedMessage}")
        }
    }

    private fun UserPreferences.toEntity(): UserPreferencesEntity {
        return UserPreferencesEntity(
            userId = userId,
            userName = userName,
            userEmail = userEmail,
            notificationsEnabled = notificationsEnabled,
            darkModeEnabled = darkModeEnabled,
            autoSyncEnabled = autoSyncEnabled,
            syncOnWifiOnly = syncOnWifiOnly,
            lastSyncTime = lastSyncTime,
            selectedCurrency = selectedCurrency,
            selectedLanguage = selectedLanguage
        )
    }

    private fun UserPreferencesEntity.toDomain(): UserPreferences {
        return UserPreferences(
            userId = userId,
            userName = userName,
            userEmail = userEmail,
            notificationsEnabled = notificationsEnabled,
            darkModeEnabled = darkModeEnabled,
            autoSyncEnabled = autoSyncEnabled,
            syncOnWifiOnly = syncOnWifiOnly,
            lastSyncTime = lastSyncTime,
            selectedCurrency = selectedCurrency,
            selectedLanguage = selectedLanguage
        )
    }
}