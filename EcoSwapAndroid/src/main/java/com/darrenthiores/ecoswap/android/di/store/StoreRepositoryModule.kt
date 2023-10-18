package com.darrenthiores.ecoswap.android.di.store

import com.darrenthiores.ecoswap.data.store.remote.StoreRemoteDataSource
import com.darrenthiores.ecoswap.data.store.repository.StoreRepositoryImpl
import com.darrenthiores.ecoswap.domain.store.repository.StoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoreRepositoryModule {

    @Provides
    @Singleton
    fun provideStoreRepository(
        remoteDataSource: StoreRemoteDataSource
    ): StoreRepository {
        return StoreRepositoryImpl(
            remoteDataSource = remoteDataSource
        )
    }
}