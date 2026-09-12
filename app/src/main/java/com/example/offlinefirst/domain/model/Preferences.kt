package com.example.offlinefirst.domain.model

import java.util.UUID

data class Preferences(
    val id: String = UUID.randomUUID().toString(),
    val key: String,
    val value: String,
    val category: PreferenceCategory = PreferenceCategory.GENERAL,
    val dataType: PreferenceDataType = PreferenceDataType.STRING,
    val isEncrypted: Boolean = false,
    val isGlobal: Boolean = false,
    val userId: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val lastSyncAt: Long? = null,
    val syncStatus: SyncStatus = SyncStatus.PENDING
) {
    init {
        require(key.isNotBlank()) { "Preference key cannot be blank" }
        require(value.isNotBlank()) { "Preference value cannot be blank" }
        require(id.isNotBlank()) { "Preference ID cannot be blank" }
    }

    fun isSynced(): Boolean = syncStatus == SyncStatus.SYNCED

    fun hasPendingChanges(): Boolean = syncStatus == SyncStatus.PENDING || syncStatus == SyncStatus.CONFLICT

    fun needsSync(): Boolean = lastSyncAt == null || updatedAt > (lastSyncAt ?: 0L)

    fun withUpdatedTimestamp(): Preferences = copy(updatedAt = System.currentTimeMillis())

    fun markAsSynced(): Preferences = copy(
        syncStatus = SyncStatus.SYNCED,
        lastSyncAt = System.currentTimeMillis()
    )

    fun markAsPending(): Preferences = copy(syncStatus = SyncStatus.PENDING)

    fun markAsConflict(): Preferences = copy(syncStatus = SyncStatus.CONFLICT)

    fun getTypedValue(): Any? = when (dataType) {
        PreferenceDataType.STRING -> value
        PreferenceDataType.INTEGER -> value.toIntOrNull()
        PreferenceDataType.LONG -> value.toLongOrNull()
        PreferenceDataType.FLOAT -> value.toFloatOrNull()
        PreferenceDataType.DOUBLE -> value.toDoubleOrNull()
        PreferenceDataType.BOOLEAN -> value.toBooleanStrictOrNull()
        PreferenceDataType.JSON -> value
    }

    fun withValue(newValue: String): Preferences = copy(
        value = newValue,
        updatedAt = System.currentTimeMillis(),
        syncStatus = SyncStatus.PENDING
    )

    fun withCategory(category: PreferenceCategory): Preferences = copy(
        category = category,
        updatedAt = System.currentTimeMillis()
    )

    fun withEncryption(encrypted: Boolean): Preferences = copy(
        isEncrypted = encrypted,
        updatedAt = System.currentTimeMillis()
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Preferences) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String = "Preferences(id=$id, key=$key, category=$category)"
}

enum class PreferenceCategory {
    GENERAL,
    NOTIFICATIONS,
    PRIVACY,
    DISPLAY,
    SYNC,
    NETWORK,
    SECURITY,
    THEME;

    fun isValidCategory(): Boolean = entries.contains(this)
}

enum class PreferenceDataType {
    STRING,
    INTEGER,
    LONG,
    FLOAT,
    DOUBLE,
    BOOLEAN,
    JSON;

    fun isNumeric(): Boolean = this in listOf(INTEGER, LONG, FLOAT, DOUBLE)
    fun isBoolean(): Boolean = this == BOOLEAN
    fun isString(): Boolean = this == STRING || this == JSON
}

data class PreferenceGroup(
    val category: PreferenceCategory,
    val preferences: List<Preferences>
) {
    fun hasPendingChanges(): Boolean = preferences.any { it.hasPendingChanges() }
    fun getPendingCount(): Int = preferences.count { it.hasPendingChanges() }
    fun getSyncedCount(): Int = preferences.count { it.isSynced() }
}

sealed class PreferenceValidationResult {
    data object Valid : PreferenceValidationResult()
    data class Invalid(val errors: List<String>) : PreferenceValidationResult()

    fun isValid(): Boolean = this is Valid
    fun getErrors(): List<String> = (this as? Invalid)?.errors ?: emptyList()
}

fun Preferences.validate(): PreferenceValidationResult {
    val errors = mutableListOf<String>()
    if (key.isBlank()) errors.add("Preference key cannot be blank")
    if (value.isBlank()) errors.add("Preference value cannot be blank")
    if (!isGlobal && userId.isNullOrBlank()) {
        errors.add("User-specific preferences must have a user ID")
    }
    return if (errors.isEmpty()) PreferenceValidationResult.Valid
    else PreferenceValidationResult.Invalid(errors)
}

object DefaultPreferences {
    const val KEY_DARK_MODE = "dark_mode"
    const val KEY_NOTIFICATIONS_ENABLED = "notifications_enabled"
    const val KEY_SYNC_WIFI_ONLY = "sync_wifi_only"
    const val KEY_SYNC_INTERVAL = "sync_interval"
    const val KEY_LAST_SYNC = "last_sync_timestamp"
    const val KEY_OFFLINE_MODE = "offline_mode"
    const val KEY_CACHE_SIZE = "cache_size"
    const val KEY_LANGUAGE = "language"

    val defaults = mapOf(
        KEY_DARK_MODE to "false",
        KEY_NOTIFICATIONS_ENABLED to "true",
        KEY_SYNC_WIFI_ONLY to "true",
        KEY_SYNC_INTERVAL to "5",
        KEY_OFFLINE_MODE to "false",
        KEY_CACHE_SIZE to "100",
        KEY_LANGUAGE to "en"
    )
}