package com.example.offlinefirst.data.remote

import com.example.offlinefirst.data.remote.dto.ContentDto
import com.example.offlinefirst.data.remote.dto.PreferencesDto
import com.example.offlinefirst.data.remote.dto.SyncRequestDto
import com.example.offlinefirst.data.remote.dto.SyncResponseDto
import com.example.offlinefirst.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users/{id}")
    suspend fun getUser(@Path("id") userId: String): Response<UserDto>

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int = 1, @Query("limit") limit: Int = 20): Response<List<UserDto>>

    @POST("users")
    suspend fun createUser(@Body user: UserDto): Response<UserDto>

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") userId: String, @Body user: UserDto): Response<UserDto>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") userId: String): Response<Unit>

    @GET("preferences/{id}")
    suspend fun getPreference(@Path("id") preferenceId: String): Response<PreferencesDto>

    @GET("preferences")
    suspend fun getPreferences(
        @Query("userId") userId: String?,
        @Query("category") category: String?,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50
    ): Response<List<PreferencesDto>>

    @POST("preferences")
    suspend fun createPreference(@Body preference: PreferencesDto): Response<PreferencesDto>

    @PUT("preferences/{id}")
    suspend fun updatePreference(
        @Path("id") preferenceId: String,
        @Body preference: PreferencesDto
    ): Response<PreferencesDto>

    @DELETE("preferences/{id}")
    suspend fun deletePreference(@Path("id") preferenceId: String): Response<Unit>

    @POST("preferences/batch")
    suspend fun createPreferencesBatch(@Body preferences: List<PreferencesDto>): Response<List<PreferencesDto>>

    @PUT("preferences/batch")
    suspend fun updatePreferencesBatch(@Body preferences: List<PreferencesDto>): Response<List<PreferencesDto>>

    @GET("content/{id}")
    suspend fun getContent(@Path("id") contentId: String): Response<ContentDto>

    @GET("content")
    suspend fun getContentList(
        @Query("category") category: String?,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): Response<List<ContentDto>>

    @POST("content")
    suspend fun createContent(@Body content: ContentDto): Response<ContentDto>

    @PUT("content/{id}")
    suspend fun updateContent(@Path("id") contentId: String, @Body content: ContentDto): Response<ContentDto>

    @DELETE("content/{id}")
    suspend fun deleteContent(@Path("id") contentId: String): Response<Unit>

    @POST("content/batch")
    suspend fun createContentBatch(@Body contents: List<ContentDto>): Response<List<ContentDto>>

    @PUT("content/batch")
    suspend fun updateContentBatch(@Body contents: List<ContentDto>): Response<List<ContentDto>>

    @POST("content/download/{id}")
    suspend fun getDownloadUrl(@Path("id") contentId: String): Response<Map<String, String>>

    @POST("sync")
    suspend fun syncData(@Body syncRequest: SyncRequestDto): Response<SyncResponseDto>

    @GET("sync/status")
    suspend fun getSyncStatus(): Response<SyncResponseDto>

    @POST("sync/force")
    suspend fun forceSync(): Response<SyncResponseDto>

    @GET("health")
    suspend fun healthCheck(): Response<Map<String, Any>>
}


package com.example.offlinefirst.data.remote

import com.example.offlinefirst.data.remote.ApiService
import com.example.offlinefirst.util.Constants
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiClient @Inject constructor(
    private val moshi: Moshi,
    private val authInterceptor: AuthInterceptor,
    private val errorInterceptor: ErrorInterceptor
) {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(errorInterceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}

@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {
    private var authToken: String? = null

    fun setAuthToken(token: String?) {
        authToken = token
    }

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")

        authToken?.let { token ->
            requestBuilder.header("Authorization", "Bearer $token")
        }

        return chain.proceed(requestBuilder.build())
    }
}

@Singleton
class ErrorInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        val response = chain.proceed(request)

        return when (response.code) {
            401 -> {
                response.close()
                throw UnauthorizedException("Unauthorized: Invalid or expired token")
            }
            403 -> {
                response.close()
                throw ForbiddenException("Forbidden: Access denied")
            }
            404 -> {
                response.close()
                throw NotFoundException("Resource not found")
            }
            429 -> {
                val retryAfter = response.header("Retry-After")?.toLongOrNull() ?: 60
                response.close()
                throw RateLimitException("Rate limit exceeded. Retry after $retryAfter seconds", retryAfter)
            }
            in 500..599 -> {
                response.close()
                throw ServerException("Server error: ${response.code}")
            }
            else -> response
        }
    }
}

class UnauthorizedException(message: String) : Exception(message)
class ForbiddenException(message: String) : Exception(message)
class NotFoundException(message: String) : Exception(message)
class RateLimitException(message: String, val retryAfterSeconds: Long) : Exception(message)
class ServerException(message: String) : Exception(message)