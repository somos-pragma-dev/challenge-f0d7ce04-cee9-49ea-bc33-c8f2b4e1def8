package com.example.offlinefirst.data.remote.dto

import com.example.offlinefirst.domain.model.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * DTO para la respuesta de la API de productos.
 * Representa la estructura de datos que viene del servidor remoto.
 */
@Serializable
data class ProductDto(
    @SerialName("id")
    val id: String,
    
    @SerialName("name")
    val name: String,
    
    @SerialName("description")
    val description: String,
    
    @SerialName("price")
    val price: Double,
    
    @SerialName("currency")
    val currency: String = "USD",
    
    @SerialName("category")
    val category: String,
    
    @SerialName("imageUrl")
    val imageUrl: String? = null,
    
    @SerialName("stock")
    val stock: Int,
    
    @SerialName("rating")
    val rating: Float = 0f,
    
    @SerialName("reviewCount")
    val reviewCount: Int = 0,
    
    @SerialName("isAvailable")
    val isAvailable: Boolean = true,
    
    @SerialName("createdAt")
    val createdAt: Long,
    
    @SerialName("updatedAt")
    val updatedAt: Long
) {
    /**
     * Convierte el DTO al modelo de dominio.
     */
    fun toDomain(): Product {
        return Product(
            id = id,
            name = name,
            description = description,
            price = BigDecimal.valueOf(price),
            currency = currency,
            category = category,
            imageUrl = imageUrl,
            stock = stock,
            rating = rating,
            reviewCount = reviewCount,
            isAvailable = isAvailable,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    companion object {
        /**
         * Crea un DTO desde el modelo de dominio.
         */
        fun fromDomain(product: Product): ProductDto {
            return ProductDto(
                id = product.id,
                name = product.name,
                description = product.description,
                price = product.price.toDouble(),
                currency = product.currency,
                category = product.category,
                imageUrl = product.imageUrl,
                stock = product.stock,
                rating = product.rating,
                reviewCount = product.reviewCount,
                isAvailable = product.isAvailable,
                createdAt = product.createdAt,
                updatedAt = product.updatedAt
            )
        }
    }
}

/**
 * DTO para paginación de productos.
 */
@Serializable
data class ProductListResponse(
    @SerialName("products")
    val products: List<ProductDto>,
    
    @SerialName("total")
    val total: Int,
    
    @SerialName("page")
    val page: Int,
    
    @SerialName("pageSize")
    val pageSize: Int,
    
    @SerialName("hasMore")
    val hasMore: Boolean
)

/**
 * DTO para crear un nuevo producto (solo campos editables).
 */
@Serializable
data class CreateProductRequest(
    @SerialName("name")
    val name: String,
    
    @SerialName("description")
    val description: String,
    
    @SerialName("price")
    val price: Double,
    
    @SerialName("currency")
    val currency: String = "USD",
    
    @SerialName("category")
    val category: String,
    
    @SerialName("imageUrl")
    val imageUrl: String? = null,
    
    @SerialName("stock")
    val stock: Int
)

/**
 * DTO para actualizar un producto existente.
 */
@Serializable
data class UpdateProductRequest(
    @SerialName("name")
    val name: String? = null,
    
    @SerialName("description")
    val description: String? = null,
    
    @SerialName("price")
    val price: Double? = null,
    
    @SerialName("category")
    val category: String? = null,
    
    @SerialName("imageUrl")
    val imageUrl: String? = null,
    
    @SerialName("stock")
    val stock: Int? = null,
    
    @SerialName("isAvailable")
    val isAvailable: Boolean? = null
)