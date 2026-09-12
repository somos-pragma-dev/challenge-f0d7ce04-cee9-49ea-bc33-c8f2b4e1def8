package com.example.offlinefirst.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PreferencesDto(
    @Json(name = "id") val id: String,
    @Json(name = "key") val key: String,
    @Json(name = "value") val value: String,
    @Json(name = "dataType") val dataType: String = "STRING",
    @Json(name = "category") val category: String = "GENERAL",
    @Json(name = "userId") val userId: String? = null,
    @Json(name = "isGlobal") val isGlobal: Boolean = true,
    @Json(name = "isEncrypted") val isEncrypted: Boolean = false,
    @Json(name = "syncStatus") val syncStatus: String = "SYNCED",
    @Json(name = "lastSyncAt") val lastSyncAt: Long? = null,
    @Json(name = "createdAt") val createdAt: Long = System.currentTimeMillis(),
    @Json(name = "updatedAt") val updatedAt: Long = System.currentTimeMillis()
)