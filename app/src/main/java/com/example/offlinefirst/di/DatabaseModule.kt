package com.example.offlinefirst.di

import android.content.Context
import androidx.room.Room
import com.example.offlinefirst.data.local.dao.ContentDao
import com.example.offlinefirst.data.local.dao.PreferencesDao
import com.example.offlinefirst.data.local.dao.SyncOperationDao
import com.example.offlinefirst.data.local.dao.UserDao
import com.example.offlinefirst.data.local.database.OfflineFirstDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    private const val DATABASE_NAME = "offline_first_database"
    private const val DATABASE_VERSION = 1
    
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): OfflineFirstDatabase {
        return Room.databaseBuilder(
            context,
            OfflineFirstDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .addCallback(object : Room.Callback() {
                override fun onCreate(db: androidx.room.SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.execSQL("PRAGMA journal_mode=WAL")
                    db.execSQL("PRAGMA synchronous=NORMAL")
                    db.execSQL("PRAGMA foreign_keys=ON")
                }
                
                override fun onOpen(db: androidx.room.SupportSQLiteDatabase) {
                    super.onOpen(db)
                    db.execSQL("PRAGMA journal_mode=WAL")
                    db.execSQL("PRAGMA synchronous=NORMAL")
                    db.execSQL("PRAGMA foreign_keys=ON")
                    db.execSQL("PRAGMA cache_size=10000")
                    db.execSQL("PRAGMA temp_store=MEMORY")
                }
            })
            .setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING)
            .build()
    }
    
    @Provides
    @Singleton
    fun provideUserDao(database: OfflineFirstDatabase): UserDao {
        return database.userDao()
    }
    
    @Provides
    @Singleton
    fun providePreferencesDao(database: OfflineFirstDatabase): PreferencesDao {
        return database.preferencesDao()
    }
    
    @Provides
    @Singleton
    fun provideContentDao(database: OfflineFirstDatabase): ContentDao {
        return database.contentDao()
    }
    
    @Provides
    @Singleton
    fun provideSyncOperationDao(database: OfflineFirstDatabase): SyncOperationDao {
        return database.syncOperationDao()
    }
}