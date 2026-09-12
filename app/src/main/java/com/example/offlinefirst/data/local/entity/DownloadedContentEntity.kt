package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.offlinefirst.domain.model.ContentCategory
import com.example.offlinefirst.domain.model.DownloadedContent
import com.example.offlinefirst.domain.model.SyncStatus

@Entity(tableName = "downloaded_content")
data class DownloadedContentEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "local_path")
    val localPath: String?,

    @ColumnInfo(name = "file_size")
    val fileSize: Long,

    @ColumnInfo(name = "downloaded_size")
    val downloadedSize: Long,

    @ColumnInfo(name = "content_hash")
    val contentHash: String?,

    @ColumnInfo(name = "sync_status")
    val syncStatus: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "tags")
    val tags: String,

    @ColumnInfo(name = "metadata")
    val metadata: String?,

    @ColumnInfo(name = "expires_at")
    val expiresAt: Long?,

    @ColumnInfo(name = "created_at")
    val createdAt: Long,

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long,

    @ColumnInfo(name = "accessed_at")
    val accessedAt: Long,

    @ColumnInfo(name = "last_sync_at")
    val lastSyncAt: Long?,

    @ColumnInfo(name = "is_available_offline")
    val isAvailableOffline: Boolean
) {
    fun toDomainModel(): DownloadedContent {
        return DownloadedContent(
            id = id,
            title = title,
            description = description,
            url = url,
            localPath = localPath,
            fileSize = fileSize,
            downloadedSize = downloadedSize,
            contentHash = contentHash,
            syncStatus = SyncStatus.valueOf(syncStatus),
            category = ContentCategory.valueOf(category),
            tags = tags.split(",").filter { it.isNotBlank() },
            metadata = metadata?.let {
                try {
                    DownloadedContent.parseMetadata(it)
                } catch (e: Exception) {
                    emptyMap()
                }
            } ?: emptyMap(),
            expiresAt = expiresAt,
            createdAt = createdAt,
            updatedAt = updatedAt,
            accessedAt = accessedAt,
            lastSyncAt = lastSyncAt,
            isAvailableOffline = isAvailableOffline
        )
    }

    companion object {
        fun fromDomainModel(domain: DownloadedContent): DownloadedContentEntity {
            return DownloadedContentEntity(
                id = domain.id,
                title = domain.title,
                description = domain.description,
                url = domain.url,
                localPath = domain.localPath,
                fileSize = domain.fileSize,
                downloadedSize = domain.downloadedSize,
                contentHash = domain.contentHash,
                syncStatus = domain.syncStatus.name,
                category = domain.category.name,
                tags = domain.tags.joinToString(","),
                metadata = domain.metadata.takeIf { it.isNotEmpty() }?.let {
                    DownloadedContent.serializeMetadata(it)
                },
                expiresAt = domain.expiresAt,
                createdAt = domain.createdAt,
                updatedAt = domain.updatedAt,
                accessedAt = domain.accessedAt,
                lastSyncAt = domain.lastSyncAt,
                isAvailableOffline = domain.isAvailableOffline
            )
        }
    }
}