package com.darrenthiores.ecoswap.android.di.store

import com.darrenthiores.ecoswap.data.store.remote.StoreRemoteDataSource
import com.darrenthiores.ecoswap.data.store.remote.service.KtorStoreService
import com.darrenthiores.ecoswap.data.store.remote.service.StoreService
import com.darrenthiores.ecoswap.utils.dispatchers.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoreRemoteModule {
    @Provides
    @Singleton
    fun provideStoreService(
        client: HttpClient
    ): StoreService {
        return KtorStoreService(client)
    }

    @Provides
    @Singleton
    fun provideStoreRemoteDataSource(
        service: StoreService,
        dispatchers: DispatchersProvider
    ): StoreRemoteDataSource {
        return StoreRemoteDataSource(
            apiService = service,
            dispatchers = dispatchers
        )
    }
}