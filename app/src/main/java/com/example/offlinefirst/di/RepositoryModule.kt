package com.example.offlinefirst.di

import com.example.offlinefirst.data.repository.ProductRepositoryImpl
import com.example.offlinefirst.data.repository.PurchaseRepositoryImpl
import com.example.offlinefirst.data.repository.UserPreferencesRepositoryImpl
import com.example.offlinefirst.domain.repository.ProductRepository
import com.example.offlinefirst.domain.repository.PurchaseRepository
import com.example.offlinefirst.domain.repository.UserPreferencesRepository
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
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindPurchaseRepository(
        purchaseRepositoryImpl: PurchaseRepositoryImpl
    ): PurchaseRepository

    @Binds
    @Singleton
    abstract fun bindUserPreferencesRepository(
        userPreferencesRepositoryImpl: UserPreferencesRepositoryImpl
    ): UserPreferencesRepository
}