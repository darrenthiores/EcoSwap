package com.darrenthiores.ecoswap.android.di.carbon

import com.darrenthiores.ecoswap.data.carbon.local.CarbonLocalDataSource
import com.darrenthiores.ecoswap.data.carbon.local.dao.CarbonDao
import com.darrenthiores.ecoswap.data.carbon.local.dao.SqlDelightCarbonDao
import com.darrenthiores.ecoswap.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CarbonLocalModule {
    @Provides
    @Singleton
    fun provideCarbonDao(
        database: AppDatabase
    ): CarbonDao {
        return SqlDelightCarbonDao(database)
    }

    @Provides
    @Singleton
    fun provideCarbonLocalDataSource(
        dao: CarbonDao
    ): CarbonLocalDataSource {
        return CarbonLocalDataSource(
            dao = dao
        )
    }
}