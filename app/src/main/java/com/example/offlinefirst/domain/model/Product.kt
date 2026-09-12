package com.example.offlinefirst.domain.model

import java.math.BigDecimal

/**
 * Modelo de dominio para Producto.
 * Representa la entidad de negocio sin dependencias de infraestructura.
 * Esta clase es inmutable y contiene toda la información relevante del producto.
 */
data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val currency: String = "USD",
    val category: String,
    val imageUrl: String? = null,
    val stock: Int,
    val rating: Float = 0f,
    val reviewCount: Int = 0,
    val isAvailable: Boolean = true,
    val createdAt: Long,
    val updatedAt: Long
) {
    /**
     * Valida que el producto tenga datos consistentes.
     * @return true si el producto es válido
     */
    fun isValid(): Boolean {
        return id.isNotBlank() &&
                name.isNotBlank() &&
                price > BigDecimal.ZERO &&
                stock >= 0 &&
                rating in 0f..5f
    }

    /**
     * Verifica si hay stock disponible para la cantidad solicitada.
     * @param quantity Cantidad deseada
     * @return true si hay suficiente stock
     */
    fun hasStock(quantity: Int): Boolean {
        return isAvailable && stock >= quantity && quantity > 0
    }

    /**
     * Obtiene el precio formateado con la moneda.
     * @return String con el precio formateado
     */
    fun getFormattedPrice(): String {
        return "$currency %.2f".format(price)
    }

    /**
     * Obtiene el rating formateado como texto.
     * @return String con el rating y cantidad de reseñas
     */
    fun getFormattedRating(): String {
        return "%.1f (%d reseñas)".format(rating, reviewCount)
    }

    /**
     * Crea una copia con precio actualizado.
     * @param newPrice Nuevo precio
     * @return Nueva instancia de Product
     */
    fun withPrice(newPrice: BigDecimal): Product {
        return copy(
            price = newPrice,
            updatedAt = System.currentTimeMillis()
        )
    }

    /**
     * Crea una copia con stock actualizado.
     * @param newStock Nuevo stock
     * @return Nueva instancia de Product
     */
    fun withStock(newStock: Int): Product {
        return copy(
            stock = newStock,
            isAvailable = newStock > 0,
            updatedAt = System.currentTimeMillis()
        )
    }

    /**
     * Crea una copia del producto con disponibilidad modificada.
     * @param available Nueva disponibilidad
     * @return Nueva instancia de Product
     */
    fun withAvailability(available: Boolean): Product {
        return copy(
            isAvailable = available,
            updatedAt = System.currentTimeMillis()
        )
    }

    companion object {
        /**
         * Crea un producto de ejemplo para testing.
         */
        fun createSample(): Product {
            val now = System.currentTimeMillis()
            return Product(
                id = "sample-001",
                name = "Producto de Ejemplo",
                description = "Este es un producto de ejemplo para propósitos de prueba",
                price = BigDecimal("99.99"),
                currency = "USD",
                category = "Electronics",
                imageUrl = "https://example.com/image.jpg",
                stock = 50,
                rating = 4.5f,
                reviewCount = 120,
                isAvailable = true,
                createdAt = now,
                updatedAt = now
            )
        }
    }
}

/**
 * Representa una categoría de productos.
 */
enum class ProductCategory(val displayName: String) {
    ELECTRONICS("Electrónicos"),
    CLOTHING("Ropa"),
    BOOKS("Libros"),
    HOME("Hogar"),
    SPORTS("Deportes"),
    FOOD("Alimentos"),
    OTHER("Otros");

    companion object {
        fun fromString(value: String): ProductCategory {
            return entries.find { 
                it.name.equals(value, ignoreCase = true) || 
                it.displayName.equals(value, ignoreCase = true) 
            } ?: OTHER
        }
    }
}