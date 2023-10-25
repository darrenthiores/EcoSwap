package com.darrenthiores.ecoswap.android.di.review

import com.darrenthiores.ecoswap.data.reviews.remote.ReviewRemoteDataSource
import com.darrenthiores.ecoswap.data.reviews.repository.ReviewRepositoryImpl
import com.darrenthiores.ecoswap.domain.reviews.repository.ReviewRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReviewRepositoryModule {

    @Provides
    @Singleton
    fun provideReviewRepository(
        remoteDataSource: ReviewRemoteDataSource
    ): ReviewRepository {
        return ReviewRepositoryImpl(
            remoteDataSource = remoteDataSource
        )
    }
}