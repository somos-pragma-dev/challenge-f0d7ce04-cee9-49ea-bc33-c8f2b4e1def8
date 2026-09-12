package com.example.offlinefirst.domain.model

import java.util.UUID

data class DownloadedContent(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String? = null,
    val url: String,
    val localPath: String? = null,
    val mimeType: String = "application/octet-stream",
    val fileSize: Long = 0L,
    val downloadedSize: Long = 0L,
    val checksum: String? = null,
    val category: ContentCategory = ContentCategory.OTHER,
    val tags: List<String> = emptyList(),
    val thumbnailUrl: String? = null,
    val metadata: Map<String, String> = emptyMap(),
    val isAvailableOffline: Boolean = true,
    val expiresAt: Long? = null,
    val downloadedAt: Long = System.currentTimeMillis(),
    val accessedAt: Long = System.currentTimeMillis(),
    val accessCount: Int = 0,
    val lastSyncAt: Long? = null,
    val syncStatus: SyncStatus = SyncStatus.PENDING,
    val userId: String? = null
) {
    init {
        require(title.isNotBlank()) { "Content title cannot be blank" }
        require(url.isNotBlank()) { "Content URL cannot be blank" }
        require(id.isNotBlank()) { "Content ID cannot be blank" }
        require(downloadedSize <= fileSize) { "Downloaded size cannot exceed file size" }
    }

    fun isSynced(): Boolean = syncStatus == SyncStatus.SYNCED

    fun hasPendingChanges(): Boolean = syncStatus == SyncStatus.PENDING || syncStatus == SyncStatus.CONFLICT

    fun needsSync(): Boolean = lastSyncAt == null || updatedAt > (lastSyncAt ?: 0L)

    private val updatedAt: Long get() = maxOf(downloadedAt, accessedAt)

    fun isDownloaded(): Boolean = localPath != null && downloadedSize == fileSize

    fun isDownloading(): Boolean = downloadedSize > 0 && downloadedSize < fileSize

    fun isExpired(): Boolean = expiresAt != null && System.currentTimeMillis() > expiresAt

    fun isAvailable(): Boolean = isDownloaded() && !isExpired() && isAvailableOffline

    fun getDownloadProgress(): Float = if (fileSize > 0) downloadedSize.toFloat() / fileSize else 0f

    fun getProgressPercentage(): Int = (getDownloadProgress() * 100).toInt()

    fun withUpdatedTimestamp(): DownloadedContent = copy(accessedAt = System.currentTimeMillis())

    fun markAsSynced(): DownloadedContent = copy(
        syncStatus = SyncStatus.SYNCED,
        lastSyncAt = System.currentTimeMillis()
    )

    fun markAsPending(): DownloadedContent = copy(syncStatus = SyncStatus.PENDING)

    fun markAsConflict(): DownloadedContent = copy(syncStatus = SyncStatus.CONFLICT)

    fun markAsDownloading(progress: Long): DownloadedContent = copy(
        downloadedSize = progress,
        syncStatus = SyncStatus.SYNCING
    )

    fun markAsDownloaded(path: String, size: Long, hash: String?): DownloadedContent = copy(
        localPath = path,
        downloadedSize = size,
        fileSize = size,
        checksum = hash,
        syncStatus = SyncStatus.PENDING
    )

    fun incrementAccessCount(): DownloadedContent = copy(
        accessCount = accessCount + 1,
        accessedAt = System.currentTimeMillis()
    )

    fun withLocalPath(path: String): DownloadedContent = copy(localPath = path)

    fun withExpiration(expiresAt: Long?): DownloadedContent = copy(expiresAt = expiresAt)

    fun withCategory(category: ContentCategory): DownloadedContent = copy(category = category)

    fun withTags(newTags: List<String>): DownloadedContent = copy(tags = newTags)

    fun addTag(tag: String): DownloadedContent = copy(tags = tags + tag)

    fun removeTag(tag: String): DownloadedContent = copy(tags = tags - tag)

    fun withMetadata(additionalMetadata: Map<String, String>): DownloadedContent = copy(
        metadata = metadata + additionalMetadata
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DownloadedContent) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String = "DownloadedContent(id=$id, title=$title, status=$syncStatus)"
}

enum class ContentCategory {
    IMAGE,
    VIDEO,
    AUDIO,
    DOCUMENT,
    ARCHIVE,
    OTHER;

    fun isMedia(): Boolean = this in listOf(IMAGE, VIDEO, AUDIO)
    fun isDocument(): Boolean = this == DOCUMENT
    fun isArchive(): Boolean = this == ARCHIVE
}

data class ContentBatch(
    val contents: List<DownloadedContent>,
    val totalSize: Long = contents.sumOf { it.fileSize },
    val downloadedSize: Long = contents.sumOf { it.downloadedSize }
) {
    fun getTotalProgress(): Float = if (totalSize > 0) downloadedSize.toFloat() / totalSize else 0f

    fun getAllSynced(): Boolean = contents.all { it.isSynced() }

    fun getAllDownloaded(): Boolean = contents.all { it.isDownloaded() }

    fun getPendingCount(): Int = contents.count { it.hasPendingChanges() }

    fun getExpiredContents(): List<DownloadedContent> = contents.filter { it.isExpired() }

    fun getAvailableContents(): List<DownloadedContent> = contents.filter { it.isAvailable() }
}

sealed class ContentValidationResult {
    data object Valid : ContentValidationResult()
    data class Invalid(val errors: List<String>) : ContentValidationResult()

    fun isValid(): Boolean = this is Valid
    fun getErrors(): List<String> = (this as? Invalid)?.errors ?: emptyList()
}

fun DownloadedContent.validate(): ContentValidationResult {
    val errors = mutableListOf<String>()
    if (title.isBlank()) errors.add("Content title cannot be blank")
    if (url.isBlank()) errors.add("Content URL cannot be blank")
    if (fileSize < 0) errors.add("File size cannot be negative")
    if (downloadedSize < 0) errors.add("Downloaded size cannot be negative")
    if (downloadedSize > fileSize) errors.add("Downloaded size cannot exceed file size")
    if (expiresAt != null && expiresAt < System.currentTimeMillis()) {
        errors.add("Expiration date cannot be in the past")
    }
    return if (errors.isEmpty()) ContentValidationResult.Valid
    else ContentValidationResult.Invalid(errors)
}