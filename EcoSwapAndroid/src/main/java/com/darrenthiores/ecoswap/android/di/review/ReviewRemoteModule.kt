package com.darrenthiores.ecoswap.android.di.review

import com.darrenthiores.ecoswap.data.reviews.remote.ReviewRemoteDataSource
import com.darrenthiores.ecoswap.data.reviews.remote.service.KtorReviewService
import com.darrenthiores.ecoswap.data.reviews.remote.service.ReviewService
import com.darrenthiores.ecoswap.utils.dispatchers.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReviewRemoteModule {
    @Provides
    @Singleton
    fun provideReviewService(
        client: HttpClient
    ): ReviewService {
        return KtorReviewService(client)
    }

    @Provides
    @Singleton
    fun provideReviewRemoteDataSource(
        service: ReviewService,
        dispatchers: DispatchersProvider
    ): ReviewRemoteDataSource {
        return ReviewRemoteDataSource(
            apiService = service,
            dispatchers = dispatchers
        )
    }
}