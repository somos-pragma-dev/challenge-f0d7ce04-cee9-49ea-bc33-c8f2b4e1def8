package com.example.offlinefirst.domain.repository

import com.example.offlinefirst.domain.model.PurchaseHistory
import com.example.offlinefirst.domain.model.PurchaseStatus
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow

interface PurchaseRepository {
    fun getPurchases(): Flow<Resource<List<PurchaseHistory>>>
    
    fun getPurchaseById(id: String): Flow<Resource<PurchaseHistory>>
    
    fun getPurchasesByStatus(status: PurchaseStatus): Flow<Resource<List<PurchaseHistory>>>
    
    fun getActivePurchases(): Flow<Resource<List<PurchaseHistory>>>
    
    fun getPurchasesByDateRange(
        startDate: Long,
        endDate: Long
    ): Flow<Resource<List<PurchaseHistory>>>
    
    suspend fun savePurchase(purchase: PurchaseHistory): Resource<PurchaseHistory>
    
    suspend fun savePurchases(purchases: List<PurchaseHistory>): Resource<List<PurchaseHistory>>
    
    suspend fun updatePurchaseStatus(
        purchaseId: String,
        newStatus: PurchaseStatus
    ): Resource<PurchaseHistory>
    
    suspend fun updateShippingInfo(
        purchaseId: String,
        trackingNumber: String?,
        deliveryDate: Long?
    ): Resource<PurchaseHistory>
    
    suspend fun deletePurchase(id: String): Resource<Unit>
    
    suspend fun deleteAllPurchases(): Resource<Unit>
    
    fun getSyncStatusForPurchase(id: String): Flow<Resource<SyncStatus>>
    
    fun getPendingSyncPurchases(): Flow<Resource<List<PurchaseHistory>>>
    
    suspend fun syncPurchases(): Resource<List<PurchaseHistory>>
    
    suspend fun getPurchaseCount(): Int
    
    suspend fun getTotalSpent(): Double
    
    fun observePurchases(): Flow<List<PurchaseHistory>>
    
    fun observePurchaseById(id: String): Flow<PurchaseHistory?>
    
    fun getPurchasesByProductId(productId: String): Flow<Resource<List<PurchaseHistory>>>
}