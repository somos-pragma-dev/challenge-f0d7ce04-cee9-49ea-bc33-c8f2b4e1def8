package com.example.offlinefirst.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.offlinefirst.data.local.dao.ProductDao
import com.example.offlinefirst.data.local.dao.PurchaseHistoryDao
import com.example.offlinefirst.data.local.dao.UserPreferencesDao
import com.example.offlinefirst.data.local.entity.ProductEntity
import com.example.offlinefirst.data.local.entity.PurchaseHistoryEntity
import com.example.offlinefirst.data.local.entity.SyncMetadataEntity
import com.example.offlinefirst.data.local.entity.UserPreferencesEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@Database(
    entities = [
        ProductEntity::class,
        PurchaseHistoryEntity::class,
        UserPreferencesEntity::class,
        SyncMetadataEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class OfflineFirstDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun purchaseHistoryDao(): PurchaseHistoryDao
    abstract fun userPreferencesDao(): UserPreferencesDao

    companion object {
        private const val DATABASE_NAME = "offline_first_database"
        private const val DATABASE_VERSION = 1

        @Volatile
        private var INSTANCE: OfflineFirstDatabase? = null

        fun getDatabase(context: Context): OfflineFirstDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OfflineFirstDatabase::class.java,
                    DATABASE_NAME
                )
                    .addCallback(DatabaseCallback())
                    .addMigrations()
                    .setJournalMode(JournalMode.TRUNCATE)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        fun getDatabaseInstance(): OfflineFirstDatabase? = INSTANCE
    }

    private class DatabaseCallback : Callback() {
        private val databaseWriteExecutor = Executors.newFixedThreadPool(4)

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            databaseWriteExecutor.execute {
                INSTANCE?.let { database ->
                    populateInitialData(database)
                }
            }
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            databaseWriteExecutor.execute {
                INSTANCE?.let { database ->
                    performDatabaseMaintenance(database)
                }
            }
        }

        private fun populateInitialData(database: OfflineFirstDatabase) {
            try {
                val defaultPreferences = UserPreferencesEntity(
                    id = 1,
                    themeMode = "SYSTEM",
                    notificationsEnabled = true,
                    autoSyncEnabled = true,
                    syncOnWifiOnly = false,
                    defaultPaymentMethod = "CREDIT_CARD",
                    language = "es",
                    lastSyncTimestamp = 0L,
                    syncStatus = "PENDING",
                    createdAt = System.currentTimeMillis(),
                    updatedAt = System.currentTimeMillis()
                )
                database.userPreferencesDao().insertPreferences(defaultPreferences)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private fun performDatabaseMaintenance(database: OfflineFirstDatabase) {
            try {
                val currentTime = System.currentTimeMillis()
                val thirtyDaysAgo = currentTime - (30L * 24 * 60 * 60 * 1000)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

class Converters {
    // Los converters se delegan a las anotaciones en las entidades
    // Room usa automaticamente los converters definidos a nivel de campo
}