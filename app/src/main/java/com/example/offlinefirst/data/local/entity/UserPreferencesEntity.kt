package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.offlinefirst.domain.model.UserPreferences

@Entity(tableName = "user_preferences")
data class UserPreferencesEntity(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val userId: String,

    @ColumnInfo(name = "user_name")
    val userName: String,

    @ColumnInfo(name = "user_email")
    val userEmail: String,

    @ColumnInfo(name = "notifications_enabled")
    val notificationsEnabled: Boolean,

    @ColumnInfo(name = "dark_mode_enabled")
    val darkModeEnabled: Boolean,

    @ColumnInfo(name = "auto_sync_enabled")
    val autoSyncEnabled: Boolean,

    @ColumnInfo(name = "sync_on_wifi_only")
    val syncOnWifiOnly: Boolean,

    @ColumnInfo(name = "last_sync_time")
    val lastSyncTime: Long,

    @ColumnInfo(name = "selected_currency")
    val selectedCurrency: String,

    @ColumnInfo(name = "selected_language")
    val selectedLanguage: String
) {
    fun toDomain(): UserPreferences {
        return UserPreferences(
            userId = userId,
            userName = userName,
            userEmail = userEmail,
            notificationsEnabled = notificationsEnabled,
            darkModeEnabled = darkModeEnabled,
            autoSyncEnabled = autoSyncEnabled,
            syncOnWifiOnly = syncOnWifiOnly,
            lastSyncTime = lastSyncTime,
            selectedCurrency = selectedCurrency,
            selectedLanguage = selectedLanguage
        )
    }
}

data class ShippingAddressJson(
    val street: String,
    val city: String,
    val state: String,
    val postalCode: String,
    val country: String
) {
    fun toDomain(): UserPreferences.ShippingAddress {
        return UserPreferences.ShippingAddress(
            street = street,
            city = city,
            state = state,
            postalCode = postalCode,
            country = country
        )
    }
}