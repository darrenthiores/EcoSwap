package com.darrenthiores.ecoswap.android.di.core.local.database

import android.app.Application
import com.darrenthiores.ecoswap.data.core.local.driver.DatabaseDriverFactory
import com.darrenthiores.ecoswap.database.ChatDatabase
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatDatabaseModule {

    @Provides
    @Singleton
    fun provideDriver(
        app: Application
    ): SqlDriver {
        return DatabaseDriverFactory(app)
            .create()
    }

    @Provides
    @Singleton
    fun provideDatabase(
        driver: SqlDriver
    ): ChatDatabase {
        return ChatDatabase(driver)
    }
}