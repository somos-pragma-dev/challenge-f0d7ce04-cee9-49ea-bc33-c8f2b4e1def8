package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.SyncState

@Entity(
    tableName = "sync_metadata",
    indices = [
        Index(value = ["entity_type"]),
        Index(value = ["entity_id"]),
        Index(value = ["status"]),
        Index(value = ["last_attempt_at"])
    ]
)
data class SyncMetadataEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "entity_type")
    val entityType: String,

    @ColumnInfo(name = "entity_id")
    val entityId: String,

    @ColumnInfo(name = "operation")
    val operation: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "local_version")
    val localVersion: Int,

    @ColumnInfo(name = "server_version")
    val serverVersion: Int?,

    @ColumnInfo(name = "local_data")
    val localData: String?,

    @ColumnInfo(name = "server_data")
    val serverData: String?,

    @ColumnInfo(name = "conflict_resolution")
    val conflictResolution: String?,

    @ColumnInfo(name = "error_message")
    val errorMessage: String?,

    @ColumnInfo(name = "retry_count")
    val retryCount: Int,

    @ColumnInfo(name = "max_retries")
    val maxRetries: Int,

    @ColumnInfo(name = "created_at")
    val createdAt: Long,

    @ColumnInfo(name = "last_attempt_at")
    val lastAttemptAt: Long?,

    @ColumnInfo(name = "next_retry_at")
    val nextRetryAt: Long?,

    @ColumnInfo(name = "completed_at")
    val completedAt: Long?
) {
    fun toDomain(): SyncStatus {
        return SyncStatus(
            entityType = entityType,
            entityId = entityId,
            status = try {
                SyncState.valueOf(status)
            } catch (e: Exception) {
                SyncState.PENDING
            },
            operation = operation,
            localVersion = localVersion,
            serverVersion = serverVersion,
            lastAttemptAt = lastAttemptAt,
            retryCount = retryCount,
            errorMessage = errorMessage
        )
    }

    fun canRetry(): Boolean {
        return retryCount < maxRetries && 
               (nextRetryAt == null || System.currentTimeMillis() >= nextRetryAt)
    }

    fun shouldRetry(): Boolean {
        return status == SyncState.FAILED.name && canRetry()
    }

    companion object {
        fun createForNewEntity(
            entityType: String,
            entityId: String,
            operation: String,
            localData: String?
        ): SyncMetadataEntity {
            return SyncMetadataEntity(
                id = "${entityType}_${entityId}_${System.currentTimeMillis()}",
                entityType = entityType,
                entityId = entityId,
                operation = operation,
                status = SyncState.PENDING.name,
                localVersion = 1,
                serverVersion = null,
                localData = localData,
                serverData = null,
                conflictResolution = null,
                errorMessage = null,
                retryCount = 0,
                maxRetries = 3,
                createdAt = System.currentTimeMillis(),
                lastAttemptAt = null,
                nextRetryAt = null,
                completedAt = null
            )
        }

        fun createForUpdate(
            entityType: String,
            entityId: String,
            localVersion: Int,
            localData: String?
        ): SyncMetadataEntity {
            return SyncMetadataEntity(
                id = "${entityType}_${entityId}_update_${System.currentTimeMillis()}",
                entityType = entityType,
                entityId = entityId,
                operation = "UPDATE",
                status = SyncState.PENDING.name,
                localVersion = localVersion,
                serverVersion = null,
                localData = localData,
                serverData = null,
                conflictResolution = null,
                errorMessage = null,
                retryCount = 0,
                maxRetries = 3,
                createdAt = System.currentTimeMillis(),
                lastAttemptAt = null,
                nextRetryAt = null,
                completedAt = null
            )
        }

        const val ENTITY_TYPE_PRODUCT = "PRODUCT"
        const val ENTITY_TYPE_PURCHASE = "PURCHASE"
        const val ENTITY_TYPE_USER_PREFERENCES = "USER_PREFERENCES"

        const val OPERATION_CREATE = "CREATE"
        const val OPERATION_UPDATE = "UPDATE"
        const val OPERATION_DELETE = "DELETE"

        const val STATUS_PENDING = "PENDING"
        const val STATUS_IN_PROGRESS = "IN_PROGRESS"
        const val STATUS_COMPLETED = "COMPLETED"
        const val STATUS_FAILED = "FAILED"
        const val STATUS_CONFLICT = "CONFLICT"

        const val CONFLICT_RESOLUTION_LOCAL = "LOCAL_WINS"
        const val CONFLICT_RESOLUTION_SERVER = "SERVER_WINS"
        const val CONFLICT_RESOLUTION_MERGE = "MERGE"
    }
}

@Entity(
    tableName = "sync_queue",
    indices = [
        Index(value = ["entity_type", "entity_id"], unique = true),
        Index(value = ["priority"]),
        Index(value = ["scheduled_at"])
    ]
)
data class SyncQueueEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "entity_type")
    val entityType: String,

    @ColumnInfo(name = "entity_id")
    val entityId: String,

    @ColumnInfo(name = "operation")
    val operation: String,

    @ColumnInfo(name = "payload")
    val payload: String,

    @ColumnInfo(name = "priority")
    val priority: Int,

    @ColumnInfo(name = "scheduled_at")
    val scheduledAt: Long,

    @ColumnInfo(name = "attempted_at")
    val attemptedAt: Long?,

    @ColumnInfo(name = "attempt_count")
    val attemptCount: Int,

    @ColumnInfo(name = "max_attempts")
    val maxAttempts: Int,

    @ColumnInfo(name = "last_error")
    val lastError: String?,

    @ColumnInfo(name = "created_at")
    val createdAt: Long
)