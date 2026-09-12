package com.example.offlinefirst.domain.repository

import com.example.offlinefirst.domain.model.PaymentMethod
import com.example.offlinefirst.domain.model.ProductCategory
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.ThemeMode
import com.example.offlinefirst.domain.model.UserPreferences
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    fun getUserPreferences(userId: String): Flow<Resource<UserPreferences>>
    
    fun getDefaultPreferences(userId: String): UserPreferences
    
    suspend fun savePreferences(preferences: UserPreferences): Resource<UserPreferences>
    
    suspend fun updateTheme(userId: String, theme: ThemeMode): Resource<UserPreferences>
    
    suspend fun updateNotifications(
        userId: String,
        enabled: Boolean,
        priceDrops: Boolean? = null,
        newProducts: Boolean? = null
    ): Resource<UserPreferences>
    
    suspend fun updateAutoSync(
        userId: String,
        enabled: Boolean,
        wifiOnly: Boolean? = null
    ): Resource<UserPreferences>
    
    suspend fun updateFavoriteCategories(
        userId: String,
        categories: List<ProductCategory>
    ): Resource<UserPreferences>
    
    suspend fun updatePreferredPaymentMethod(
        userId: String,
        paymentMethod: PaymentMethod?
    ): Resource<UserPreferences>
    
    suspend fun updateSyncRetention(
        userId: String,
        retentionDays: Int,
        maxProducts: Int
    ): Resource<UserPreferences>
    
    suspend fun updateLastSyncTimestamp(userId: String, timestamp: Long): Resource<UserPreferences>
    
    suspend fun deletePreferences(userId: String): Resource<Unit>
    
    fun getSyncStatus(): Flow<Resource<SyncStatus>>
    
    suspend fun syncPreferences(): Resource<UserPreferences>
    
    fun observePreferences(userId: String): Flow<UserPreferences?>
    
    suspend fun hasPreferences(userId: String): Boolean
}