package com.darrenthiores.ecoswap.android.di.review

import com.darrenthiores.ecoswap.domain.reviews.repository.ReviewRepository
import com.darrenthiores.ecoswap.domain.reviews.use_cases.AddReview
import com.darrenthiores.ecoswap.domain.reviews.use_cases.GetMyReviews
import com.darrenthiores.ecoswap.domain.reviews.use_cases.GetStoreReviews
import com.darrenthiores.ecoswap.domain.reviews.use_cases.GetUserReviews
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ReviewUseCasesModule {
    @Provides
    @ViewModelScoped
    fun provideGetMyReviewsUseCase(
        repository: ReviewRepository
    ): GetMyReviews {
        return GetMyReviews(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetUserReviewsUseCase(
        repository: ReviewRepository
    ): GetUserReviews {
        return GetUserReviews(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetStoreReviewsUseCase(
        repository: ReviewRepository
    ): GetStoreReviews {
        return GetStoreReviews(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideAddReviewUseCase(
        repository: ReviewRepository
    ): AddReview {
        return AddReview(
            repository = repository
        )
    }
}