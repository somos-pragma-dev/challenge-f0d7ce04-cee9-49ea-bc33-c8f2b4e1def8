package com.example.offlinefirst.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.offlinefirst.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM products ORDER BY createdAt DESC")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE id = :productId")
    fun getProductById(productId: String): Flow<ProductEntity?>

    @Query("SELECT * FROM products WHERE id = :productId")
    suspend fun getProductByIdSync(productId: String): ProductEntity?

    @Query("SELECT * FROM products WHERE category = :category ORDER BY createdAt DESC")
    fun getProductsByCategory(category: String): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE name LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%' ORDER BY createdAt DESC")
    fun searchProducts(query: String): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE isAvailable = 1 AND stock > 0 ORDER BY createdAt DESC")
    fun getAvailableProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE syncStatus = 'PENDING' OR syncStatus = 'FAILED'")
    suspend fun getProductsToSync(): List<ProductEntity>

    @Query("SELECT * FROM products WHERE updatedAt > :lastSyncTimestamp")
    suspend fun getProductsUpdatedSince(lastSyncTimestamp: Long): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)

    @Update
    suspend fun updateProduct(product: ProductEntity)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    @Query("DELETE FROM products WHERE id = :productId")
    suspend fun deleteProductById(productId: String)

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()

    @Query("UPDATE products SET syncStatus = :syncStatus WHERE id = :productId")
    suspend fun updateSyncStatus(productId: String, syncStatus: String)

    @Query("UPDATE products SET stock = :newStock, updatedAt = :updatedAt WHERE id = :productId")
    suspend fun updateStock(productId: String, newStock: Int, updatedAt: Long)

    @Query("UPDATE products SET isAvailable = :isAvailable, updatedAt = :updatedAt WHERE id = :productId")
    suspend fun updateAvailability(productId: String, isAvailable: Boolean, updatedAt: Long)

    @Query("SELECT COUNT(*) FROM products")
    fun getProductCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM products WHERE isAvailable = 1 AND stock > 0")
    fun getAvailableProductCount(): Flow<Int>

    @Query("SELECT * FROM products WHERE price BETWEEN :minPrice AND :maxPrice ORDER BY price ASC")
    fun getProductsByPriceRange(minPrice: Double, maxPrice: Double): Flow<List<ProductEntity>>

    @Query("SELECT DISTINCT category FROM products ORDER BY category ASC")
    fun getAllCategories(): Flow<List<String>>
}