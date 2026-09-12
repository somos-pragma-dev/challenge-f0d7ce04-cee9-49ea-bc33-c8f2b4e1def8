package com.example.offlinefirst.domain.repository

import com.example.offlinefirst.domain.model.SyncOperation
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow

interface SyncRepository {
    fun getPendingOperations(): Flow<List<SyncOperation>>
    
    fun getOperationsByStatus(status: SyncStatus): Flow<List<SyncOperation>>
    
    suspend fun queueOperation(operation: SyncOperation): Resource<SyncOperation>
    
    suspend fun markOperationAsCompleted(operationId: String): Resource<Unit>
    
    suspend fun markOperationAsFailed(operationId: String, error: String): Resource<Unit>
    
    suspend fun getOperationById(operationId: String): Resource<SyncOperation>
    
    suspend fun deleteCompletedOperations(olderThan: Long): Resource<Int>
    
    suspend fun retryFailedOperations(): Resource<List<SyncOperation>>
    
    suspend fun getOperationCount(): Resource<Int>
    
    suspend fun getPendingCount(): Resource<Int>
    
    suspend fun clearAllOperations(): Resource<Unit>
    
    fun observeSyncStatus(): Flow<SyncStatus>
    
    suspend fun executeSync(): Resource<SyncOperationResult>
}

data class SyncOperationResult(
    val totalOperations: Int,
    val successfulOperations: Int,
    val failedOperations: Int,
    val skippedOperations: Int,
    val startTime: Long,
    val endTime: Long
) {
    val duration: Long get() = endTime - startTime
    
    val successRate: Float get() = if (totalOperations > 0) {
        successfulOperations.toFloat() / totalOperations
    } else 0f
    
    fun isFullySuccessful(): Boolean = failedOperations == 0 && successfulOperations > 0
    
    fun hasFailures(): Boolean = failedOperations > 0
}