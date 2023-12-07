package com.darrenthiores.ecoswap.android.di.message

import com.darrenthiores.ecoswap.data.message.local.MessageLocalDataSource
import com.darrenthiores.ecoswap.data.message.remote.MessageRemoteDataSource
import com.darrenthiores.ecoswap.data.message.repository.MessageRepositoryImpl
import com.darrenthiores.ecoswap.domain.message.repository.MessageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MessageRepositoryModule {

    @Provides
    @Singleton
    fun provideMessageRepository(
        remoteDataSource: MessageRemoteDataSource,
        localDataSource: MessageLocalDataSource
    ): MessageRepository {
        return MessageRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )
    }
}