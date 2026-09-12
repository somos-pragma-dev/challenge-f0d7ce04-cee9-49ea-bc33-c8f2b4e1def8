package com.example.offlinefirst.data.repository

import com.example.offlinefirst.data.local.dao.ContentDao
import com.example.offlinefirst.data.local.entity.DownloadedContentEntity
import com.example.offlinefirst.domain.model.ContentCategory
import com.example.offlinefirst.domain.model.DownloadedContent
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.repository.ContentRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentRepositoryImpl @Inject constructor(
    private val contentDao: ContentDao
) : ContentRepository {

    override fun getContentById(id: String): Flow<Resource<DownloadedContent>> = flow {
        emit(Resource.Loading())
        try {
            val entity = contentDao.getById(id)
            if (entity != null) {
                emit(Resource.Success(entity.toDomain()))
            } else {
                emit(Resource.Error("Content not found with id: $id"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getContentByUrl(url: String): Flow<Resource<DownloadedContent>> = flow {
        emit(Resource.Loading())
        try {
            val entity = contentDao.getByUrl(url)
            if (entity != null) {
                emit(Resource.Success(entity.toDomain()))
            } else {
                emit(Resource.Error("Content not found with url: $url"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getAllContent(): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.getAll().collect { entities ->
                emit(Resource.Success(entities.map { it.toDomain() }))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getDownloadedContent(): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.getDownloaded().collect { entities ->
                emit(Resource.Success(entities.map { it.toDomain() }))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getAvailableOfflineContent(): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            val currentTime = System.currentTimeMillis()
            contentDao.getAvailableOffline(currentTime).collect { entities ->
                emit(Resource.Success(entities.map { it.toDomain() }))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getContentByCategory(category: ContentCategory): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.getByCategory(category.name).collect { entities ->
                emit(Resource.Success(entities.map { it.toDomain() }))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getPendingContent(): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = contentDao.getPendingSync()
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getExpiredContent(): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            val currentTime = System.currentTimeMillis()
            val entities = contentDao.getExpired(currentTime)
            emit(Resource.Success(entities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun saveContent(content: DownloadedContent): Flow<Resource<DownloadedContent>> = flow {
        emit(Resource.Loading())
        try {
            val entity = content.toEntity()
            contentDao.insert(entity)
            
            val savedEntity = contentDao.getById(content.id)
            if (savedEntity != null) {
                emit(Resource.Success(savedEntity.toDomain()))
            } else {
                emit(Resource.Error("Failed to save content"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun saveContentList(contentList: List<DownloadedContent>): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            val entities = contentList.map { it.toEntity() }
            contentDao.insertAll(entities)
            
            val savedEntities = contentList.mapNotNull { contentDao.getById(it.id) }
            emit(Resource.Success(savedEntities.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun updateDownloadProgress(id: String, downloadedSize: Long): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.updateDownloadProgress(id, null, downloadedSize, "DOWNLOADING", System.currentTimeMillis())
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsDownloaded(id: String, localPath: String, fileSize: Long, hash: String?): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.updateDownloadProgress(id, localPath, fileSize, "SYNCED", System.currentTimeMillis())
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun deleteContent(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val entity = contentDao.getById(id)
            entity?.localPath?.let { path ->
                val file = File(path)
                if (file.exists()) {
                    file.delete()
                }
            }
            contentDao.deleteById(id)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun deleteExpiredContent(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val currentTime = System.currentTimeMillis()
            val expiredEntities = contentDao.getExpired(currentTime)
            
            expiredEntities.forEach { entity ->
                entity.localPath?.let { path ->
                    val file = File(path)
                    if (file.exists()) {
                        file.delete()
                    }
                }
            }
            
            val deletedCount = contentDao.deleteExpired(currentTime)
            emit(Resource.Success(deletedCount))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsSynced(id: String, syncTimestamp: Long): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.updateSyncStatus(id, SyncStatus.SYNCED.name, syncTimestamp)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsPending(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val timestamp = System.currentTimeMillis()
            contentDao.updateSyncStatus(id, SyncStatus.PENDING.name, timestamp)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun markAsConflict(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.updateSyncStatus(id, SyncStatus.CONFLICT.name, 0L)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun incrementAccessCount(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.incrementAccessCount(id, System.currentTimeMillis())
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getContentCount(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = contentDao.getCount()
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getDownloadedCount(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val count = contentDao.getCountBySyncStatus("SYNCED")
            emit(Resource.Success(count))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getTotalSize(): Flow<Resource<Long>> = flow {
        emit(Resource.Loading())
        try {
            val size = contentDao.getTotalSize() ?: 0L
            emit(Resource.Success(size))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun searchContent(query: String): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.search(query).collect { entities ->
                emit(Resource.Success(entities.map { it.toDomain() }))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getContentByTag(tag: String): Flow<Resource<List<DownloadedContent>>> = flow {
        emit(Resource.Loading())
        try {
            contentDao.getByTag(tag).collect { entities ->
                emit(Resource.Success(entities.map { it.toDomain() }))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }

    private fun DownloadedContentEntity.toDomain(): DownloadedContent {
        return DownloadedContent(
            id = id,
            title = title,
            description = description,
            url = url,
            localPath = localPath,
            thumbnailUrl = thumbnailUrl,
            fileSize = fileSize,
            downloadedSize = downloadedSize,
            mimeType = mimeType,
            hash = hash,
            category = ContentCategory.valueOf(category),
            tags = tags.split(",").filter { it.isNotBlank() },
            isAvailableOffline = isAvailableOffline,
            expiresAt = expiresAt,
            accessCount = accessCount,
            accessedAt = accessedAt,
            syncStatus = SyncStatus.valueOf(syncStatus),
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt
        )
    }

    private fun DownloadedContent.toEntity(): DownloadedContentEntity {
        return DownloadedContentEntity(
            id = id,
            title = title,
            description = description,
            url = url,
            localPath = localPath,
            thumbnailUrl = thumbnailUrl,
            fileSize = fileSize,
            downloadedSize = downloadedSize,
            mimeType = mimeType,
            hash = hash,
            category = category.name,
            tags = tags.joinToString(","),
            isAvailableOffline = isAvailableOffline,
            expiresAt = expiresAt,
            accessCount = accessCount,
            accessedAt = accessedAt,
            syncStatus = syncStatus.name,
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncAt = lastSyncAt
        )
    }
}