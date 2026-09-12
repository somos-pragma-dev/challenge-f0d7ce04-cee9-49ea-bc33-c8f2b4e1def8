package com.example.offlinefirst.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.offlinefirst.data.local.entity.DownloadedContentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(content: DownloadedContentEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(contents: List<DownloadedContentEntity>): List<Long>

    @Update
    suspend fun update(content: DownloadedContentEntity)

    @Delete
    suspend fun delete(content: DownloadedContentEntity)

    @Query("DELETE FROM downloaded_content WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM downloaded_content WHERE syncStatus = :status")
    suspend fun deleteBySyncStatus(status: String)

    @Query("SELECT * FROM downloaded_content WHERE id = :id")
    suspend fun getById(id: String): DownloadedContentEntity?

    @Query("SELECT * FROM downloaded_content WHERE id = :id")
    fun getByIdFlow(id: String): Flow<DownloadedContentEntity?>

    @Query("SELECT * FROM downloaded_content ORDER BY createdAt DESC")
    fun getAll(): Flow<List<DownloadedContentEntity>>

    @Query("SELECT * FROM downloaded_content ORDER BY createdAt DESC LIMIT :limit OFFSET :offset")
    suspend fun getAllPaginated(limit: Int, offset: Int): List<DownloadedContentEntity>

    @Query("SELECT * FROM downloaded_content WHERE syncStatus = :status ORDER BY createdAt ASC")
    fun getBySyncStatus(status: String): Flow<List<DownloadedContentEntity>>

    @Query("SELECT * FROM downloaded_content WHERE syncStatus IN (:statuses) ORDER BY createdAt ASC")
    suspend fun getBySyncStatuses(statuses: List<String>): List<DownloadedContentEntity>

    @Query("SELECT * FROM downloaded_content WHERE category = :category ORDER BY createdAt DESC")
    fun getByCategory(category: String): Flow<List<DownloadedContentEntity>>

    @Query("SELECT * FROM downloaded_content WHERE category IN (:categories) ORDER BY createdAt DESC")
    fun getByCategories(categories: List<String>): Flow<List<DownloadedContentEntity>>

    @Query("SELECT * FROM downloaded_content WHERE localPath IS NOT NULL AND downloadedSize = fileSize ORDER BY accessedAt DESC")
    fun getDownloaded(): Flow<List<DownloadedContentEntity>>

    @Query("SELECT * FROM downloaded_content WHERE localPath IS NOT NULL AND downloadedSize = fileSize AND expiresAt IS NOT NULL AND expiresAt < :currentTime ORDER BY expiresAt ASC")
    suspend fun getExpired(currentTime: Long): List<DownloadedContentEntity>

    @Query("SELECT * FROM downloaded_content WHERE localPath IS NOT NULL AND downloadedSize = fileSize AND (expiresAt IS NULL OR expiresAt > :currentTime) AND isAvailableOffline = 1 ORDER BY accessedAt DESC")
    fun getAvailableOffline(currentTime: Long): Flow<List<DownloadedContentEntity>>

    @Query("SELECT * FROM downloaded_content WHERE url = :url LIMIT 1")
    suspend fun getByUrl(url: String): DownloadedContentEntity?

    @Query("SELECT * FROM downloaded_content WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%' ORDER BY createdAt DESC")
    fun search(query: String): Flow<List<DownloadedContentEntity>>

    @Query("SELECT COUNT(*) FROM downloaded_content")
    suspend fun getCount(): Int

    @Query("SELECT COUNT(*) FROM downloaded_content WHERE syncStatus = :status")
    suspend fun getCountBySyncStatus(status: String): Int

    @Query("SELECT COUNT(*) FROM downloaded_content WHERE category = :category")
    suspend fun getCountByCategory(category: String): Int

    @Query("SELECT SUM(fileSize) FROM downloaded_content")
    suspend fun getTotalSize(): Long?

    @Query("SELECT SUM(downloadedSize) FROM downloaded_content")
    suspend fun getTotalDownloadedSize(): Long?

    @Query("SELECT SUM(fileSize) FROM downloaded_content WHERE localPath IS NOT NULL AND downloadedSize = fileSize")
    suspend fun getTotalCachedSize(): Long?

    @Query("UPDATE downloaded_content SET syncStatus = :status, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateSyncStatus(id: String, status: String, updatedAt: Long)

    @Query("UPDATE downloaded_content SET localPath = :localPath, downloadedSize = :downloadedSize, syncStatus = :status, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateDownloadProgress(id: String, localPath: String?, downloadedSize: Long, status: String, updatedAt: Long)

    @Query("UPDATE downloaded_content SET accessedAt = :accessedAt WHERE id = :id")
    suspend fun updateAccessTime(id: String, accessedAt: Long)

    @Transaction
    @Query("SELECT * FROM downloaded_content WHERE syncStatus = 'PENDING' OR syncStatus = 'ERROR' ORDER BY createdAt ASC")
    suspend fun getPendingSync(): List<DownloadedContentEntity>

    @Query("DELETE FROM downloaded_content WHERE expiresAt IS NOT NULL AND expiresAt < :currentTime")
    suspend fun deleteExpired(currentTime: Long): Int

    @Query("SELECT * FROM downloaded_content WHERE tags LIKE '%' || :tag || '%' ORDER BY createdAt DESC")
    fun getByTag(tag: String): Flow<List<DownloadedContentEntity>>

    @Query("UPDATE downloaded_content SET tags = :tags, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateTags(id: String, tags: String, updatedAt: Long)

    @Query("UPDATE downloaded_content SET accessCount = accessCount + 1, accessedAt = :accessedAt WHERE id = :id")
    suspend fun incrementAccessCount(id: String, accessedAt: Long)

    @Query("SELECT * FROM downloaded_content WHERE metadata LIKE '%' || :key || '%' ORDER BY createdAt DESC")
    fun getByMetadataKey(key: String): Flow<List<DownloadedContentEntity>>
}