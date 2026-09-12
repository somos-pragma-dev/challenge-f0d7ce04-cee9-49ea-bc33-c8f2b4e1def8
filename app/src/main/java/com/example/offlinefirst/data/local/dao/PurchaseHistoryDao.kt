package com.example.offlinefirst.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.offlinefirst.data.local.entity.PurchaseHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseHistoryDao {
    fun getAllPurchases(): Flow<List<PurchaseHistoryEntity>>
    fun getPurchaseById(purchaseId: String): Flow<PurchaseHistoryEntity?>
    suspend fun getPurchaseByIdSync(purchaseId: String): PurchaseHistoryEntity?
    fun getPurchasesByStatus(status: String): Flow<List<PurchaseHistoryEntity>>
    fun getPurchasesByProduct(productId: String): Flow<List<PurchaseHistoryEntity>>
    fun getPurchasesByDateRange(startDate: Long, endDate: Long): Flow<List<PurchaseHistoryEntity>>
    suspend fun getPurchasesToSync(): List<PurchaseHistoryEntity>
    suspend fun getPurchasesUpdatedSince(lastSyncTimestamp: Long): List<PurchaseHistoryEntity>
    fun getActivePurchasesSince(sinceTimestamp: Long): Flow<List<PurchaseHistoryEntity>>
    suspend fun insertPurchase(purchase: PurchaseHistoryEntity)
    suspend fun insertPurchases(purchases: List<PurchaseHistoryEntity>)
    suspend fun updatePurchase(purchase: PurchaseHistoryEntity)
    suspend fun deletePurchase(purchase: PurchaseHistoryEntity)
    suspend fun deletePurchaseById(purchaseId: String)
    suspend fun deleteAllPurchases()
    suspend fun updateSyncStatus(purchaseId: String, syncStatus: String)
    suspend fun updateStatus(purchaseId: String, status: String, updatedAt: Long)
    suspend fun updateShippingInfo(purchaseId: String, tracking: String?, deliveryDate: Long?, updatedAt: Long)
    suspend fun updatePurchaseStatus(purchaseId: String, status: String)
    suspend fun markPurchaseForDeletion(purchaseId: String)
    fun getPendingPurchases(): Flow<List<PurchaseHistoryEntity>>
    suspend fun deletePendingPurchase(purchaseId: String)
    @Query("SELECT COUNT(*) FROM purchase_history")
    fun getPurchaseCount(): Flow<Int>
    @Query("SELECT SUM(totalPrice) FROM purchase_history WHERE status = 'COMPLETED' AND purchaseDate BETWEEN :startDate AND :endDate")
    fun getTotalSpentInRange(startDate: Long, endDate: Long): Flow<Double?>
    @Query("SELECT SUM(quantity) FROM purchase_history WHERE productId = :productId")
    fun getTotalQuantityPurchasedForProduct(productId: String): Flow<Int?>
}