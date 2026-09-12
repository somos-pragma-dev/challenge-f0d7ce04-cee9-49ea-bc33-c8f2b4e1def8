package com.example.offlinefirst.data.sync

import com.example.offlinefirst.domain.model.Product
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.SyncState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConflictResolver @Inject constructor() {

    fun resolveProductConflict(localProduct: Product): Product {
        return localProduct
    }

    fun resolveProductConflict(local: Product, remote: Product): Product {
        return if (local.lastUpdated > remote.lastUpdated) local else remote
    }

    fun hasConflict(local: Product, remote: Product): Boolean {
        return local.lastUpdated != remote.lastUpdated
    }

    fun mergeProducts(local: Product, remote: Product): Product {
        return remote.copy(
            name = local.name.ifEmpty { remote.name },
            description = local.description.ifEmpty { remote.description },
            stock = if (local.stock != remote.stock) maxOf(local.stock, remote.stock) else local.stock
        )
    }
}