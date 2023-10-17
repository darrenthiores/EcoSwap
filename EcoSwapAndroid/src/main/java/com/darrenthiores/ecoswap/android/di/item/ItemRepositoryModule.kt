package com.darrenthiores.ecoswap.android.di.item

import com.darrenthiores.ecoswap.data.item.remote.ItemRemoteDataSource
import com.darrenthiores.ecoswap.data.item.repository.ItemRepositoryImpl
import com.darrenthiores.ecoswap.domain.item.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ItemRepositoryModule {

    @Provides
    @Singleton
    fun provideItemRepository(
        remoteDataSource: ItemRemoteDataSource
    ): ItemRepository {
        return ItemRepositoryImpl(
            remoteDataSource = remoteDataSource
        )
    }
}