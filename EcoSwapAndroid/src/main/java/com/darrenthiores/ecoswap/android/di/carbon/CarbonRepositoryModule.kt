package com.darrenthiores.ecoswap.android.di.carbon

import com.darrenthiores.ecoswap.data.carbon.local.CarbonLocalDataSource
import com.darrenthiores.ecoswap.data.carbon.remote.source.CarbonRemoteDataSource
import com.darrenthiores.ecoswap.data.carbon.repository.CarbonRepositoryImpl
import com.darrenthiores.ecoswap.domain.carbon.repository.CarbonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CarbonRepositoryModule {

    @Provides
    @Singleton
    fun provideCarbonRepository(
        remoteDataSource: CarbonRemoteDataSource,
        localDataSource: CarbonLocalDataSource
    ): CarbonRepository {
        return CarbonRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )
    }
}