package com.example.offlinefirst.data.remote.api

import com.example.offlinefirst.data.remote.dto.CreateProductRequest
import com.example.offlinefirst.data.remote.dto.CreatePurchaseRequest
import com.example.offlinefirst.data.remote.dto.ProductDto
import com.example.offlinefirst.data.remote.dto.ProductListResponse
import com.example.offlinefirst.data.remote.dto.PurchaseDto
import com.example.offlinefirst.data.remote.dto.PurchaseListResponse
import com.example.offlinefirst.data.remote.dto.PurchaseResponse
import com.example.offlinefirst.data.remote.dto.UpdateProductRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface OfflineFirstApiService {

    // Endpoints de Productos
    @GET("api/v1/products")
    suspend fun getProducts(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20,
        @Query("category") category: String? = null
    ): Response<ProductListResponse>

    @GET("api/v1/products/{id}")
    suspend fun getProductById(@Path("id") productId: String): Response<ProductDto>

    @POST("api/v1/products")
    suspend fun createProduct(@Body request: CreateProductRequest): Response<ProductDto>

    @PUT("api/v1/products/{id}")
    suspend fun updateProduct(
        @Path("id") productId: String,
        @Body request: UpdateProductRequest
    ): Response<ProductDto>

    @DELETE("api/v1/products/{id}")
    suspend fun deleteProduct(@Path("id") productId: String): Response<Unit>

    @GET("api/v1/products/search")
    suspend fun searchProducts(@Query("q") query: String): Response<ProductListResponse>

    // Endpoints de Compras/Historial
    @GET("api/v1/purchases")
    suspend fun getPurchases(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20,
        @Query("status") status: String? = null
    ): Response<PurchaseListResponse>

    @GET("api/v1/purchases/{id}")
    suspend fun getPurchaseById(@Path("id") purchaseId: String): Response<PurchaseDto>

    @POST("api/v1/purchases")
    suspend fun createPurchase(@Body request: CreatePurchaseRequest): Response<PurchaseResponse>

    @PUT("api/v1/purchases/{id}/cancel")
    suspend fun cancelPurchase(@Path("id") purchaseId: String): Response<PurchaseDto>

    @PUT("api/v1/purchases/{id}/status")
    suspend fun updatePurchaseStatus(
        @Path("id") purchaseId: String,
        @Query("status") status: String
    ): Response<PurchaseDto>

    // Endpoints de Sincronización
    @POST("api/v1/sync/products")
    suspend fun syncProducts(@Body products: List<ProductDto>): Response<ProductListResponse>

    @POST("api/v1/sync/purchases")
    suspend fun syncPurchases(@Body purchases: List<PurchaseDto>): Response<PurchaseListResponse>

    @GET("api/v1/sync/last-modified")
    suspend fun getLastModifiedTimestamp(): Response<SyncTimestampResponse>

    @POST("api/v1/sync/resolve-conflict")
    suspend fun resolveConflict(@Body conflictData: ConflictResolutionRequest): Response<ConflictResolutionResponse>

    // Endpoints de Preferencias de Usuario
    @GET("api/v1/user/preferences")
    suspend fun getUserPreferences(): Response<UserPreferencesResponse>

    @PUT("api/v1/user/preferences")
    suspend fun updateUserPreferences(@Body preferences: UserPreferencesDto): Response<UserPreferencesResponse>
}

data class SyncTimestampResponse(
    val timestamp: Long,
    val serverVersion: String
)

data class ConflictResolutionRequest(
    val entityType: String,
    val entityId: String,
    val localVersion: String,
    val serverVersion: String,
    val resolutionStrategy: String
)

data class ConflictResolutionResponse(
    val success: Boolean,
    val resolvedEntity: String,
    val finalVersion: String
)

data class UserPreferencesResponse(
    val themeMode: String,
    val notificationsEnabled: Boolean,
    val autoSyncEnabled: Boolean,
    val syncOnWifiOnly: Boolean,
    val defaultPaymentMethod: String,
    val language: String,
    val lastUpdated: Long
)

data class UserPreferencesDto(
    val themeMode: String,
    val notificationsEnabled: Boolean,
    val autoSyncEnabled: Boolean,
    val syncOnWifiOnly: Boolean,
    val defaultPaymentMethod: String,
    val language: String
)