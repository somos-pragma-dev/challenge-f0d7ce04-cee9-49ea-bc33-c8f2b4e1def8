package com.example.offlinefirst.domain.model

import java.util.UUID

data class UserPreferences(
    val id: String = UUID.randomUUID().toString(),
    val userId: String,
    val theme: ThemeMode = ThemeMode.SYSTEM,
    val notificationsEnabled: Boolean = true,
    val autoSyncEnabled: Boolean = true,
    val lastSyncTimestamp: Long = 0L,
    val preferredPaymentMethod: PaymentMethod? = null,
    val favoriteCategories: List<ProductCategory> = emptyList(),
    val notificationsForPriceDrops: Boolean = true,
    val notificationsForNewProducts: Boolean = true,
    val offlineDataRetentionDays: Int = 30,
    val maxOfflineProducts: Int = 500,
    val syncOnWifiOnly: Boolean = false,
    val language: String = "es",
    val currency: String = "EUR",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    fun isValid(): Boolean {
        return userId.isNotBlank() &&
                offlineDataRetentionDays in 1..365 &&
                maxOfflineProducts in 10..10000 &&
                language.isNotBlank() &&
                currency.isNotBlank()
    }

    fun requiresSync(): Boolean {
        val timeSinceLastSync = System.currentTimeMillis() - lastSyncTimestamp
        val twentyFourHours = 24 * 60 * 60 * 1000L
        return autoSyncEnabled && (lastSyncTimestamp == 0L || timeSinceLastSync > twentyFourHours)
    }

    fun canSaveOfflineData(): Boolean {
        return offlineDataRetentionDays > 0 && maxOfflineProducts > 0
    }

    fun getCategoriesForFiltering(): List<ProductCategory> {
        return if (favoriteCategories.isEmpty()) ProductCategory.entries else favoriteCategories
    }

    fun isCategoryFavorite(category: ProductCategory): Boolean {
        return favoriteCategories.contains(category)
    }

    fun withTheme(newTheme: ThemeMode): UserPreferences {
        return copy(theme = newTheme, updatedAt = System.currentTimeMillis())
    }

    fun withNotifications(enabled: Boolean): UserPreferences {
        return copy(notificationsEnabled = enabled, updatedAt = System.currentTimeMillis())
    }

    fun withAutoSync(enabled: Boolean): UserPreferences {
        return copy(autoSyncEnabled = enabled, updatedAt = System.currentTimeMillis())
    }

    fun withLastSyncTimestamp(timestamp: Long): UserPreferences {
        return copy(lastSyncTimestamp = timestamp, updatedAt = System.currentTimeMillis())
    }

    fun withFavoriteCategories(categories: List<ProductCategory>): UserPreferences {
        return copy(favoriteCategories = categories, updatedAt = System.currentTimeMillis())
    }

    fun withPreferredPaymentMethod(method: PaymentMethod?): UserPreferences {
        return copy(preferredPaymentMethod = method, updatedAt = System.currentTimeMillis())
    }

    fun addFavoriteCategory(category: ProductCategory): UserPreferences {
        if (favoriteCategories.contains(category)) return this
        return copy(
            favoriteCategories = favoriteCategories + category,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun removeFavoriteCategory(category: ProductCategory): UserPreferences {
        return copy(
            favoriteCategories = favoriteCategories.filter { it != category },
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withSyncSettings(
        syncOnWifiOnly: Boolean,
        offlineDataRetentionDays: Int,
        maxOfflineProducts: Int
    ): UserPreferences {
        return copy(
            syncOnWifiOnly = syncOnWifiOnly,
            offlineDataRetentionDays = offlineDataRetentionDays.coerceIn(1, 365),
            maxOfflineProducts = maxOfflineProducts.coerceIn(10, 10000),
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withNotificationPreferences(
        priceDrops: Boolean,
        newProducts: Boolean
    ): UserPreferences {
        return copy(
            notificationsForPriceDrops = priceDrops,
            notificationsForNewProducts = newProducts,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun getNotificationSettings(): Map<String, Boolean> {
        return mapOf(
            "general" to notificationsEnabled,
            "priceDrops" to notificationsForPriceDrops,
            "newProducts" to notificationsForNewProducts
        )
    }

    fun getSyncConfiguration(): SyncConfiguration {
        return SyncConfiguration(
            autoSyncEnabled = autoSyncEnabled,
            syncOnWifiOnly = syncOnWifiOnly,
            lastSyncTimestamp = lastSyncTimestamp,
            offlineDataRetentionDays = offlineDataRetentionDays,
            maxOfflineProducts = maxOfflineProducts
        )
    }
}

enum class ThemeMode(val displayName: String, val value: Int) {
    LIGHT("Claro", 0),
    DARK("Oscuro", 1),
    SYSTEM("Sistema", 2);

    companion object {
        fun fromValue(value: Int): ThemeMode {
            return entries.find { it.value == value } ?: SYSTEM
        }
    }
}

data class SyncConfiguration(
    val autoSyncEnabled: Boolean,
    val syncOnWifiOnly: Boolean,
    val lastSyncTimestamp: Long,
    val offlineDataRetentionDays: Int,
    val maxOfflineProducts: Int
)