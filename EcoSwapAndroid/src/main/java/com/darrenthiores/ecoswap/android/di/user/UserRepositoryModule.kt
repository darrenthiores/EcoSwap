package com.darrenthiores.ecoswap.android.di.user

import com.darrenthiores.ecoswap.data.user.remote.UserRemoteDataSource
import com.darrenthiores.ecoswap.data.user.repository.UserRepositoryImpl
import com.darrenthiores.ecoswap.domain.user.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserRepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        remoteDataSource: UserRemoteDataSource
    ): UserRepository {
        return UserRepositoryImpl(
            remoteDataSource = remoteDataSource
        )
    }
}