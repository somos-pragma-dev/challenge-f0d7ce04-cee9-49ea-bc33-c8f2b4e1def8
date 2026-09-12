package com.example.offlinefirst.domain.model

import java.math.BigDecimal

/**
 * Modelo de dominio para Historial de Compras.
 * Representa la entidad de negocio sin dependencias de infraestructura.
 * Esta clase es inmutable y contiene toda la información relevante de una compra.
 */
data class PurchaseHistory(
    val id: String,
    val userId: String,
    val productId: String,
    val productName: String,
    val productImageUrl: String? = null,
    val quantity: Int,
    val unitPrice: BigDecimal,
    val totalPrice: BigDecimal,
    val currency: String = "USD",
    val status: PurchaseStatus,
    val paymentMethod: String,
    val shippingAddress: String? = null,
    val purchaseDate: Long,
    val deliveryDate: Long? = null,
    val trackingNumber: String? = null,
    val notes: String? = null,
    val createdAt: Long,
    val updatedAt: Long
) {
    /**
     * Valida que la compra tenga datos consistentes.
     * @return true si la compra es válida
     */
    fun isValid(): Boolean {
        return id.isNotBlank() &&
                userId.isNotBlank() &&
                productId.isNotBlank() &&
                quantity > 0 &&
                totalPrice > BigDecimal.ZERO
    }

    /**
     * Verifica si la compra está activa (no cancelada ni reembolsada).
     * @return true si la compra está activa
     */
    fun isActive(): Boolean {
        return status != PurchaseStatus.CANCELLED && 
               status != PurchaseStatus.REFUNDED
    }

    /**
     * Obtiene el precio total formateado con la moneda.
     * @return String con el precio formateado
     */
    fun getFormattedTotalPrice(): String {
        return "$currency %.2f".format(totalPrice)
    }

    /**
     * Obtiene el precio unitario formateado con la moneda.
     * @return String con el precio formateado
     */
    fun getFormattedUnitPrice(): String {
        return "$currency %.2f".format(unitPrice)
    }

    /**
     * Obtiene el estado formateado para display.
     * @return String con el estado traducida
     */
    fun getFormattedStatus(): String {
        return when (status) {
            PurchaseStatus.PENDING -> "Pendiente"
            PurchaseStatus.CONFIRMED -> "Confirmada"
            PurchaseStatus.PROCESSING -> "Procesando"
            PurchaseStatus.SHIPPED -> "Enviada"
            PurchaseStatus.DELIVERED -> "Entregada"
            PurchaseStatus.CANCELLED -> "Cancelada"
            PurchaseStatus.REFUNDED -> "Reembolsada"
            PurchaseStatus.UNKNOWN -> "Desconocido"
        }
    }

    /**
     * Verifica si la compra puede ser cancelada.
     * @return true si se puede cancelar
     */
    fun canBeCancelled(): Boolean {
        return status in listOf(
            PurchaseStatus.PENDING,
            PurchaseStatus.CONFIRMED,
            PurchaseStatus.PROCESSING
        )
    }

    /**
     * Obtiene los días transcurridos desde la compra.
     * @return Número de días
     */
    fun getDaysSincePurchase(): Long {
        val now = System.currentTimeMillis()
        return (now - purchaseDate) / (1000 * 60 * 60 * 24)
    }

    /**
     * Crea una copia con estado actualizado.
     * @param newStatus Nuevo estado
     * @return Nueva instancia de PurchaseHistory
     */
    fun withStatus(newStatus: PurchaseStatus): PurchaseHistory {
        return copy(
            status = newStatus,
            updatedAt = System.currentTimeMillis()
        )
    }

    /**
     * Crea una copia con información de envío actualizada.
     * @param tracking Nuevo número de seguimiento
     * @param deliveryDate Nueva fecha de entrega
     * @return Nueva instancia de PurchaseHistory
     */
    fun withShippingInfo(tracking: String?, deliveryDate: Long?): PurchaseHistory {
        return copy(
            trackingNumber = tracking,
            deliveryDate = deliveryDate,
            updatedAt = System.currentTimeMillis()
        )
    }

    companion object {
        /**
         * Crea una compra de ejemplo para testing.
         */
        fun createSample(): PurchaseHistory {
            val now = System.currentTimeMillis()
            return PurchaseHistory(
                id = "purchase-001",
                userId = "user-001",
                productId = "product-001",
                productName = "Producto de Ejemplo",
                productImageUrl = "https://example.com/image.jpg",
                quantity = 2,
                unitPrice = BigDecimal("49.99"),
                totalPrice = BigDecimal("99.98"),
                currency = "USD",
                status = PurchaseStatus.DELIVERED,
                paymentMethod = "Credit Card",
                shippingAddress = "123 Main St, City, Country",
                purchaseDate = now - (7 * 24 * 60 * 60 * 1000), // hace 7 días
                deliveryDate = now - (3 * 24 * 60 * 60 * 1000), // hace 3 días
                trackingNumber = "TRACK123456",
                notes = "Entrega en horario laboral",
                createdAt = now - (7 * 24 * 60 * 60 * 1000),
                updatedAt = now - (3 * 24 * 60 * 60 * 1000)
            )
        }
    }
}

/**
 * Estados posibles de una compra.
 */
enum class PurchaseStatus {
    PENDING,      // Pendiente de confirmación
    CONFIRMED,    // Confirmada
    PROCESSING,   // Procesando
    SHIPPED,      // Enviada
    DELIVERED,    // Entregada
    CANCELLED,    // Cancelada
    REFUNDED,     // Reembolsada
    UNKNOWN       // Estado desconocido
}

/**
 * Métodos de pago disponibles.
 */
enum class PaymentMethod(val displayName: String) {
    CREDIT_CARD("Tarjeta de Crédito"),
    DEBIT_CARD("Tarjeta de Débito"),
    PAYPAL("PayPal"),
    BANK_TRANSFER("Transferencia Bancaria"),
    CASH_ON_DELIVERY("Contra Reembolso"),
    CRYPTOCURRENCY("Criptomoneda");

    companion object {
        fun fromString(value: String): PaymentMethod {
            return entries.find { 
                it.name.equals(value, ignoreCase = true) ||
                it.displayName.equals(value, ignoreCase = true)
            } ?: CREDIT_CARD
        }
    }
}