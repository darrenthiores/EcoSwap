package com.darrenthiores.ecoswap.android.di.user

import com.darrenthiores.ecoswap.data.user.remote.UserRemoteDataSource
import com.darrenthiores.ecoswap.data.user.remote.service.KtorUserService
import com.darrenthiores.ecoswap.data.user.remote.service.UserService
import com.darrenthiores.ecoswap.utils.dispatchers.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserRemoteModule {
    @Provides
    @Singleton
    fun provideUserService(
        client: HttpClient
    ): UserService {
        return KtorUserService(client)
    }

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(
        service: UserService,
        dispatchers: DispatchersProvider
    ): UserRemoteDataSource {
        return UserRemoteDataSource(
            apiService = service,
            dispatchers = dispatchers
        )
    }
}