package com.darrenthiores.ecoswap.android.di.auth

import com.darrenthiores.ecoswap.data.auth.remote.AuthRemoteDataSource
import com.darrenthiores.ecoswap.data.auth.remote.service.AuthService
import com.darrenthiores.ecoswap.data.auth.remote.service.KtorAuthService
import com.darrenthiores.ecoswap.utils.dispatchers.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthRemoteModule {
    @Provides
    @Singleton
    fun provideAuthService(
        client: HttpClient
    ): AuthService {
        return KtorAuthService(client)
    }

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(
        service: AuthService,
        dispatchers: DispatchersProvider
    ): AuthRemoteDataSource {
        return AuthRemoteDataSource(
            apiService = service,
            dispatchers = dispatchers
        )
    }
}