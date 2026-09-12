package com.example.offlinefirst.data.remote.dto

import com.example.offlinefirst.domain.model.PurchaseHistory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * DTO para la respuesta de la API de historial de compras.
 * Representa la estructura de datos que viene del servidor remoto.
 */
@Serializable
data class PurchaseDto(
    @SerialName("id")
    val id: String,
    
    @SerialName("userId")
    val userId: String,
    
    @SerialName("productId")
    val productId: String,
    
    @SerialName("productName")
    val productName: String,
    
    @SerialName("productImageUrl")
    val productImageUrl: String? = null,
    
    @SerialName("quantity")
    val quantity: Int,
    
    @SerialName("unitPrice")
    val unitPrice: Double,
    
    @SerialName("totalPrice")
    val totalPrice: Double,
    
    @SerialName("currency")
    val currency: String = "USD",
    
    @SerialName("status")
    val status: String,
    
    @SerialName("paymentMethod")
    val paymentMethod: String,
    
    @SerialName("shippingAddress")
    val shippingAddress: String? = null,
    
    @SerialName("purchaseDate")
    val purchaseDate: Long,
    
    @SerialName("deliveryDate")
    val deliveryDate: Long? = null,
    
    @SerialName("trackingNumber")
    val trackingNumber: String? = null,
    
    @SerialName("notes")
    val notes: String? = null,
    
    @SerialName("createdAt")
    val createdAt: Long,
    
    @SerialName("updatedAt")
    val updatedAt: Long
) {
    /**
     * Convierte el DTO al modelo de dominio.
     */
    fun toDomain(): PurchaseHistory {
        return PurchaseHistory(
            id = id,
            userId = userId,
            productId = productId,
            productName = productName,
            productImageUrl = productImageUrl,
            quantity = quantity,
            unitPrice = BigDecimal.valueOf(unitPrice),
            totalPrice = BigDecimal.valueOf(totalPrice),
            currency = currency,
            status = parseStatus(status),
            paymentMethod = paymentMethod,
            shippingAddress = shippingAddress,
            purchaseDate = purchaseDate,
            deliveryDate = deliveryDate,
            trackingNumber = trackingNumber,
            notes = notes,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun parseStatus(status: String): PurchaseHistory.PurchaseStatus {
        return when (status.uppercase()) {
            "PENDING" -> PurchaseHistory.PurchaseStatus.PENDING
            "CONFIRMED" -> PurchaseHistory.PurchaseStatus.CONFIRMED
            "PROCESSING" -> PurchaseHistory.PurchaseStatus.PROCESSING
            "SHIPPED" -> PurchaseHistory.PurchaseStatus.SHIPPED
            "DELIVERED" -> PurchaseHistory.PurchaseStatus.DELIVERED
            "CANCELLED" -> PurchaseHistory.PurchaseStatus.CANCELLED
            "REFUNDED" -> PurchaseHistory.PurchaseStatus.REFUNDED
            else -> PurchaseHistory.PurchaseStatus.UNKNOWN
        }
    }

    companion object {
        /**
         * Crea un DTO desde el modelo de dominio.
         */
        fun fromDomain(purchase: PurchaseHistory): PurchaseDto {
            return PurchaseDto(
                id = purchase.id,
                userId = purchase.userId,
                productId = purchase.productId,
                productName = purchase.productName,
                productImageUrl = purchase.productImageUrl,
                quantity = purchase.quantity,
                unitPrice = purchase.unitPrice.toDouble(),
                totalPrice = purchase.totalPrice.toDouble(),
                currency = purchase.currency,
                status = purchase.status.name,
                paymentMethod = purchase.paymentMethod,
                shippingAddress = purchase.shippingAddress,
                purchaseDate = purchase.purchaseDate,
                deliveryDate = purchase.deliveryDate,
                trackingNumber = purchase.trackingNumber,
                notes = purchase.notes,
                createdAt = purchase.createdAt,
                updatedAt = purchase.updatedAt
            )
        }
    }
}

/**
 * DTO para paginación de compras.
 */
@Serializable
data class PurchaseListResponse(
    @SerialName("purchases")
    val purchases: List<PurchaseDto>,
    
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
 * DTO para crear una nueva compra.
 */
@Serializable
data class CreatePurchaseRequest(
    @SerialName("productId")
    val productId: String,
    
    @SerialName("quantity")
    val quantity: Int,
    
    @SerialName("paymentMethod")
    val paymentMethod: String,
    
    @SerialName("shippingAddress")
    val shippingAddress: String? = null,
    
    @SerialName("notes")
    val notes: String? = null
)

/**
 * DTO para respuesta de compra exitosa.
 */
@Serializable
data class PurchaseResponse(
    @SerialName("purchase")
    val purchase: PurchaseDto,
    
    @SerialName("message")
    val message: String,
    
    @SerialName("confirmationCode")
    val confirmationCode: String
)