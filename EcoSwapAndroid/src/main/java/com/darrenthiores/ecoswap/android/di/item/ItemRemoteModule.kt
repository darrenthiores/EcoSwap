package com.darrenthiores.ecoswap.android.di.item

import com.darrenthiores.ecoswap.data.item.remote.ItemRemoteDataSource
import com.darrenthiores.ecoswap.data.item.remote.service.ItemService
import com.darrenthiores.ecoswap.data.item.remote.service.KtorItemService
import com.darrenthiores.ecoswap.utils.dispatchers.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ItemRemoteModule {
    @Provides
    @Singleton
    fun provideItemService(
        client: HttpClient
    ): ItemService {
        return KtorItemService(client)
    }

    @Provides
    @Singleton
    fun provideItemRemoteDataSource(
        service: ItemService,
        dispatchers: DispatchersProvider
    ): ItemRemoteDataSource {
        return ItemRemoteDataSource(
            apiService = service,
            dispatchers = dispatchers
        )
    }
}