package com.example.offlinefirst.data.sync

import com.example.offlinefirst.data.remote.ApiClient
import com.example.offlinefirst.domain.model.DownloadedContent
import com.example.offlinefirst.domain.model.Preferences
import com.example.offlinefirst.domain.model.SyncOperation
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.User
import com.example.offlinefirst.domain.repository.ContentRepository
import com.example.offlinefirst.domain.repository.PreferencesRepository
import com.example.offlinefirst.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConflictResolver @Inject constructor(
    private val apiClient: ApiClient,
    private val userRepository: UserRepository,
    private val preferencesRepository: PreferencesRepository,
    private val contentRepository: ContentRepository
) {
    suspend fun resolveConflict(operation: SyncOperation): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            when (operation.entityType) {
                "User" -> resolveUserConflict(operation)
                "Preferences" -> resolvePreferencesConflict(operation)
                "DownloadedContent" -> resolveContentConflict(operation)
                else -> Result.failure(IllegalArgumentException("Unknown entity type: ${operation.entityType}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun resolveUserConflict(operation: SyncOperation): Result<Unit> {
        val localUser = userRepository.getUserById(operation.entityId)
            ?: return Result.failure(IllegalStateException("Local user not found"))

        val remoteUser = fetchRemoteUser(operation.entityId)
            ?: return Result.failure(IllegalStateException("Remote user not found"))

        val resolvedUser = resolveUserConflict(localUser, remoteUser)
        userRepository.saveUser(resolvedUser)
        return Result.success(Unit)
    }

    private suspend fun resolvePreferencesConflict(operation: SyncOperation): Result<Unit> {
        val localPrefs = preferencesRepository.getPreferenceById(operation.entityId)
            ?: return Result.failure(IllegalStateException("Local preferences not found"))

        val remotePrefs = fetchRemotePreferences(operation.entityId)
            ?: return Result.failure(IllegalStateException("Remote preferences not found"))

        val resolvedPrefs = resolvePreferencesConflict(localPrefs, remotePrefs)
        preferencesRepository.savePreference(resolvedPrefs)
        return Result.success(Unit)
    }

    private suspend fun resolveContentConflict(operation: SyncOperation): Result<Unit> {
        val localContent = contentRepository.getContentById(operation.entityId)
            ?: return Result.failure(IllegalStateException("Local content not found"))

        val remoteContent = fetchRemoteContent(operation.entityId)
            ?: return Result.failure(IllegalStateException("Remote content not found"))

        val resolvedContent = resolveContentConflict(localContent, remoteContent)
        contentRepository.saveContent(resolvedContent)
        return Result.success(Unit)
    }

    fun resolveUserConflict(local: User, remote: User): User {
        val strategy = determineConflictStrategy(local, remote)

        return when (strategy) {
            ConflictStrategy.USE_LOCAL -> local.copy(
                version = maxOf(local.version, remote.version) + 1,
                syncStatus = SyncStatus.SYNCED
            )
            ConflictStrategy.USE_REMOTE -> remote.copy(
                syncStatus = SyncStatus.SYNCED
            )
            ConflictStrategy.MERGE -> mergeUsers(local, remote)
        }
    }

    fun resolvePreferencesConflict(local: Preferences, remote: Preferences): Preferences {
        val strategy = determineConflictStrategy(local, remote)

        return when (strategy) {
            ConflictStrategy.USE_LOCAL -> local.copy(
                version = maxOf(local.version, remote.version) + 1,
                syncStatus = SyncStatus.SYNCED
            )
            ConflictStrategy.USE_REMOTE -> remote.copy(
                syncStatus = SyncStatus.SYNCED
            )
            ConflictStrategy.MERGE -> mergePreferences(local, remote)
        }
    }

    fun resolveContentConflict(local: DownloadedContent, remote: DownloadedContent): DownloadedContent {
        val strategy = determineConflictStrategy(local, remote)

        return when (strategy) {
            ConflictStrategy.USE_LOCAL -> local.copy(
                version = maxOf(local.version, remote.version) + 1,
                syncStatus = SyncStatus.SYNCED
            )
            ConflictStrategy.USE_REMOTE -> remote.copy(
                syncStatus = SyncStatus.SYNCED
            )
            ConflictStrategy.MERGE -> mergeContent(local, remote)
        }
    }

    private fun <T> determineConflictStrategy(local: T, remote: T): ConflictStrategy {
        val localUpdatedAt = when (local) {
            is User -> local.updatedAt
            is Preferences -> local.updatedAt
            is DownloadedContent -> local.updatedAt
            else -> return ConflictStrategy.USE_REMOTE
        }

        val remoteUpdatedAt = when (remote) {
            is User -> remote.updatedAt
            is Preferences -> remote.updatedAt
            is DownloadedContent -> remote.updatedAt
            else -> return ConflictStrategy.USE_REMOTE
        }

        return if (localUpdatedAt > remoteUpdatedAt) {
            ConflictStrategy.USE_LOCAL
        } else if (remoteUpdatedAt > localUpdatedAt) {
            ConflictStrategy.USE_REMOTE
        } else {
            ConflictStrategy.MERGE
        }
    }

    private fun mergeUsers(local: User, remote: User): User {
        return local.copy(
            email = if (local.email != remote.email) remote.email else local.email,
            displayName = if (local.displayName != remote.displayName) remote.displayName else local.displayName,
            phone = if (local.phone != remote.phone) remote.phone else local.phone,
            avatarUrl = if (local.avatarUrl != remote.avatarUrl) remote.avatarUrl else local.avatarUrl,
            isEmailVerified = local.isEmailVerified || remote.isEmailVerified,
            isPhoneVerified = local.isPhoneVerified || remote.isPhoneVerified,
            version = maxOf(local.version, remote.version) + 1,
            updatedAt = System.currentTimeMillis(),
            syncStatus = SyncStatus.SYNCED
        )
    }

    private fun mergePreferences(local: Preferences, remote: Preferences): Preferences {
        return local.copy(
            value = if (local.value != remote.value) remote.value else local.value,
            category = local.category,
            version = maxOf(local.version, remote.version) + 1,
            updatedAt = System.currentTimeMillis(),
            syncStatus = SyncStatus.SYNCED
        )
    }

    private fun mergeContent(local: DownloadedContent, remote: DownloadedContent): DownloadedContent {
        return local.copy(
            title = if (local.title != remote.title) remote.title else local.title,
            description = if (local.description != remote.description) remote.description else local.description,
            localPath = if (local.localPath != null) local.localPath else remote.localPath,
            downloadedSize = maxOf(local.downloadedSize, remote.downloadedSize),
            fileSize = maxOf(local.fileSize, remote.fileSize),
            version = maxOf(local.version, remote.version) + 1,
            accessedAt = System.currentTimeMillis(),
            syncStatus = SyncStatus.SYNCED
        )
    }

    private suspend fun fetchRemoteUser(userId: String): User? {
        return try {
            val response = apiClient.apiService.getUser(userId)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun fetchRemotePreferences(prefsId: String): Preferences? {
        return try {
            val response = apiClient.apiService.getPreference(prefsId)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun fetchRemoteContent(contentId: String): DownloadedContent? {
        return try {
            val response = apiClient.apiService.getContent(contentId)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            null
        }
    }
}

enum class ConflictStrategy {
    USE_LOCAL,
    USE_REMOTE,
    MERGE
}