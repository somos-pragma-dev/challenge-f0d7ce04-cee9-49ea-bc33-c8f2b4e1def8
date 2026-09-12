package com.example.offlinefirst.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SyncResponseDto(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "users") val users: List<UserDto> = emptyList(),
    @Json(name = "preferences") val preferences: List<PreferencesDto> = emptyList(),
    @Json(name = "content") val content: List<ContentDto> = emptyList(),
    @Json(name = "conflicts") val conflicts: List<ConflictDto> = emptyList(),
    @Json(name = "syncTimestamp") val syncTimestamp: Long = System.currentTimeMillis(),
    @Json(name = "message") val message: String? = null
)

@JsonClass(generateAdapter = true)
data class ConflictDto(
    @Json(name = "entityType") val entityType: String,
    @Json(name = "entityId") val entityId: String,
    @Json(name = "localVersion") val localVersion: String,
    @Json(name = "remoteVersion") val remoteVersion: String,
    @Json(name = "conflictType") val conflictType: String
)