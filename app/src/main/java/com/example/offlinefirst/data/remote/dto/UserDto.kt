package com.example.offlinefirst.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    @Json(name = "id") val id: String,
    @Json(name = "username") val username: String,
    @Json(name = "email") val email: String,
    @Json(name = "displayName") val displayName: String?,
    @Json(name = "phone") val phone: String?,
    @Json(name = "avatarUrl") val avatarUrl: String?,
    @Json(name = "isEmailVerified") val isEmailVerified: Boolean = false,
    @Json(name = "isPhoneVerified") val isPhoneVerified: Boolean = false,
    @Json(name = "syncStatus") val syncStatus: String = "SYNCED",
    @Json(name = "lastSyncAt") val lastSyncAt: Long? = null,
    @Json(name = "createdAt") val createdAt: Long = System.currentTimeMillis(),
    @Json(name = "updatedAt") val updatedAt: Long = System.currentTimeMillis()
)