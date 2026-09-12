package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.offlinefirst.domain.model.PurchaseHistory
import com.example.offlinefirst.domain.model.PaymentMethod
import com.example.offlinefirst.domain.model.PurchaseStatus
import java.math.BigDecimal

@Entity(tableName = "purchase_history")
data class PurchaseHistoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "product_id")
    val productId: String,

    @ColumnInfo(name = "product_name")
    val productName: String,

    @ColumnInfo(name = "quantity")
    val quantity: Int,

    @ColumnInfo(name = "unit_price")
    val unitPrice: BigDecimal,

    @ColumnInfo(name = "total_price")
    val totalPrice: BigDecimal,

    @ColumnInfo(name = "payment_method")
    val paymentMethod: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "purchase_date")
    val purchaseDate: Long,

    @ColumnInfo(name = "shipping_address")
    val shippingAddress: String?,

    @ColumnInfo(name = "shipping_city")
    val shippingCity: String?,

    @ColumnInfo(name = "shipping_postal_code")
    val shippingPostalCode: String?,

    @ColumnInfo(name = "tracking_number")
    val trackingNumber: String?,

    @ColumnInfo(name = "delivery_date")
    val deliveryDate: Long?,

    @ColumnInfo(name = "last_updated")
    val lastUpdated: Long,

    @ColumnInfo(name = "sync_status")
    val syncStatus: String
) {
    fun toDomain(): PurchaseHistory {
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
}

data class PurchaseHistoryWithProductEntity(
    @ColumnInfo(name = "purchase")
    val purchase: PurchaseHistoryEntity,
    @ColumnInfo(name = "product")
    val product: ProductEntity?
)