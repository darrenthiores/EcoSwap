package com.darrenthiores.ecoswap.android.di.message

import com.darrenthiores.ecoswap.data.message.remote.MessageRemoteDataSource
import com.darrenthiores.ecoswap.data.message.remote.service.KtorMessageService
import com.darrenthiores.ecoswap.data.message.remote.service.MessageService
import com.darrenthiores.ecoswap.utils.dispatchers.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MessageRemoteModule {
    @Provides
    @Singleton
    fun provideMessageService(
        client: HttpClient
    ): MessageService {
        return KtorMessageService(client)
    }

    @Provides
    @Singleton
    fun provideMessageRemoteDataSource(
        service: MessageService,
        dispatchers: DispatchersProvider
    ): MessageRemoteDataSource {
        return MessageRemoteDataSource(
            apiService = service,
            dispatchers = dispatchers
        )
    }
}