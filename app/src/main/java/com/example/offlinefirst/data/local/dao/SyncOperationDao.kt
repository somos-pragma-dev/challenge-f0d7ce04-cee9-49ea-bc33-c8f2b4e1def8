package com.example.offlinefirst.data.local.dao

import androidx.room.*
import com.example.offlinefirst.data.local.entity.SyncOperationEntity
import kotlinx.coroutines.flow.Flow

interface SyncOperationDao {
    suspend fun insert(operation: SyncOperationEntity): Long
    suspend fun insertAll(operations: List<SyncOperationEntity>): List<Long>
    suspend fun update(operation: SyncOperationEntity)
    suspend fun delete(operation: SyncOperationEntity)
    suspend fun deleteById(id: String)
    suspend fun deleteByStatus(status: String)
    suspend fun deleteOldCompleted(olderThan: Long): Int
    suspend fun getById(id: String): SyncOperationEntity?
    fun getByIdFlow(id: String): Flow<SyncOperationEntity?>
    fun getAll(): Flow<List<SyncOperationEntity>>
    suspend fun getAllLimited(limit: Int): List<SyncOperationEntity>
    fun getByStatus(status: String): Flow<List<SyncOperationEntity>>
    suspend fun getPendingByStatus(status: String, limit: Int): List<SyncOperationEntity>
    suspend fun getByStatuses(statuses: List<String>, limit: Int): List<SyncOperationEntity>
    suspend fun getByEntityTypeAndStatus(entityType: String, status: String): List<SyncOperationEntity>
    suspend fun getPendingOperationForEntity(entityType: String, entityId: String): SyncOperationEntity?
    fun getByOperationType(operationType: String): Flow<List<SyncOperationEntity>>
    @Query("SELECT COUNT(*) FROM sync_operation")
    suspend fun getOperationCount(): Int
    @Query("SELECT COUNT(*) FROM sync_operation WHERE status = :status")
    suspend fun getCountByStatus(status: String): Int
    @Query("SELECT COUNT(*) FROM sync_operation WHERE entityType = :entityType")
    suspend fun getCountByEntityType(entityType: String): Int
    @Query("SELECT COUNT(*) FROM sync_operation WHERE operationType = :operationType AND status = :status")
    suspend fun getCountByOperationTypeAndStatus(operationType: String, status: String): Int
    suspend fun updateStatus(id: String, status: String, errorMessage: String?, updatedAt: Long)
    suspend fun markAsCompleted(id: String, status: String, executedAt: Long, updatedAt: Long)
    suspend fun markAsFailed(id: String, status: String, errorMessage: String?, updatedAt: Long)
    suspend fun incrementRetryCount(id: String, updatedAt: Long)
    suspend fun getNextPending(): SyncOperationEntity?
    suspend fun getNextBatch(batchSize: Int): List<SyncOperationEntity>
    @Query("SELECT * FROM sync_operation WHERE status IN ('PENDING', 'IN_PROGRESS') ORDER BY createdAt ASC")
    fun getActiveOperations(): Flow<List<SyncOperationEntity>>
    suspend fun getRetryableOperations(maxRetries: Int): List<SyncOperationEntity>
    suspend fun getFailedOperations(maxRetries: Int): List<SyncOperationEntity>
    suspend fun cancelPendingForEntity(entityType: String, entityId: String): Int
    @Query("SELECT EXISTS(SELECT 1 FROM sync_operation WHERE entityType = :entityType AND entityId = :entityId AND status IN ('PENDING', 'IN_PROGRESS'))")
    suspend fun hasPendingOperation(entityType: String, entityId: String): Boolean
    suspend fun getAndLockNextPending(): SyncOperationEntity?
    @Query("SELECT MAX(createdAt) FROM sync_operation WHERE entityType = :entityType AND status = 'COMPLETED'")
    suspend fun getLastCompletedTime(entityType: String): Long?
    suspend fun getOperationsSince(since: Long): List<SyncOperationEntity>

    // Additional methods needed by SyncRepositoryImpl
    @Query("SELECT * FROM sync_operation WHERE id = :id")
    suspend fun getOperationById(id: String): SyncOperationEntity?

    @Query("SELECT * FROM sync_operation ORDER BY createdAt ASC")
    suspend fun getAllOperations(): List<SyncOperationEntity>

    @Query("SELECT * FROM sync_operation WHERE status = 'PENDING' ORDER BY createdAt ASC")
    suspend fun getPendingOperations(): List<SyncOperationEntity>

    @Query("SELECT * FROM sync_operation WHERE operationType = :operationType ORDER BY createdAt ASC")
    suspend fun getOperationsByType(operationType: String): List<SyncOperationEntity>

    @Query("SELECT * FROM sync_operation WHERE status = :status ORDER BY createdAt ASC")
    suspend fun getOperationsByStatus(status: String): List<SyncOperationEntity>

    @Query("SELECT * FROM sync_operation WHERE status = 'FAILED' ORDER BY createdAt ASC")
    suspend fun getFailedOperationsList(): List<SyncOperationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(operation: SyncOperationEntity)

    @Query("SELECT * FROM sync_operation WHERE id IN (:ids)")
    suspend fun getOperationsByIds(ids: List<String>): List<SyncOperationEntity>

    @Query("UPDATE sync_operation SET completedAt = :completedAt, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateCompletedAt(id: String, completedAt: Long, updatedAt: Long)

    @Query("DELETE FROM sync_operation WHERE status = 'COMPLETED' AND completedAt < :olderThan")
    suspend fun deleteCompletedOperations(olderThan: Long): Int

    @Query("DELETE FROM sync_operation WHERE createdAt < :olderThanTimestamp")
    suspend fun deleteOldOperations(olderThanTimestamp: Long): Int

    @Query("SELECT COUNT(*) FROM sync_operation WHERE status = 'PENDING'")
    suspend fun getPendingCount(): Int

    @Query("SELECT COUNT(*) FROM sync_operation WHERE status = 'FAILED'")
    suspend fun getFailedCount(): Int

    @Query("UPDATE sync_operation SET status = 'PENDING', updatedAt = :updatedAt WHERE status = 'FAILED'")
    suspend fun retryAllFailed(): Int

    @Query("SELECT * FROM sync_operation WHERE entityId = :entityId ORDER BY createdAt ASC")
    suspend fun getOperationsByEntityId(entityId: String): List<SyncOperationEntity>
}