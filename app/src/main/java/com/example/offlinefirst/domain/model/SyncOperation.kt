package com.example.offlinefirst.domain.model

data class SyncOperation(
    val id: String,
    val entityId: String,
    val entityType: EntityType,
    val operationType: SyncOperationType,
    val payload: String,
    val status: SyncOperationStatus = SyncOperationStatus.PENDING,
    val errorMessage: String? = null,
    val retryCount: Int = 0,
    val maxRetries: Int = 3,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val startedAt: Long? = null,
    val completedAt: Long? = null,
    val priority: OperationPriority = OperationPriority.NORMAL,
    val dependsOn: String? = null
) {
    fun canRetry(): Boolean = status == SyncOperationStatus.FAILED && retryCount < maxRetries
    fun shouldRetry(): Boolean = when {
        status != SyncOperationStatus.FAILED -> false
        retryCount >= maxRetries -> false
        else -> true
    }
    fun incrementAttempt(): SyncOperation = copy(retryCount = retryCount + 1, updatedAt = System.currentTimeMillis())
    fun markAsInProgress(): SyncOperation = copy(
        status = SyncOperationStatus.IN_PROGRESS,
        startedAt = System.currentTimeMillis(),
        updatedAt = System.currentTimeMillis()
    )
    fun markAsCompleted(): SyncOperation = copy(
        status = SyncOperationStatus.COMPLETED,
        completedAt = System.currentTimeMillis(),
        updatedAt = System.currentTimeMillis()
    )
    fun markAsFailed(error: String): SyncOperation = copy(
        status = SyncOperationStatus.FAILED,
        errorMessage = error,
        updatedAt = System.currentTimeMillis()
    )
}

enum class EntityType {
    USER, PREFERENCES, CONTENT, SYNC_METADATA
}

enum class SyncOperationStatus {
    PENDING, IN_PROGRESS, COMPLETED, FAILED, CANCELLED
}

enum class OperationPriority {
    CRITICAL, HIGH, NORMAL, LOW
}