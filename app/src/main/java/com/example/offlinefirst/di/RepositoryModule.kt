package com.example.offlinefirst.di

import com.example.offlinefirst.data.repository.ContentRepositoryImpl
import com.example.offlinefirst.data.repository.PreferencesRepositoryImpl
import com.example.offlinefirst.data.repository.SyncRepositoryImpl
import com.example.offlinefirst.data.repository.UserRepositoryImpl
import com.example.offlinefirst.domain.repository.ContentRepository
import com.example.offlinefirst.domain.repository.PreferencesRepository
import com.example.offlinefirst.domain.repository.SyncRepository
import com.example.offlinefirst.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
    
    @Binds
    @Singleton
    abstract fun bindPreferencesRepository(
        preferencesRepositoryImpl: PreferencesRepositoryImpl
    ): PreferencesRepository
    
    @Binds
    @Singleton
    abstract fun bindContentRepository(
        contentRepositoryImpl: ContentRepositoryImpl
    ): ContentRepository
    
    @Binds
    @Singleton
    abstract fun bindSyncRepository(
        syncRepositoryImpl: SyncRepositoryImpl
    ): SyncRepository
}