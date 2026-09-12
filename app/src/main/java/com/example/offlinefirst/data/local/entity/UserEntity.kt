package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.User
import java.util.UUID

@Entity(
    tableName = "users",
    indices = [
        Index(value = ["email"], unique = true),
        Index(value = ["username"], unique = true),
        Index(value = ["sync_status"]),
        Index(value = ["updated_at"])
    ]
)
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "display_name")
    val displayName: String?,

    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String?,

    @ColumnInfo(name = "phone")
    val phone: String?,

    @ColumnInfo(name = "is_email_verified")
    val isEmailVerified: Boolean = false,

    @ColumnInfo(name = "is_phone_verified")
    val isPhoneVerified: Boolean = false,

    @ColumnInfo(name = "sync_status")
    val syncStatus: String = SyncStatus.PENDING.name,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "last_sync_at")
    val lastSyncAt: Long? = null,

    @ColumnInfo(name = "version")
    val version: Int = 1,

    @ColumnInfo(name = "is_deleted")
    val isDeleted: Boolean = false,

    @ColumnInfo(name = "remote_id")
    val remoteId: String? = null,

    @ColumnInfo(name = "etag")
    val etag: String? = null
) {
    fun toDomain(): User {
        return User(
            id = id,
            username = username,
            email = email,
            displayName = displayName,
            avatarUrl = avatarUrl,
            phone = phone,
            isEmailVerified = isEmailVerified,
            isPhoneVerified = isPhoneVerified,
            syncStatus = SyncStatus.valueOf(syncStatus),
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt,
            version = version,
            isDeleted = isDeleted,
            remoteId = remoteId,
            etag = etag
        )
    }

    fun isSynced(): Boolean = syncStatus == SyncStatus.SYNCED.name

    fun hasPendingChanges(): Boolean = 
        syncStatus == SyncStatus.PENDING.name || syncStatus == SyncStatus.CONFLICT.name

    fun needsSync(): Boolean = 
        lastSyncAt == null || updatedAt > (lastSyncAt ?: 0L)

    fun markAsSynced(newEtag: String? = null): UserEntity {
        return copy(
            syncStatus = SyncStatus.SYNCED.name,
            lastSyncAt = System.currentTimeMillis(),
            etag = newEtag ?: etag,
            version = version + 1
        )
    }

    fun markAsPending(): UserEntity {
        return copy(
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun markAsSyncing(): UserEntity {
        return copy(syncStatus = SyncStatus.SYNCING.name)
    }

    fun markAsConflict(remoteVersion: UserEntity? = null): UserEntity {
        return copy(
            syncStatus = SyncStatus.CONFLICT.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun markAsError(errorMessage: String? = null): UserEntity {
        return copy(
            syncStatus = SyncStatus.ERROR.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun softDelete(): UserEntity {
        return copy(
            isDeleted = true,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withEmailVerification(verified: Boolean): UserEntity {
        return copy(
            isEmailVerified = verified,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withPhoneVerification(verified: Boolean): UserEntity {
        return copy(
            isPhoneVerified = verified,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withAvatar(url: String?): UserEntity {
        return copy(
            avatarUrl = url,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withDisplayName(name: String): UserEntity {
        require(name.isNotBlank()) { "Display name cannot be blank" }
        return copy(
            displayName = name,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withEmail(email: String): UserEntity {
        require(email.contains("@")) { "Invalid email format" }
        return copy(
            email = email,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withPhone(phone: String?): UserEntity {
        return copy(
            phone = phone,
            syncStatus = SyncStatus.PENDING.name,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun canMergeWith(remote: UserEntity): Boolean {
        return this.version == remote.version || this.lastSyncAt == null
    }

    fun getConflictResolutionStrategy(other: UserEntity): ConflictStrategy {
        return when {
            this.updatedAt > other.updatedAt -> ConflictStrategy.KEEP_LOCAL
            other.updatedAt > this.updatedAt -> ConflictStrategy.KEEP_REMOTE
            else -> ConflictStrategy.MANUAL
        }
    }

    companion object {
        fun fromDomain(user: User): UserEntity {
            return UserEntity(
                id = user.id,
                username = user.username,
                email = user.email,
                displayName = user.displayName,
                avatarUrl = user.avatarUrl,
                phone = user.phone,
                isEmailVerified = user.isEmailVerified,
                isPhoneVerified = user.isPhoneVerified,
                syncStatus = user.syncStatus.name,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt,
                lastSyncAt = user.lastSyncAt,
                version = user.version,
                isDeleted = user.isDeleted,
                remoteId = user.remoteId,
                etag = user.etag
            )
        }

        fun createNew(
            username: String,
            email: String,
            displayName: String? = null
        ): UserEntity {
            require(username.isNotBlank()) { "Username cannot be blank" }
            require(email.contains("@")) { "Invalid email format" }
            
            return UserEntity(
                username = username,
                email = email,
                displayName = displayName ?: username,
                syncStatus = SyncStatus.PENDING.name
            )
        }
    }
}

enum class ConflictStrategy {
    KEEP_LOCAL,
    KEEP_REMOTE,
    MANUAL,
    MERGE
}