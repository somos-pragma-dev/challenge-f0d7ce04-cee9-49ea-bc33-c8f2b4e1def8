package com.example.offlinefirst.data.local.entity

import java.math.BigDecimal

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        price = this.price,
        stock = this.stock,
        category = this.category.name,
        imageUrl = this.imageUrl ?: "",
        isAvailable = this.isAvailable,
        rating = this.rating,
        lastUpdated = this.lastUpdated,
        syncStatus = SyncState.SYNCED.name
    )
}

fun Product.toPendingEntity(): ProductEntity {
    return ProductEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        price = this.price,
        stock = this.stock,
        category = this.category.name,
        imageUrl = this.imageUrl ?: "",
        isAvailable = this.isAvailable,
        rating = this.rating,
        lastUpdated = System.currentTimeMillis(),
        syncStatus = SyncState.PENDING.name
    )
}