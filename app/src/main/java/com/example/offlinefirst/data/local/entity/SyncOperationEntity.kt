package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.offlinefirst.domain.model.EntityType
import com.example.offlinefirst.domain.model.OperationType
import com.example.offlinefirst.domain.model.SyncOperation

@Entity(
    tableName = "sync_operation",
    indices = [
        Index(value = ["entity_type", "entity_id"]),
        Index(value = ["status"]),
        Index(value = ["created_at"])
    ]
)
data class SyncOperationEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "entity_type")
    val entityType: String,

    @ColumnInfo(name = "entity_id")
    val entityId: String,

    @ColumnInfo(name = "operation_type")
    val operationType: String,

    @ColumnInfo(name = "payload")
    val payload: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "retry_count")
    val retryCount: Int,

    @ColumnInfo(name = "max_retries")
    val maxRetries: Int,

    @ColumnInfo(name = "created_at")
    val createdAt: Long,

    @ColumnInfo(name = "last_attempt_at")
    val lastAttemptAt: Long?,

    @ColumnInfo(name = "error_message")
    val errorMessage: String?,

    @ColumnInfo(name = "priority")
    val priority: Int,

    @ColumnInfo(name = "depends_on")
    val dependsOn: String?
) {
    fun toDomainModel(): SyncOperation {
        return SyncOperation(
            id = id,
            entityType = EntityType.valueOf(entityType),
            entityId = entityId,
            operationType = OperationType.valueOf(operationType),
            payload = try {
                SyncOperation.parsePayload(payload)
            } catch (e: Exception) {
                emptyMap()
            },
            status = com.example.offlinefirst.domain.model.SyncOperationStatus.valueOf(status),
            retryCount = retryCount,
            maxRetries = maxRetries,
            createdAt = createdAt,
            lastAttemptAt = lastAttemptAt,
            errorMessage = errorMessage,
            priority = priority,
            dependsOn = dependsOn
        )
    }

    companion object {
        fun fromDomainModel(domain: SyncOperation): SyncOperationEntity {
            return SyncOperationEntity(
                id = domain.id,
                entityType = domain.entityType.name,
                entityId = domain.entityId,
                operationType = domain.operationType.name,
                payload = SyncOperation.serializePayload(domain.payload),
                status = domain.status.name,
                retryCount = domain.retryCount,
                maxRetries = domain.maxRetries,
                createdAt = domain.createdAt,
                lastAttemptAt = domain.lastAttemptAt,
                errorMessage = domain.errorMessage,
                priority = domain.priority,
                dependsOn = domain.dependsOn
            )
        }
    }
}