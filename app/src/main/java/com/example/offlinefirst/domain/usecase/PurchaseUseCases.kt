package com.example.offlinefirst.domain.usecase

import com.example.offlinefirst.domain.model.PurchaseHistory
import com.example.offlinefirst.domain.model.PurchaseStatus
import com.example.offlinefirst.domain.repository.PurchaseRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPurchaseHistoryUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    operator fun invoke(): Flow<Resource<List<PurchaseHistory>>> = flow {
        emit(Resource.Loading())
        try {
            val purchases = purchaseRepository.getPurchases().first()
            emit(Resource.Success(purchases))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al obtener historial de compras", e))
        }
    }

    fun observePurchases(): Flow<List<PurchaseHistory>> = purchaseRepository.getPurchases()

    suspend fun getActivePurchases(): List<PurchaseHistory> {
        return purchaseRepository.getPurchases().first()
            .filter { it.isActive() }
    }

    suspend fun getPurchasesByStatus(status: PurchaseStatus): List<PurchaseHistory> {
        return purchaseRepository.getPurchases().first()
            .filter { it.status == status }
    }
}

class GetPurchaseByIdUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(purchaseId: String): Resource<PurchaseHistory> {
        return try {
            val purchase = purchaseRepository.getPurchaseById(purchaseId)
            if (purchase != null) {
                Resource.Success(purchase)
            } else {
                Resource.Error("Compra no encontrada")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al obtener compra", e)
        }
    }
}

class CreatePurchaseUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(purchase: PurchaseHistory): Resource<PurchaseHistory> {
        return try {
            if (!purchase.isValid()) {
                return Resource.Error("Datos de compra inválidos")
            }
            val savedPurchase = purchaseRepository.savePurchase(purchase)
            Resource.Success(savedPurchase)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al crear compra", e)
        }
    }
}

class CancelPurchaseUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(purchaseId: String): Resource<PurchaseHistory> {
        return try {
            val purchase = purchaseRepository.getPurchaseById(purchaseId)
                ?: return Resource.Error("Compra no encontrada")

            if (!purchase.canBeCancelled()) {
                return Resource.Error("Esta compra no puede ser cancelada")
            }

            val cancelledPurchase = purchase.withStatus(PurchaseStatus.CANCELLED)
            val updated = purchaseRepository.savePurchase(cancelledPurchase)
            Resource.Success(updated)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al cancelar compra", e)
        }
    }
}

class UpdatePurchaseStatusUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(
        purchaseId: String,
        newStatus: PurchaseStatus,
        trackingNumber: String? = null,
        deliveryDate: Long? = null
    ): Resource<PurchaseHistory> {
        return try {
            val purchase = purchaseRepository.getPurchaseById(purchaseId)
                ?: return Resource.Error("Compra no encontrada")

            val updatedPurchase = purchase
                .withStatus(newStatus)
                .withShippingInfo(trackingNumber, deliveryDate)

            val saved = purchaseRepository.savePurchase(updatedPurchase)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar estado", e)
        }
    }
}

class SyncPurchasesUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(): Resource<List<PurchaseHistory>> {
        return try {
            val purchases = purchaseRepository.syncPurchases()
            Resource.Success(purchases)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al sincronizar compras", e)
        }
    }

    fun getPendingSyncPurchases(): Flow<List<PurchaseHistory>> {
        return purchaseRepository.getPendingSyncPurchases()
    }
}

class GetPurchaseStatisticsUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(): Resource<PurchaseStatistics> {
        return try {
            val purchases = purchaseRepository.getPurchases().first()
            val totalPurchases = purchases.size
            val totalSpent = purchases.sumOf { it.totalPrice.toDouble() }
            val activePurchases = purchases.count { it.isActive() }
            val cancelledPurchases = purchases.count { it.status == PurchaseStatus.CANCELLED }

            Resource.Success(
                PurchaseStatistics(
                    totalPurchases = totalPurchases,
                    totalSpent = totalSpent,
                    activePurchases = activePurchases,
                    cancelledPurchases = cancelledPurchases
                )
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al calcular estadísticas", e)
        }
    }
}

data class PurchaseStatistics(
    val totalPurchases: Int,
    val totalSpent: Double,
    val activePurchases: Int,
    val cancelledPurchases: Int
)