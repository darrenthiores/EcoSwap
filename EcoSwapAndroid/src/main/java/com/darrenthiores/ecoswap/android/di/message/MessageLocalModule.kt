package com.darrenthiores.ecoswap.android.di.message

import com.darrenthiores.ecoswap.data.message.local.MessageLocalDataSource
import com.darrenthiores.ecoswap.data.message.local.dao.MessageDao
import com.darrenthiores.ecoswap.data.message.local.dao.SqlDelightMessageDao
import com.darrenthiores.ecoswap.database.ChatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MessageLocalModule {
    @Provides
    @Singleton
    fun provideMessageDao(
        database: ChatDatabase
    ): MessageDao {
        return SqlDelightMessageDao(database)
    }

    @Provides
    @Singleton
    fun provideMessageLocalDataSource(
        chatDao: MessageDao
    ): MessageLocalDataSource {
        return MessageLocalDataSource(
            dao = chatDao
        )
    }
}