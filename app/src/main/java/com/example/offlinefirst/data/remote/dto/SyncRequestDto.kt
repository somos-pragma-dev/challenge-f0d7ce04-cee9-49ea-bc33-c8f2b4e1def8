package com.example.offlinefirst.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SyncRequestDto(
    @Json(name = "users") val users: List<UserDto> = emptyList(),
    @Json(name = "preferences") val preferences: List<PreferencesDto> = emptyList(),
    @Json(name = "content") val content: List<ContentDto> = emptyList(),
    @Json(name = "lastSyncTimestamp") val lastSyncTimestamp: Long? = null,
    @Json(name = "forceFullSync") val forceFullSync: Boolean = false
)