package com.example.offlinefirst.domain.usecase

import com.example.offlinefirst.data.sync.NetworkConnectivityManager
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.repository.ProductRepository
import com.example.offlinefirst.domain.repository.PurchaseRepository
import com.example.offlinefirst.domain.repository.UserPreferencesRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SyncDataUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val purchaseRepository: PurchaseRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val networkConnectivityManager: NetworkConnectivityManager
) {
    suspend operator fun invoke(
        syncProducts: Boolean = true,
        syncPurchases: Boolean = true
    ): Resource<SyncResult> = flow {
        emit(Resource.Loading())

        if (!networkConnectivityManager.isCurrentlyConnected()) {
            emit(Resource.Error("No hay conexión a Internet"))
            return@flow
        }

        var productsSynced = 0
        var purchasesSynced = 0
        var errors = mutableListOf<String>()

        try {
            if (syncProducts) {
                try {
                    productRepository.syncProducts()
                    productsSynced++
                } catch (e: Exception) {
                    errors.add("Error sincronizando productos: ${e.message}")
                }
            }

            if (syncPurchases) {
                try {
                    purchaseRepository.syncPurchases()
                    purchasesSynced++
                } catch (e: Exception) {
                    errors.add("Error sincronizando compras: ${e.message}")
                }
            }

            val result = SyncResult(
                productsSynced = productsSynced > 0,
                purchasesSynced = purchasesSynced > 0,
                errors = errors,
                timestamp = System.currentTimeMillis()
            )

            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error en sincronización", e))
        }
    }

    fun observeNetworkStatus(): Flow<Boolean> = networkConnectivityManager.observeConnectivity()

    suspend fun checkPendingSync(): PendingSyncInfo {
        val pendingProducts = productRepository.getProducts().first()
            .count { /* lógica para determinar si hay productos pendientes */ false }

        val pendingPurchases = purchaseRepository.getPendingSyncPurchases().first().size

        return PendingSyncInfo(
            hasPendingProducts = pendingProducts > 0,
            hasPendingPurchases = pendingPurchases > 0,
            totalPendingItems = pendingProducts + pendingPurchases
        )
    }
}

data class SyncResult(
    val productsSynced: Boolean,
    val purchasesSynced: Boolean,
    val errors: List<String>,
    val timestamp: Long
) {
    fun isSuccess(): Boolean = productsSynced || purchasesSynced
    fun hasErrors(): Boolean = errors.isNotEmpty()
}

data class PendingSyncInfo(
    val hasPendingProducts: Boolean,
    val hasPendingPurchases: Boolean,
    val totalPendingItems: Int
)

class GetSyncStatusUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(): Resource<SyncStatus> {
        return try {
            val products = productRepository.getProducts().first()
            val purchases = purchaseRepository.getPurchases().first()

            val lastSyncTime = System.currentTimeMillis()
            val pendingCount = purchases.count { /* pending sync */ false }

            val status = SyncStatus(
                lastSyncTime = lastSyncTime,
                pendingChanges = pendingCount,
                isSyncing = false,
                lastError = null
            )

            Resource.Success(status)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al obtener estado de sincronización", e)
        }
    }

    fun observeSyncStatus(): Flow<SyncStatus> = flow {
        val products = productRepository.getProducts().first()
        val purchases = purchaseRepository.getPurchases().first()

        emit(
            SyncStatus(
                lastSyncTime = System.currentTimeMillis(),
                pendingChanges = 0,
                isSyncing = false,
                lastError = null
            )
        )
    }
}