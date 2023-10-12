package com.darrenthiores.ecoswap.android.di.auth

import com.darrenthiores.ecoswap.data.auth.remote.AuthRemoteDataSource
import com.darrenthiores.ecoswap.data.auth.repository.AuthRepositoryImpl
import com.darrenthiores.ecoswap.domain.auth.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthRepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        remoteDataSource: AuthRemoteDataSource
    ): AuthRepository {
        return AuthRepositoryImpl(
            remoteDataSource = remoteDataSource
        )
    }
}