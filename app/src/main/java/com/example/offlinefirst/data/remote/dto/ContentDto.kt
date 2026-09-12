package com.example.offlinefirst.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentDto(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String?,
    @Json(name = "url") val url: String,
    @Json(name = "fileSize") val fileSize: Long = 0L,
    @Json(name = "downloadedSize") val downloadedSize: Long = 0L,
    @Json(name = "localPath") val localPath: String? = null,
    @Json(name = "category") val category: String = "DOCUMENT",
    @Json(name = "tags") val tags: List<String> = emptyList(),
    @Json(name = "mimeType") val mimeType: String? = null,
    @Json(name = "hash") val hash: String? = null,
    @Json(name = "expiresAt") val expiresAt: Long? = null,
    @Json(name = "isAvailableOffline") val isAvailableOffline: Boolean = false,
    @Json(name = "syncStatus") val syncStatus: String = "SYNCED",
    @Json(name = "lastSyncAt") val lastSyncAt: Long? = null,
    @Json(name = "createdAt") val createdAt: Long = System.currentTimeMillis(),
    @Json(name = "updatedAt") val updatedAt: Long = System.currentTimeMillis()
)