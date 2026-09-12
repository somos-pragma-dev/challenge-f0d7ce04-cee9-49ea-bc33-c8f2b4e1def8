package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.offlinefirst.domain.model.Product
import com.example.offlinefirst.domain.model.ProductCategory
import java.math.BigDecimal

@Entity(
    tableName = "products",
    indices = [
        Index(value = ["category"]),
        Index(value = ["is_available"]),
        Index(value = ["last_synced_at"])
    ]
)
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "price")
    val price: Long,

    @ColumnInfo(name = "original_price")
    val originalPrice: Long?,

    @ColumnInfo(name = "stock")
    val stock: Int,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "rating")
    val rating: Float,

    @ColumnInfo(name = "rating_count")
    val ratingCount: Int,

    @ColumnInfo(name = "image_url")
    val imageUrl: String?,

    @ColumnInfo(name = "is_available")
    val isAvailable: Boolean,

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean,

    @ColumnInfo(name = "created_at")
    val createdAt: Long,

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long,

    @ColumnInfo(name = "last_synced_at")
    val lastSyncedAt: Long,

    @ColumnInfo(name = "sync_status")
    val syncStatus: Int,

    @ColumnInfo(name = "version")
    val version: Int
) {
    fun toDomain(): Product {
        return Product(
            id = id,
            name = name,
            description = description,
            price = BigDecimal(price).divide(BigDecimal(100)),
            originalPrice = originalPrice?.let { BigDecimal(it).divide(BigDecimal(100)) },
            stock = stock,
            category = try {
                ProductCategory.valueOf(category)
            } catch (e: Exception) {
                ProductCategory.OTHER
            },
            rating = rating,
            ratingCount = ratingCount,
            imageUrl = imageUrl,
            isAvailable = isAvailable,
            isFavorite = isFavorite,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    companion object {
        fun fromDomain(product: Product, syncStatus: Int = 0, version: Int = 1): ProductEntity {
            return ProductEntity(
                id = product.id,
                name = product.name,
                description = product.description,
                price = product.price.multiply(BigDecimal(100)).toLong(),
                originalPrice = product.originalPrice?.multiply(BigDecimal(100))?.toLong(),
                stock = product.stock,
                category = product.category.name,
                rating = product.rating,
                ratingCount = product.ratingCount,
                imageUrl = product.imageUrl,
                isAvailable = product.isAvailable,
                isFavorite = product.isFavorite,
                createdAt = product.createdAt,
                updatedAt = product.updatedAt,
                lastSyncedAt = System.currentTimeMillis(),
                syncStatus = syncStatus,
                version = version
            )
        }

        const val SYNC_STATUS_SYNCED = 0
        const val SYNC_STATUS_PENDING_CREATE = 1
        const val SYNC_STATUS_PENDING_UPDATE = 2
        const val SYNC_STATUS_PENDING_DELETE = 3
        const val SYNC_STATUS_CONFLICT = 4
    }
}