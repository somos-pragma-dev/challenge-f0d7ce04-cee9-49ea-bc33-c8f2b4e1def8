package com.example.offlinefirst.domain.repository

import com.example.offlinefirst.data.repository.ContentRepositoryImpl
import com.example.offlinefirst.domain.model.ContentCategory
import com.example.offlinefirst.domain.model.ContentValidationResult
import com.example.offlinefirst.domain.model.DownloadedContent
import com.example.offlinefirst.domain.model.ContentBatch
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    fun getAllContents(): Flow<List<DownloadedContent>>
    
    fun getContentById(contentId: String): Flow<Resource<DownloadedContent>>
    
    fun getContentByUrl(url: String): Flow<Resource<DownloadedContent>>
    
    fun getContentsByCategory(category: ContentCategory): Flow<List<DownloadedContent>>
    
    fun getContentsByTags(tags: List<String>): Flow<List<DownloadedContent>>
    
    fun getDownloadedContents(): Flow<List<DownloadedContent>>
    
    fun getAvailableOfflineContents(): Flow<List<DownloadedContent>>
    
    fun getExpiredContents(): Flow<List<DownloadedContent>>
    
    suspend fun saveContent(content: DownloadedContent): Resource<DownloadedContent>
    
    suspend fun saveContents(contents: List<DownloadedContent>): Resource<List<DownloadedContent>>
    
    suspend fun updateContent(content: DownloadedContent): Resource<DownloadedContent>
    
    suspend fun deleteContent(contentId: String): Resource<Unit>
    
    suspend fun deleteAllContents(): Resource<Unit>
    
    suspend fun deleteExpiredContents(): Resource<Int>
    
    fun getPendingSyncContents(): Flow<List<DownloadedContent>>
    
    fun getSyncedContents(): Flow<List<DownloadedContent>>
    
    fun getContentsWithConflicts(): Flow<List<DownloadedContent>>
    
    suspend fun markContentAsSynced(contentId: String): Resource<DownloadedContent>
    
    suspend fun markContentAsPending(contentId: String): Resource<DownloadedContent>
    
    suspend fun markContentAsConflict(contentId: String): Resource<DownloadedContent>
    
    suspend fun syncContent(contentId: String): Resource<DownloadedContent>
    
    suspend fun syncAllPendingContents(): Resource<List<DownloadedContent>>
    
    suspend fun resolveContentConflict(contentId: String, resolution: ContentConflictResolution): Resource<DownloadedContent>
    
    suspend fun downloadContent(contentId: String, url: String): Resource<DownloadedContent>
    
    suspend fun downloadContentsBatch(contents: List<DownloadedContent>): Resource<ContentBatch>
    
    suspend fun cancelDownload(contentId: String): Resource<Unit>
    
    suspend fun pauseDownload(contentId: String): Resource<Unit>
    
    suspend fun resumeDownload(contentId: String): Resource<DownloadedContent>
    
    suspend fun getDownloadProgress(contentId: String): Float
    
    fun observeDownloadProgress(contentId: String): Flow<Float>
    
    suspend fun getLocalFilePath(contentId: String): String?
    
    suspend fun validateContentFile(contentId: String): Boolean
    
    suspend fun cleanupOrphanedFiles(): Resource<Int>
    
    fun searchContents(query: String): Flow<List<DownloadedContent>>
    
    fun getContentCount(): Flow<Int>
    
    fun getDownloadedContentCount(): Flow<Int>
    
    fun getStorageUsed(): Flow<Long>
    
    suspend fun validateContent(url: String?, title: String?, fileSize: Long, downloadedSize: Long, expiresAt: Long?): ContentValidationResult
    
    suspend fun refreshContentFromRemote(contentId: String): Resource<DownloadedContent>
    
    fun observeContentSyncStatus(contentId: String): Flow<SyncStatus>
    
    suspend fun getLastSyncTime(contentId: String): Long?
    
    suspend fun updateLastSyncTime(contentId: String, timestamp: Long)
    
    suspend fun updateAccessCount(contentId: String): Resource<DownloadedContent>
    
    suspend fun getMostAccessedContents(limit: Int = 10): List<DownloadedContent>
    
    suspend fun getContentsByExpirationRange(startTime: Long, endTime: Long): List<DownloadedContent>
}

sealed class ContentConflictResolution {
    data class UseLocal(val content: DownloadedContent) : ContentConflictResolution()
    data class UseRemote(val remoteContent: DownloadedContent) : ContentConflictResolution()
    data class Merge(val localContent: DownloadedContent, val remoteContent: DownloadedContent) : ContentConflictResolution()
    data object Discard : ContentConflictResolution()
}

interface ContentCacheManager {
    suspend fun cacheContent(content: DownloadedContent)
    
    suspend fun cacheContents(contents: List<DownloadedContent>)
    
    suspend fun getCachedContent(contentId: String): DownloadedContent?
    
    suspend fun getCachedContents(): List<DownloadedContent>
    
    suspend fun invalidateCache()
    
    suspend fun invalidateContent(contentId: String)
    
    fun isCacheValid(): Boolean
    
    suspend fun getCacheSize(): Long
}

interface ContentRemoteDataSource {
    suspend fun fetchContent(contentId: String): Resource<DownloadedContent>
    
    suspend fun fetchAllContents(): Resource<List<DownloadedContent>>
    
    suspend fun fetchContentsByCategory(category: ContentCategory): Resource<List<DownloadedContent>>
    
    suspend fun fetchContentsByTags(tags: List<String>): Resource<List<DownloadedContent>>
    
    suspend fun createContent(content: DownloadedContent): Resource<DownloadedContent>
    
    suspend fun updateContent(content: DownloadedContent): Resource<DownloadedContent>
    
    suspend fun deleteContent(contentId: String): Resource<Unit>
    
    suspend fun downloadContent(url: String): Resource<Pair<String, Long>>
    
    suspend fun getContentDownloadUrl(contentId: String): Resource<String>
    
    suspend fun searchContents(query: String): Resource<List<DownloadedContent>>
}

interface ContentDownloadManager {
    suspend fun startDownload(content: DownloadedContent, url: String): Flow<DownloadProgress>
    
    suspend fun pauseDownload(contentId: String)
    
    suspend fun resumeDownload(contentId: String)
    
    suspend fun cancelDownload(contentId: String)
    
    fun getActiveDownloads(): Flow<List<DownloadedContent>>
    
    suspend fun getDownloadStatus(contentId: String): DownloadStatus
}

data class DownloadProgress(
    val contentId: String,
    val bytesDownloaded: Long,
    val totalBytes: Long,
    val progress: Float,
    val status: DownloadStatus
)

enum class DownloadStatus {
    PENDING,
    DOWNLOADING,
    PAUSED,
    COMPLETED,
    FAILED,
    CANCELLED;
    
    fun isActive(): Boolean = this == DOWNLOADING || this == PENDING
    fun isTerminal(): Boolean = this == COMPLETED || this == FAILED || this == CANCELLED
}

object ContentRepositoryFactory {
    fun create(
        localDataSource: ContentLocalDataSource,
        remoteDataSource: ContentRemoteDataSource,
        downloadManager: ContentDownloadManager
    ): ContentRepository {
        return ContentRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            downloadManager = downloadManager
        )
    }
}

interface ContentLocalDataSource {
    fun getAllContents(): Flow<List<DownloadedContent>>
    
    fun getContentById(contentId: String): Flow<DownloadedContent?>
    
    fun getContentByUrl(url: String): Flow<DownloadedContent?>
    
    fun getContentsByCategory(category: ContentCategory): Flow<List<DownloadedContent>>
    
    fun getContentsByTags(tags: List<String>): Flow<List<DownloadedContent>>
    
    fun getDownloadedContents(): Flow<List<DownloadedContent>>
    
    fun getAvailableOfflineContents(): Flow<List<DownloadedContent>>
    
    fun getExpiredContents(): Flow<List<DownloadedContent>>
    
    suspend fun insertContent(content: DownloadedContent)
    
    suspend fun insertContents(contents: List<DownloadedContent>)
    
    suspend fun updateContent(content: DownloadedContent)
    
    suspend fun deleteContent(contentId: String)
    
    suspend fun deleteAllContents()
    
    suspend fun deleteExpiredContents()
    
    fun getPendingSyncContents(): Flow<List<DownloadedContent>>
    
    fun getSyncedContents(): Flow<List<DownloadedContent>>
    
    fun getContentsWithConflicts(): Flow<List<DownloadedContent>>
    
    suspend fun updateSyncStatus(contentId: String, status: SyncStatus)
    
    fun searchContents(query: String): Flow<List<DownloadedContent>>
    
    fun getContentCount(): Flow<Int>
    
    fun getDownloadedContentCount(): Flow<Int>
    
    fun getStorageUsed(): Flow<Long>
}