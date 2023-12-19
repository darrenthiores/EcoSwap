package com.darrenthiores.ecoswap.android.di.carbon

import com.darrenthiores.ecoswap.data.carbon.remote.service.CarbonService
import com.darrenthiores.ecoswap.data.carbon.remote.service.KtorCarbonService
import com.darrenthiores.ecoswap.data.carbon.remote.source.CarbonRemoteDataSource
import com.darrenthiores.ecoswap.utils.dispatchers.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CarbonRemoteModule {
    @Provides
    @Singleton
    fun provideCarbonService(
        client: HttpClient
    ): CarbonService {
        return KtorCarbonService(client)
    }

    @Provides
    @Singleton
    fun provideCarbonRemoteDataSource(
        service: CarbonService,
        dispatchers: DispatchersProvider
    ): CarbonRemoteDataSource {
        return CarbonRemoteDataSource(
            apiService = service,
            dispatchers = dispatchers
        )
    }
}