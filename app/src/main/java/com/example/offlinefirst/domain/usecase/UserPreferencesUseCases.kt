package com.example.offlinefirst.domain.usecase

import com.example.offlinefirst.domain.model.UserPreferences
import com.example.offlinefirst.domain.repository.UserPreferencesRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserPreferencesUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) {
    operator fun invoke(): Flow<Resource<UserPreferences>> = flow {
        emit(Resource.Loading())
        try {
            val preferences = userPreferencesRepository.getUserPreferences().first()
            emit(Resource.Success(preferences))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al obtener preferencias", e))
        }
    }

    fun observePreferences(): Flow<UserPreferences> = userPreferencesRepository.getUserPreferences()
}

class UpdateUserPreferencesUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) {
    suspend operator fun invoke(preferences: UserPreferences): Resource<UserPreferences> {
        return try {
            val updated = userPreferencesRepository.savePreferences(preferences)
            Resource.Success(updated)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al guardar preferencias", e)
        }
    }

    suspend fun updateTheme(theme: String): Resource<UserPreferences> {
        return try {
            val current = userPreferencesRepository.getUserPreferences().first()
            val updated = current.copy(theme = theme)
            val saved = userPreferencesRepository.savePreferences(updated)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar tema", e)
        }
    }

    suspend fun updateLanguage(language: String): Resource<UserPreferences> {
        return try {
            val current = userPreferencesRepository.getUserPreferences().first()
            val updated = current.copy(language = language)
            val saved = userPreferencesRepository.savePreferences(updated)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar idioma", e)
        }
    }

    suspend fun updateNotificationsEnabled(enabled: Boolean): Resource<UserPreferences> {
        return try {
            val current = userPreferencesRepository.getUserPreferences().first()
            val updated = current.copy(notificationsEnabled = enabled)
            val saved = userPreferencesRepository.savePreferences(updated)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar notificaciones", e)
        }
    }

    suspend fun updateAutoSync(enabled: Boolean): Resource<UserPreferences> {
        return try {
            val current = userPreferencesRepository.getUserPreferences().first()
            val updated = current.copy(autoSyncEnabled = enabled)
            val saved = userPreferencesRepository.savePreferences(updated)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar sincronización automática", e)
        }
    }

    suspend fun updateSyncInterval(intervalMinutes: Int): Resource<UserPreferences> {
        return try {
            if (intervalMinutes < 15 || intervalMinutes > 1440) {
                return Resource.Error("Intervalo de sincronización inválido")
            }
            val current = userPreferencesRepository.getUserPreferences().first()
            val updated = current.copy(syncIntervalMinutes = intervalMinutes)
            val saved = userPreferencesRepository.savePreferences(updated)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar intervalo", e)
        }
    }

    suspend fun updateDataSavingMode(enabled: Boolean): Resource<UserPreferences> {
        return try {
            val current = userPreferencesRepository.getUserPreferences().first()
            val updated = current.copy(dataSavingMode = enabled)
            val saved = userPreferencesRepository.savePreferences(updated)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar modo ahorro de datos", e)
        }
    }
}

class ResetUserPreferencesUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) {
    suspend operator fun invoke(): Resource<UserPreferences> {
        return try {
            val defaultPreferences = UserPreferences()
            val saved = userPreferencesRepository.savePreferences(defaultPreferences)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al restaurar preferencias", e)
        }
    }
}