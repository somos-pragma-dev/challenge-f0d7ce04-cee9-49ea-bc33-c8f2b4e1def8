package com.example.offlinefirst.domain.repository

import com.example.offlinefirst.domain.model.Product
import com.example.offlinefirst.domain.model.ProductCategory
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<Resource<List<Product>>>
    
    fun getProductById(id: String): Flow<Resource<Product>>
    
    fun getProductsByCategory(category: ProductCategory): Flow<Resource<List<Product>>>
    
    fun searchProducts(query: String): Flow<Resource<List<Product>>>
    
    suspend fun saveProduct(product: Product): Resource<Product>
    
    suspend fun saveProducts(products: List<Product>): Resource<List<Product>>
    
    suspend fun deleteProduct(id: String): Resource<Unit>
    
    suspend fun deleteAllProducts(): Resource<Unit>
    
    fun getSyncStatusForProduct(id: String): Flow<Resource<SyncStatus>>
    
    fun getPendingSyncProducts(): Flow<Resource<List<Product>>>
    
    suspend fun syncProducts(): Resource<List<Product>>
    
    suspend fun getProductCount(): Int
    
    suspend fun getProductsOlderThan(timestamp: Long): List<Product>
    
    suspend fun updateProductStock(productId: String, newStock: Int): Resource<Product>
    
    fun observeProducts(): Flow<List<Product>>
    
    fun observeProductById(id: String): Flow<Product?>
}