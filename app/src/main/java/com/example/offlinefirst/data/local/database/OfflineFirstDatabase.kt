package com.example.offlinefirst.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.offlinefirst.data.local.dao.ContentDao
import com.example.offlinefirst.data.local.dao.PreferencesDao
import com.example.offlinefirst.data.local.dao.SyncOperationDao
import com.example.offlinefirst.data.local.dao.UserDao
import com.example.offlinefirst.data.local.entity.DownloadedContentEntity
import com.example.offlinefirst.data.local.entity.PreferencesEntity
import com.example.offlinefirst.data.local.entity.SyncOperationEntity
import com.example.offlinefirst.data.local.entity.UserEntity
import com.example.offlinefirst.util.Error
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@Database(
    entities = [
        UserEntity::class,
        PreferencesEntity::class,
        DownloadedContentEntity::class,
        SyncOperationEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class OfflineFirstDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun preferencesDao(): PreferencesDao
    abstract fun contentDao(): ContentDao
    abstract fun syncOperationDao(): SyncOperationDao

    companion object {
        private const val DATABASE_NAME = "offline_first_database"
        private const val SCHEMA_FILE_NAME = "offline_first_schema"

        @Volatile
        private var INSTANCE: OfflineFirstDatabase? = null

        fun getInstance(context: Context): OfflineFirstDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): OfflineFirstDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                OfflineFirstDatabase::class.java,
                DATABASE_NAME
            )
                .addCallback(DatabaseCallback())
                .addMigrations()
                .setJournalMode(JournalMode.TRUNCATE)
                .setQueryCallback({ sql, bindArgs ->
                    android.util.Log.d("DatabaseQuery", "SQL: $sql, Args: $bindArgs")
                }, Executors.newSingleThreadExecutor())
                .fallbackToDestructiveMigration()
                .build()
        }

        fun destroyInstance() {
            INSTANCE?.close()
            INSTANCE = null
        }
    }

    private class DatabaseCallback : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            android.util.Log.i("Database", "Creating database schema")
            CoroutineScope(Dispatchers.IO).launch {
                INSTANCE?.let { database ->
                    initializeDefaultData(database)
                }
            }
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            android.util.Log.i("Database", "Database opened")
            db.execSQL("PRAGMA foreign_keys = ON")
            db.execSQL("PRAGMA journal_mode = TRUNCATE")
            db.execSQL("PRAGMA synchronous = NORMAL")
            db.execSQL("PRAGMA cache_size = 10000")
            db.execSQL("PRAGMA temp_store = MEMORY")
        }

        override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
            super.onDestructiveMigration(db)
            android.util.Log.w("Database", "Destructive migration performed - all data lost")
        }

        private suspend fun initializeDefaultData(database: OfflineFirstDatabase) {
            try {
                val currentTime = System.currentTimeMillis()
                android.util.Log.d("Database", "Initializing default data")
            } catch (e: Error) {
                android.util.Log.e("Database", "Error initializing default data", e)
            } catch (e: Exception) {
                android.util.Log.e("Database", "Error initializing default data", e)
            }
        }
    }
}