package com.example.offlinefirst.domain.usecase

import com.example.offlinefirst.domain.model.Product
import com.example.offlinefirst.domain.repository.ProductRepository
import com.example.offlinefirst.util.Error
import com.example.offlinefirst.util.Loading
import com.example.offlinefirst.util.Resource
import com.example.offlinefirst.util.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(forceRefresh: Boolean = false): Flow<Resource<List<Product>>> = flow {
        emit(Loading)
        try {
            val cachedProducts = productRepository.getProducts().first()
            if (cachedProducts.isNotEmpty() && !forceRefresh) {
                emit(Success(cachedProducts))
            }
            if (forceRefresh || cachedProducts.isEmpty()) {
                val remoteProducts = productRepository.syncProducts()
                emit(Success(remoteProducts))
            } else {
                emit(Success(cachedProducts))
            }
        } catch (e: Exception) {
            val cachedProducts = productRepository.getProducts().first()
            if (cachedProducts.isNotEmpty()) {
                emit(Success(cachedProducts))
            } else {
                emit(Error(e.message ?: "Error al obtener productos", e))
            }
        }
    }

    fun getProductsFromCache(): Flow<List<Product>> = productRepository.getProducts()

    fun observeProducts(): Flow<List<Product>> = productRepository.getProducts()
}

class GetProductByIdUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(productId: String): Resource<Product> {
        return try {
            val product = productRepository.getProductById(productId)
            if (product != null) {
                Success(product)
            } else {
                Error("Producto no encontrado")
            }
        } catch (e: Exception) {
            Error(e.message ?: "Error al obtener producto", e)
        }
    }
}

class SaveProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(product: Product): Resource<Product> {
        return try {
            if (!product.isValid()) {
                return Error("Producto inválido")
            }
            val savedProduct = productRepository.saveProduct(product)
            Success(savedProduct)
        } catch (e: Exception) {
            Error(e.message ?: "Error al guardar producto", e)
        }
    }

    suspend fun saveProducts(products: List<Product>): Resource<Int> {
        return try {
            var savedCount = 0
            products.forEach { product ->
                if (product.isValid()) {
                    productRepository.saveProduct(product)
                    savedCount++
                }
            }
            Success(savedCount)
        } catch (e: Exception) {
            Error(e.message ?: "Error al guardar productos", e)
        }
    }
}

class DeleteProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(productId: String): Resource<Unit> {
        return try {
            productRepository.deleteProduct(productId)
            Success(Unit)
        } catch (e: Exception) {
            Error(e.message ?: "Error al eliminar producto", e)
        }
    }
}

class SearchProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(query: String): Resource<List<Product>> {
        return try {
            val products = productRepository.searchProducts(query)
            Success(products)
        } catch (e: Exception) {
            Error(e.message ?: "Error al buscar productos", e)
        }
    }

    fun searchProductsByCategory(category: String): Flow<List<Product>> {
        return productRepository.getProductsByCategory(category)
    }
}

class SyncProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): Resource<List<Product>> {
        return try {
            val products = productRepository.syncProducts()
            Success(products)
        } catch (e: Exception) {
            Error(e.message ?: "Error al sincronizar productos", e)
        }
    }

    fun observeSyncStatus(): Flow<Boolean> = productRepository.getPendingSyncProducts().let { flow ->
        flow.map { it.isNotEmpty() }
    }
}


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