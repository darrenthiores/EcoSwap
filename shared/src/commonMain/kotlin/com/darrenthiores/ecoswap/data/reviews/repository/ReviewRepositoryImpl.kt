package com.darrenthiores.ecoswap.data.reviews.repository

import com.darrenthiores.ecoswap.data.reviews.remote.ReviewRemoteDataSource
import com.darrenthiores.ecoswap.data.utils.ApiResponse
import com.darrenthiores.ecoswap.domain.reviews.model.Review
import com.darrenthiores.ecoswap.domain.reviews.model.StoreReview
import com.darrenthiores.ecoswap.domain.reviews.repository.ReviewRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class ReviewRepositoryImpl(
    private val remoteDataSource: ReviewRemoteDataSource
): ReviewRepository {
    override suspend fun getMyReviews(page: Int): Resource<List<Review>> {
        val result = remoteDataSource
            .getMyReviews(
                page = page
            )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data)
        }
    }

    override suspend fun getUserReviews(page: Int, id: String): Resource<List<Review>> {
        val result = remoteDataSource
            .getUserReviews(
                page = page,
                id = id
            )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data)
        }
    }

    override suspend fun getStoreReviews(page: Int, id: String): Resource<List<StoreReview>> {
        val result = remoteDataSource
            .getStoreReviews(
                page = page,
                id = id
            )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data)
        }
    }

    override suspend fun addReview(
        rating: Int,
        message: String,
        userId: String
    ): Resource<Unit> {
        val result = remoteDataSource
            .addUserReview(
                rating = rating,
                message = message,
                userId = userId
            )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data)
        }
    }
}