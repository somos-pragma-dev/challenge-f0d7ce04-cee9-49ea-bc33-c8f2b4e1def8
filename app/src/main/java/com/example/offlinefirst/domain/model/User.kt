package com.example.offlinefirst.domain.model

import java.util.UUID

data class User(
    val id: String = UUID.randomUUID().toString(),
    val username: String,
    val email: String,
    val displayName: String,
    val avatarUrl: String? = null,
    val phoneNumber: String? = null,
    val isEmailVerified: Boolean = false,
    val isPhoneVerified: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val lastSyncAt: Long? = null,
    val syncStatus: SyncStatus = SyncStatus.PENDING
) {
    init {
        require(username.isNotBlank()) { "Username cannot be blank" }
        require(email.isNotBlank()) { "Email cannot be blank" }
        require(email.contains("@")) { "Email must be valid" }
        require(displayName.isNotBlank()) { "Display name cannot be blank" }
        require(id.isNotBlank()) { "User ID cannot be blank" }
    }

    fun isSynced(): Boolean = syncStatus == SyncStatus.SYNCED

    fun hasPendingChanges(): Boolean = syncStatus == SyncStatus.PENDING || syncStatus == SyncStatus.CONFLICT

    fun needsSync(): Boolean = lastSyncAt == null || updatedAt > (lastSyncAt ?: 0L)

    fun withUpdatedTimestamp(): User = copy(updatedAt = System.currentTimeMillis())

    fun markAsSynced(): User = copy(
        syncStatus = SyncStatus.SYNCED,
        lastSyncAt = System.currentTimeMillis()
    )

    fun markAsPending(): User = copy(syncStatus = SyncStatus.PENDING)

    fun markAsConflict(): User = copy(syncStatus = SyncStatus.CONFLICT)

    fun withEmailVerification(verified: Boolean): User = copy(
        isEmailVerified = verified,
        updatedAt = System.currentTimeMillis()
    )

    fun withPhoneVerification(verified: Boolean): User = copy(
        isPhoneVerified = verified,
        updatedAt = System.currentTimeMillis()
    )

    fun withAvatar(url: String?): User = copy(
        avatarUrl = url,
        updatedAt = System.currentTimeMillis()
    )

    fun withDisplayName(name: String): User = require(name.isNotBlank()) {
        "Display name cannot be blank"
    }.let { copy(displayName = name, updatedAt = System.currentTimeMillis()) }

    fun withEmail(email: String): User = require(email.contains("@")) {
        "Email must be valid"
    }.let { copy(email = email, updatedAt = System.currentTimeMillis()) }

    fun withPhone(phone: String?): User = copy(
        phoneNumber = phone,
        updatedAt = System.currentTimeMillis()
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String = "User(id=$id, username=$username, email=$email, displayName=$displayName)"
}

enum class SyncStatus {
    PENDING,
    SYNCING,
    SYNCED,
    CONFLICT,
    ERROR;

    fun isPending(): Boolean = this == PENDING
    fun isSyncing(): Boolean = this == SYNCING
    fun isSynced(): Boolean = this == SYNCED
    fun hasConflict(): Boolean = this == CONFLICT
    fun hasError(): Boolean = this == ERROR

    fun canSync(): Boolean = this == PENDING || this == ERROR
}

data class UserProfile(
    val user: User,
    val preferences: Preferences? = null,
    val downloadedContents: List<DownloadedContent> = emptyList()
)

data class UserCredentials(
    val email: String,
    val password: String
) {
    init {
        require(email.isNotBlank()) { "Email cannot be blank" }
        require(password.length >= 8) { "Password must be at least 8 characters" }
    }

    fun isValid(): Boolean = email.contains("@") && password.length >= 8
}

sealed class UserValidationResult {
    data object Valid : UserValidationResult()
    data class Invalid(val errors: List<String>) : UserValidationResult()

    fun isValid(): Boolean = this is Valid
    fun getErrors(): List<String> = (this as? Invalid)?.errors ?: emptyList()
}

fun User.validate(): UserValidationResult {
    val errors = mutableListOf<String>()
    if (username.isBlank()) errors.add("Username cannot be blank")
    if (email.isBlank()) errors.add("Email cannot be blank")
    if (!email.contains("@")) errors.add("Email must be valid")
    if (displayName.isBlank()) errors.add("Display name cannot be blank")
    return if (errors.isEmpty()) UserValidationResult.Valid
    else UserValidationResult.Invalid(errors)
}