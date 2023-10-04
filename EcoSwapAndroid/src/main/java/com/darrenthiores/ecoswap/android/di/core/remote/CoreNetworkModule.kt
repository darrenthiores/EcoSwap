package com.darrenthiores.ecoswap.android.di.core.remote

import com.darrenthiores.ecoswap.data.core.local.TokenPreferences
import com.darrenthiores.ecoswap.data.core.remote.client.HttpClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModule {

    @Provides
    @Singleton
    fun provideClient(
        tokenPreferences: TokenPreferences
    ): HttpClient {
        return HttpClientFactory()
            .create(
                tokenPreferences = tokenPreferences
            )
    }
}