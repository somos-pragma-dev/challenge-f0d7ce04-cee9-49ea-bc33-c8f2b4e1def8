package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.offlinefirst.domain.model.PreferenceCategory
import com.example.offlinefirst.domain.model.PreferenceDataType
import com.example.offlinefirst.domain.model.Preferences
import com.example.offlinefirst.domain.model.SyncStatus
import java.util.UUID

@Entity(
    tableName = "preferences",
    indices = [
        Index(value = ["key"], unique = true),
        Index(value = ["user_id"]),
        Index(value = ["category"]),
        Index(value = ["sync_status"]),
        Index(value = ["updated_at"])
    ]
)
data class PreferencesEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "user_id")
    val userId: String?,

    @ColumnInfo(name = "key")
    val key: String,

    @ColumnInfo(name = "value")
    val value: String,

    @ColumnInfo(name = "category")
    val category: String = PreferenceCategory.GENERAL.name,

    @ColumnInfo(name = "data_type")
    val dataType: String = PreferenceDataType.STRING.name,

    @ColumnInfo(name = "is_global")
    val isGlobal: Boolean = true,

    @ColumnInfo(name = "is_encrypted")
    val isEncrypted: Boolean = false,

    @ColumnInfo(name = "sync_status")
    val syncStatus: String = SyncStatus.PENDING.name,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "last_sync_at")
    val lastSyncAt: Long? = null,

    @ColumnInfo(name = "version")
    val version: Int = 1,

    @ColumnInfo(name = "is_deleted")
    val isDeleted: Boolean = false,

    @ColumnInfo(name = "remote_id")
    val remoteId: String? = null,

    @ColumnInfo(name = "etag")
    val etag: String? = null,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "default_value")
    val defaultValue: String? = null,

    @ColumnInfo(name = "is_sensitive")
    val isSensitive: Boolean = false
) {
    fun toDomain(): Preferences {
        return Preferences(
            id = id,
            userId = userId,
            key = key,
            value = value,
            category = PreferenceCategory.valueOf(category),
            dataType = PreferenceDataType.valueOf(dataType),
            isGlobal = isGlobal,
            isEncrypted = isEncrypted,
            syncStatus = SyncStatus.valueOf(syncStatus),
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt,
            version = version,
            isDeleted = isDeleted,
            remoteId = remoteId,
            etag = etag,
            description = description,
            defaultValue = defaultValue,
            isSensitive = isSensitive
        )
    }

    fun isSynced(): Boolean = syncStatus == SyncStatus.SYNCED.name

    fun hasPendingChanges(): Boolean = 
        syncStatus == SyncStatus.PENDING.name || syncStatus == SyncStatus.CONFLICT.name

    fun needsSync(): Boolean = 
        lastSyncAt == null || updatedAt > (lastSyncAt ?: 0L)

    fun markAsSynced(newEtag: String? = null): PreferencesEntity {
        return copy(
            syncStatus = SyncStatus.SYNCED.name,
            lastSyncAt = System.currentTimeMillis(),
            etag = newEtag ?: etag,
            version = version + 1
        )
    }

    fun markAsPending(): PreferencesEntity {
        return copy(
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun markAsSyncing(): PreferencesEntity {
        return copy(syncStatus = SyncStatus.SYNCING.name)
    }

    fun markAsConflict(): PreferencesEntity {
        return copy(
            syncStatus = SyncStatus.CONFLICT.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun markAsError(): PreferencesEntity {
        return copy(
            syncStatus = SyncStatus.ERROR.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun softDelete(): PreferencesEntity {
        return copy(
            isDeleted = true,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withValue(newValue: String): PreferencesEntity {
        return copy(
            value = newValue,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withCategory(category: PreferenceCategory): PreferencesEntity {
        return copy(
            category = category.name,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withEncryption(encrypted: Boolean): PreferencesEntity {
        return copy(
            isEncrypted = encrypted,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun getTypedValue(): Any? {
        return when (PreferenceDataType.valueOf(dataType)) {
            PreferenceDataType.STRING -> value
            PreferenceDataType.INTEGER -> value.toIntOrNull()
            PreferenceDataType.LONG -> value.toLongOrNull()
            PreferenceDataType.FLOAT -> value.toFloatOrNull()
            PreferenceDataType.DOUBLE -> value.toDoubleOrNull()
            PreferenceDataType.BOOLEAN -> value.toBooleanStrictOrNull()
            PreferenceDataType.JSON -> value
            PreferenceDataType.BYTE_ARRAY -> value.toByteArray()
        }
    }

    fun isValidValue(): Boolean {
        return try {
            getTypedValue() != null || value.isEmpty()
        } catch (e: Exception) {
            false
        }
    }

    fun canSync(): Boolean = 
        syncStatus == SyncStatus.PENDING.name || syncStatus == SyncStatus.ERROR.name

    fun getDataTypeEnum(): PreferenceDataType = PreferenceDataType.valueOf(dataType)

    fun getCategoryEnum(): PreferenceCategory = PreferenceCategory.valueOf(category)

    fun isNumeric(): Boolean = getDataTypeEnum().isNumeric()

    fun isBoolean(): Boolean = getDataTypeEnum().isBoolean()

    fun isString(): Boolean = getDataTypeEnum().isString()

    companion object {
        fun fromDomain(preferences: Preferences): PreferencesEntity {
            return PreferencesEntity(
                id = preferences.id,
                userId = preferences.userId,
                key = preferences.key,
                value = preferences.value,
                category = preferences.category.name,
                dataType = preferences.dataType.name,
                isGlobal = preferences.isGlobal,
                isEncrypted = preferences.isEncrypted,
                syncStatus = preferences.syncStatus.name,
                createdAt = preferences.createdAt,
                updatedAt = preferences.updatedAt,
                lastSyncAt = preferences.lastSyncAt,
                version = preferences.version,
                isDeleted = preferences.isDeleted,
                remoteId = preferences.remoteId,
                etag = preferences.etag,
                description = preferences.description,
                defaultValue = preferences.defaultValue,
                isSensitive = preferences.isSensitive
            )
        }

        fun createGlobal(
            key: String,
            value: String,
            category: PreferenceCategory = PreferenceCategory.GENERAL,
            dataType: PreferenceDataType = PreferenceDataType.STRING
        ): PreferencesEntity {
            require(key.isNotBlank()) { "Preference key cannot be blank" }
            
            return PreferencesEntity(
                key = key,
                value = value,
                category = category.name,
                dataType = dataType.name,
                isGlobal = true,
                userId = null,
                syncStatus = SyncStatus.PENDING.name
            )
        }

        fun createUserSpecific(
            userId: String,
            key: String,
            value: String,
            category: PreferenceCategory = PreferenceCategory.USER,
            dataType: PreferenceDataType = PreferenceDataType.STRING
        ): PreferencesEntity {
            require(key.isNotBlank()) { "Preference key cannot be blank" }
            require(userId.isNotBlank()) { "User ID cannot be blank for user-specific preference" }
            
            return PreferencesEntity(
                userId = userId,
                key = key,
                value = value,
                category = category.name,
                dataType = dataType.name,
                isGlobal = false,
                syncStatus = SyncStatus.PENDING.name
            )
        }
    }
}