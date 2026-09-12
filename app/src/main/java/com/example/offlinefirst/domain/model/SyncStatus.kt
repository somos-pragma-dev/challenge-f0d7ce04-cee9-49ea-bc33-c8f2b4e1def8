package com.example.offlinefirst.domain.model

import com.example.offlinefirst.util.Error
import java.util.UUID

data class SyncStatus(
    val id: String = UUID.randomUUID().toString(),
    val entityType: EntityType,
    val entityId: String,
    val status: SyncState = SyncState.PENDING,
    val localTimestamp: Long = System.currentTimeMillis(),
    val remoteTimestamp: Long? = null,
    val errorMessage: String? = null,
    val retryCount: Int = 0,
    val lastAttemptTimestamp: Long? = null,
    val conflictData: ConflictData? = null
) {
    fun isPending(): Boolean = status == SyncState.PENDING
    fun isSyncing(): Boolean = status == SyncState.SYNCING
    fun isSynced(): Boolean = status == SyncState.SYNCED
    fun isFailed(): Boolean = status == SyncState.FAILED
    fun hasConflict(): Boolean = conflictData != null
    fun canRetry(): Boolean = retryCount < MAX_RETRY_COUNT && (status == SyncState.FAILED || status == SyncState.PENDING)

    fun markAsSyncing(): SyncStatus {
        return copy(
            status = SyncState.SYNCING,
            lastAttemptTimestamp = System.currentTimeMillis()
        )
    }

    fun markAsSynced(remoteTimestamp: Long = System.currentTimeMillis()): SyncStatus {
        return copy(
            status = SyncState.SYNCED,
            remoteTimestamp = remoteTimestamp,
            errorMessage = null,
            retryCount = 0
        )
    }

    fun markAsFailed(error: String): SyncStatus {
        return copy(
            status = SyncState.FAILED,
            errorMessage = error,
            retryCount = retryCount + 1,
            lastAttemptTimestamp = System.currentTimeMillis()
        )
    }

    fun markAsPending(): SyncStatus {
        return copy(status = SyncState.PENDING, errorMessage = null)
    }

    fun withConflict(conflictData: ConflictData): SyncStatus {
        return copy(
            status = SyncState.CONFLICT,
            conflictData = conflictData
        )
    }

    fun resolveConflict(resolution: ConflictResolution): SyncStatus {
        return when (resolution) {
            ConflictResolution.USE_LOCAL -> markAsSynced()
            ConflictResolution.USE_REMOTE -> markAsSynced()
            ConflictResolution.MERGE -> markAsSynced()
        }.copy(conflictData = null)
    }

    fun getTimeSinceLastAttempt(): Long? {
        return lastAttemptTimestamp?.let { System.currentTimeMillis() - it }
    }

    fun shouldRetry(): Boolean {
        if (!canRetry()) return false
        val timeSinceLastAttempt = getTimeSinceLastAttempt() ?: return true
        return timeSinceLastAttempt > calculateBackoffDelay()
    }

    private fun calculateBackoffDelay(): Long {
        val baseDelay = 1000L
        val maxDelay = 30000L
        val delay = baseDelay * (1 shl retryCount)
        return delay.coerceAtMost(maxDelay)
    }

    fun toDisplayString(): String {
        return when (status) {
            SyncState.PENDING -> "Pendiente de sincronizar"
            SyncState.SYNCING -> "Sincronizando..."
            SyncState.SYNCED -> "Sincronizado"
            SyncState.FAILED -> "Error: ${errorMessage ?: "Desconocido"}"
            SyncState.CONFLICT -> "Conflicto detectado"
        }
    }

    companion object {
        const val MAX_RETRY_COUNT = 3

        fun createForEntity(entityType: EntityType, entityId: String): SyncStatus {
            return SyncStatus(
                entityType = entityType,
                entityId = entityId
            )
        }
    }
}

enum class SyncState(val displayName: String) {
    PENDING("Pendiente"),
    SYNCING("Sincronizando"),
    SYNCED("Sincronizado"),
    FAILED("Fallido"),
    CONFLICT("Conflicto");

    fun isActive(): Boolean = this == PENDING || this == SYNCING
    fun isTerminal(): Boolean = this == SYNCED || this == FAILED
}

enum class EntityType(val displayName: String, val tableName: String) {
    PRODUCT("Producto", "products"),
    PURCHASE("Compra", "purchases"),
    USER_PREFERENCES("Preferencias", "user_preferences");

    fun getSyncPriority(): Int {
        return when (this) {
            PRODUCT -> 1
            PURCHASE -> 2
            USER_PREFERENCES -> 3
        }
    }
}

data class ConflictData(
    val localVersion: String,
    val remoteVersion: String,
    val localTimestamp: Long,
    val remoteTimestamp: Long,
    val conflictFields: List<String>
) {
    fun getOlderVersion(): VersionInfo {
        return if (localTimestamp < remoteTimestamp) {
            VersionInfo("local", localVersion, localTimestamp)
        } else {
            VersionInfo("remote", remoteVersion, remoteTimestamp)
        }
    }

    fun getNewerVersion(): VersionInfo {
        return if (localTimestamp > remoteTimestamp) {
            VersionInfo("local", localVersion, localTimestamp)
        } else {
            VersionInfo("remote", remoteVersion, remoteTimestamp)
        }
    }

    fun hasFieldConflict(field: String): Boolean {
        return conflictFields.contains(field)
    }
}

data class VersionInfo(
    val source: String,
    val version: String,
    val timestamp: Long
)

enum class ConflictResolution(val displayName: String) {
    USE_LOCAL("Usar versión local"),
    USE_REMOTE("Usar versión remota"),
    MERGE("Combinar versiones")
}