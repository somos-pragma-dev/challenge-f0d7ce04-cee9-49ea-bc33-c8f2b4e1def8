package com.example.offlinefirst.data.repository

import com.example.offlinefirst.data.local.dao.PurchaseHistoryDao
import com.example.offlinefirst.data.local.entity.PurchaseHistoryEntity
import com.example.offlinefirst.data.remote.api.OfflineFirstApiService
import com.example.offlinefirst.data.remote.dto.CreatePurchaseRequest
import com.example.offlinefirst.data.remote.dto.PurchaseDto
import com.example.offlinefirst.domain.model.PurchaseHistory
import com.example.offlinefirst.domain.model.PurchaseStatus
import com.example.offlinefirst.domain.model.PaymentMethod
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.SyncState
import com.example.offlinefirst.domain.repository.PurchaseRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PurchaseRepositoryImpl @Inject constructor(
    private val purchaseDao: PurchaseHistoryDao,
    private val apiService: OfflineFirstApiService
) : PurchaseRepository {

    override fun getPurchaseHistory(): Flow<Resource<List<PurchaseHistory>>> = flow {
        emit(Resource.Loading())
        
        try {
            val localPurchases = purchaseDao.getAllPurchases().first()
            
            if (localPurchases.isEmpty()) {
                val response = apiService.getPurchases()
                if (response.isSuccessful && response.body() != null) {
                    val remotePurchases = response.body()!!.purchases
                    val entities = remotePurchases.map { it.toEntity() }
                    purchaseDao.insertPurchases(entities)
                    emit(Resource.Success(entities.map { it.toDomain() }))
                } else {
                    emit(Resource.Error("Error al cargar historial: ${response.message()}"))
                }
            } else {
                try {
                    val response = apiService.getPurchases()
                    if (response.isSuccessful && response.body() != null) {
                        val remotePurchases = response.body()!!.purchases
                        val mergedPurchases = mergePurchases(localPurchases, remotePurchases)
                        purchaseDao.insertPurchases(mergedPurchases)
                        emit(Resource.Success(mergedPurchases.map { it.toDomain() }))
                    } else {
                        emit(Resource.Success(localPurchases.map { it.toDomain() }))
                    }
                } catch (e: Exception) {
                    emit(Resource.Success(localPurchases.map { it.toDomain() }))
                }
            }
        } catch (e: Exception) {
            val localPurchases = purchaseDao.getAllPurchases().first()
            if (localPurchases.isNotEmpty()) {
                emit(Resource.Success(localPurchases.map { it.toDomain() }))
            } else {
                emit(Resource.Error("Error de conexión: ${e.localizedMessage}"))
            }
        }
    }

    override fun getPurchaseById(id: String): Flow<Resource<PurchaseHistory>> = flow {
        emit(Resource.Loading())
        
        val localPurchase = purchaseDao.getPurchaseById(id).first()
        if (localPurchase != null) {
            emit(Resource.Success(localPurchase.toDomain()))
            return@flow
        }
        
        try {
            val response = apiService.getPurchaseById(id)
            if (response.isSuccessful && response.body() != null) {
                val purchase = response.body()!!
                purchaseDao.insertPurchase(purchase.toEntity())
                emit(Resource.Success(purchase.toDomain()))
            } else {
                emit(Resource.Error("Compra no encontrada"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error de conexión: ${e.localizedMessage}"))
        }
    }

    override suspend fun createPurchase(purchase: PurchaseHistory): Resource<PurchaseHistory> {
        return try {
            val request = CreatePurchaseRequest(
                productId = purchase.productId,
                quantity = purchase.quantity,
                unitPrice = purchase.unitPrice.toDouble(),
                totalPrice = purchase.totalPrice.toDouble(),
                paymentMethod = purchase.paymentMethod.name,
                shippingAddress = purchase.shippingAddress,
                shippingCity = purchase.shippingCity,
                shippingPostalCode = purchase.shippingPostalCode
            )
            
            val response = apiService.createPurchase(request)
            if (response.isSuccessful && response.body() != null) {
                val createdPurchase = response.body()!!
                purchaseDao.insertPurchase(createdPurchase.toEntity())
                Resource.Success(createdPurchase.toDomain())
            } else {
                val pendingEntity = purchase.toPendingEntity()
                purchaseDao.insertPurchase(pendingEntity)
                Resource.Success(purchase)
            }
        } catch (e: Exception) {
            val pendingEntity = purchase.toPendingEntity()
            purchaseDao.insertPurchase(pendingEntity)
            Resource.Success(purchase)
        }
    }

    override suspend fun cancelPurchase(id: String): Resource<Unit> {
        return try {
            val response = apiService.cancelPurchase(id)
            if (response.isSuccessful) {
                purchaseDao.updatePurchaseStatus(id, PurchaseStatus.CANCELLED.name)
                Resource.Success(Unit)
            } else {
                purchaseDao.markPurchaseForDeletion(id)
                Resource.Success(Unit)
            }
        } catch (e: Exception) {
            purchaseDao.markPurchaseForDeletion(id)
            Resource.Success(Unit)
        }
    }

    override fun getActivePurchases(): Flow<Resource<List<PurchaseHistory>>> = flow {
        emit(Resource.Loading())
        
        val activePurchases = purchaseDao.getPurchasesByStatus(
            listOf(PurchaseStatus.PENDING.name, PurchaseStatus.CONFIRMED.name, PurchaseStatus.SHIPPED.name)
        ).first()
        
        emit(Resource.Success(activePurchases.map { it.toDomain() }))
    }

    override fun getPendingPurchases(): Flow<List<PurchaseHistory>> {
        return purchaseDao.getPendingPurchases().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun syncPendingPurchases(): Resource<Int> {
        val pendingPurchases = purchaseDao.getPendingPurchases().first()
        var syncedCount = 0
        
        for (purchase in pendingPurchases) {
            try {
                val request = CreatePurchaseRequest(
                    productId = purchase.productId,
                    quantity = purchase.quantity,
                    unitPrice = purchase.unitPrice.toDouble(),
                    totalPrice = purchase.totalPrice.toDouble(),
                    paymentMethod = purchase.paymentMethod,
                    shippingAddress = purchase.shippingAddress,
                    shippingCity = purchase.shippingCity,
                    shippingPostalCode = purchase.shippingPostalCode
                )
                val response = apiService.createPurchase(request)
                if (response.isSuccessful) {
                    purchaseDao.deletePendingPurchase(purchase.id)
                    syncedCount++
                }
            } catch (e: Exception) {
                // Continuar con el siguiente
            }
        }
        
        return Resource.Success(syncedCount)
    }

    private fun mergePurchases(
        local: List<PurchaseHistoryEntity>,
        remote: List<PurchaseHistoryEntity>
    ): List<PurchaseHistoryEntity> {
        val merged = mutableMapOf<String, PurchaseHistoryEntity>()
        
        local.forEach { merged[it.id] = it }
        remote.forEach { remotePurchase ->
            val localPurchase = merged[remotePurchase.id]
            if (localPurchase == null) {
                merged[remotePurchase.id] = remotePurchase
            } else if (remotePurchase.lastUpdated > localPurchase.lastUpdated) {
                merged[remotePurchase.id] = remotePurchase
            }
        }
        
        return merged.values.toList().sortedByDescending { it.purchaseDate }
    }

    private fun PurchaseDto.toEntity(): PurchaseHistoryEntity {
        return PurchaseHistoryEntity(
            id = id,
            productId = productId,
            productName = productName,
            quantity = quantity,
            unitPrice = BigDecimal(unitPrice.toString()),
            totalPrice = BigDecimal(totalPrice.toString()),
            paymentMethod = paymentMethod,
            status = parseStatus(status).name,
            purchaseDate = purchaseDate,
            shippingAddress = shippingAddress,
            shippingCity = shippingCity,
            shippingPostalCode = shippingPostalCode,
            trackingNumber = trackingNumber,
            deliveryDate = deliveryDate,
            lastUpdated = System.currentTimeMillis(),
            syncStatus = SyncState.SYNCED.name
        )
    }

    private fun PurchaseHistoryEntity.toDomain(): PurchaseHistory {
        return PurchaseHistory(
            id = id,
            productId = productId,
            productName = productName,
            quantity = quantity,
            unitPrice = unitPrice,
            totalPrice = totalPrice,
            paymentMethod = try { PaymentMethod.valueOf(paymentMethod) } catch (e: Exception) { PaymentMethod.CREDIT_CARD },
            status = try { PurchaseStatus.valueOf(status) } catch (e: Exception) { PurchaseStatus.PENDING },
            purchaseDate = purchaseDate,
            shippingAddress = shippingAddress,
            shippingCity = shippingCity,
            shippingPostalCode = shippingPostalCode,
            trackingNumber = trackingNumber,
            deliveryDate = deliveryDate
        )
    }

    private fun PurchaseHistory.toEntity(): PurchaseHistoryEntity {
        return PurchaseHistoryEntity(
            id = id,
            productId = productId,
            productName = productName,
            quantity = quantity,
            unitPrice = unitPrice,
            totalPrice = totalPrice,
            paymentMethod = paymentMethod.name,
            status = status.name,
            purchaseDate = purchaseDate,
            shippingAddress = shippingAddress,
            shippingCity = shippingCity,
            shippingPostalCode = shippingPostalCode,
            trackingNumber = trackingNumber,
            deliveryDate = deliveryDate,
            lastUpdated = System.currentTimeMillis(),
            syncStatus = SyncState.SYNCED.name
        )
    }

    private fun PurchaseHistory.toPendingEntity(): PurchaseHistoryEntity {
        return PurchaseHistoryEntity(
            id = id,
            productId = productId,
            productName = productName,
            quantity = quantity,
            unitPrice = unitPrice,
            totalPrice = totalPrice,
            paymentMethod = paymentMethod.name,
            status = status.name,
            purchaseDate = purchaseDate,
            shippingAddress = shippingAddress,
            shippingCity = shippingCity,
            shippingPostalCode = shippingPostalCode,
            trackingNumber = trackingNumber,
            deliveryDate = deliveryDate,
            lastUpdated = System.currentTimeMillis(),
            syncStatus = SyncState.PENDING.name
        )
    }

    private fun PurchaseDto.toDomain(): PurchaseHistory {
        return PurchaseHistory(
            id = id,
            productId = productId,
            productName = productName,
            quantity = quantity,
            unitPrice = BigDecimal(unitPrice.toString()),
            totalPrice = BigDecimal(totalPrice.toString()),
            paymentMethod = try { PaymentMethod.valueOf(paymentMethod) } catch (e: Exception) { PaymentMethod.CREDIT_CARD },
            status = parseStatus(status),
            purchaseDate = purchaseDate,
            shippingAddress = shippingAddress,
            shippingCity = shippingCity,
            shippingPostalCode = shippingPostalCode,
            trackingNumber = trackingNumber,
            deliveryDate = deliveryDate
        )
    }

    private fun PurchaseDto.parseStatus(status: String): PurchaseStatus {
        return try {
            PurchaseStatus.valueOf(status)
        } catch (e: Exception) {
            PurchaseStatus.PENDING
        }
    }
}