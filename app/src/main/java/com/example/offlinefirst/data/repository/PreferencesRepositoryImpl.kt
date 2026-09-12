package com.example.offlinefirst.data.repository

import com.example.offlinefirst.data.local.dao.PreferencesDao
import com.example.offlinefirst.data.local.entity.PreferencesEntity
import com.example.offlinefirst.domain.model.PreferenceCategory
import com.example.offlinefirst.domain.model.PreferenceDataType
import com.example.offlinefirst.domain.model.Preferences
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.Success
import com.example.offlinefirst.domain.repository.PreferencesRepository
import com.example.offlinefirst.util.Error
import com.example.offlinefirst.util.Loading
import com.example.offlinefirst.data.sync.Failed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesRepositoryImpl @Inject constructor(
    private val preferencesDao: PreferencesDao
) : PreferencesRepository {

    override fun getPreferenceById(id: String): Flow<Resource<Preferences>> = flow {
        emit(Loading())
        try {
            val entity = preferencesDao.getPreferenceById(id)
            if (entity != null) {
                emit(Success(entity.toDomain()))
            } else {
                emit(Error("Preference not found with id: $id"))
            }
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPreferenceByKey(key: String, userId: String?): Flow<Resource<Preferences>> = flow {
        emit(Loading())
        try {
            val entity = preferencesDao.getPreferenceByKeyAndUser(key, userId)
            if (entity != null) {
                emit(Success(entity.toDomain()))
            } else {
                emit(Error("Preference not found with key: $key"))
            }
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getAllPreferences(userId: String?): Flow<Resource<List<Preferences>>> = flow {
        emit(Loading())
        try {
            val entities = preferencesDao.getPreferencesForUser(userId)
            emit(Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPreferencesByCategory(
        category: PreferenceCategory,
        userId: String?
    ): Flow<Resource<List<Preferences>>> = flow {
        emit(Loading())
        try {
            val entities = preferencesDao.getPreferencesByCategory(category.name)
            emit(Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPendingPreferences(): Flow<Resource<List<Preferences>>> = flow {
        emit(Loading())
        try {
            val entities = preferencesDao.getPreferencesWithPendingSync()
            emit(Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun savePreference(preferences: Preferences): Flow<Resource<Preferences>> = flow {
        emit(Loading())
        try {
            val validation = preferences.validate()
            if (!validation.isValid()) {
                emit(Error(validation.getErrors().joinToString(", ")))
                return@flow
            }
            
            val entity = preferences.toEntity()
            preferencesDao.upsertPreference(entity)
            
            val savedEntity = preferencesDao.getPreferenceById(preferences.id)
            if (savedEntity != null) {
                emit(Success(savedEntity.toDomain()))
            } else {
                emit(Error("Failed to save preference"))
            }
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun savePreferences(preferencesList: List<Preferences>): Flow<Resource<List<Preferences>>> = flow {
        emit(Loading())
        try {
            val entities = preferencesList.map { it.toEntity() }
            preferencesDao.insertPreferences(entities)
            
            val ids = preferencesList.map { it.id }
            val savedEntities = ids.mapNotNull { preferencesDao.getPreferenceById(it) }
            emit(Success(savedEntities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun deletePreference(id: String): Flow<Resource<Boolean>> = flow {
        emit(Loading())
        try {
            preferencesDao.deletePreferenceById(id)
            emit(Success(true))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun deleteAllPreferences(userId: String?): Flow<Resource<Boolean>> = flow {
        emit(Loading())
        try {
            if (userId != null) {
                preferencesDao.deletePreferencesForUser(userId)
            } else {
                preferencesDao.deleteAllPreferences()
            }
            emit(Success(true))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsSynced(id: String, syncTimestamp: Long): Flow<Resource<Boolean>> = flow {
        emit(Loading())
        try {
            preferencesDao.updateSyncStatus(id, SyncStatus.SYNCED.name, syncTimestamp)
            emit(Success(true))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsPending(id: String): Flow<Resource<Boolean>> = flow {
        emit(Loading())
        try {
            val timestamp = System.currentTimeMillis()
            preferencesDao.updateSyncStatus(id, SyncStatus.PENDING.name, timestamp)
            emit(Success(true))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsConflict(id: String): Flow<Resource<Boolean>> = flow {
        emit(Loading())
        try {
            preferencesDao.updateSyncStatus(id, SyncStatus.CONFLICT.name, 0L)
            emit(Success(true))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPreferencesCount(userId: String?): Flow<Resource<Int>> = flow {
        emit(Loading())
        try {
            val count = preferencesDao.getPreferenceCount()
            emit(Success(count))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPendingCount(): Flow<Resource<Int>> = flow {
        emit(Loading())
        try {
            val count = preferencesDao.getPreferenceCountBySyncStatus("PENDING")
            emit(Success(count))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun searchPreferences(query: String, userId: String?): Flow<Resource<List<Preferences>>> = flow {
        emit(Loading())
        try {
            val entities = preferencesDao.searchPreferences(query, null, userId, "key", 50, 0)
            emit(Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Error(e.message ?: "Unknown error occurred"))
        }
    }

    private fun PreferencesEntity.toDomain(): Preferences {
        return Preferences(
            id = id,
            key = key,
            value = value,
            dataType = PreferenceDataType.valueOf(dataType),
            category = PreferenceCategory.valueOf(category),
            userId = userId,
            isGlobal = isGlobal,
            isEncrypted = isEncrypted,
            syncStatus = SyncStatus.valueOf(syncStatus),
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt
        )
    }

    private fun Preferences.toEntity(): PreferencesEntity {
        return PreferencesEntity(
            id = id,
            key = key,
            value = value,
            dataType = dataType.name,
            category = category.name,
            userId = userId,
            isGlobal = isGlobal,
            isEncrypted = isEncrypted,
            syncStatus = syncStatus.name,
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt
        )
    }

    private fun Preferences.validate(): PreferenceValidationResult {
        val errors = mutableListOf<String>()
        if (key.isBlank()) errors.add("Preference key cannot be blank")
        if (value.isBlank()) errors.add("Preference value cannot be blank")
        if (!isGlobal && userId.isNullOrBlank()) errors.add("User ID required for non-global preferences")
        return if (errors.isEmpty()) {
            PreferenceValidationResult.Valid
        } else {
            PreferenceValidationResult.Invalid(errors)
        }
    }
}