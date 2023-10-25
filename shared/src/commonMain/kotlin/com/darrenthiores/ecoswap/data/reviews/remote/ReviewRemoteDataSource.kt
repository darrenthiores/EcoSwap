package com.darrenthiores.ecoswap.data.reviews.remote

import com.darrenthiores.ecoswap.data.reviews.remote.service.ReviewService
import com.darrenthiores.ecoswap.data.utils.ApiResponse
import com.darrenthiores.ecoswap.data.utils.tryCatch
import com.darrenthiores.ecoswap.domain.reviews.model.Review
import com.darrenthiores.ecoswap.domain.reviews.model.StoreReview
import com.darrenthiores.ecoswap.utils.dispatchers.DispatchersProvider
import kotlinx.coroutines.withContext

class ReviewRemoteDataSource(
    private val apiService: ReviewService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun getMyReviews(page: Int): ApiResponse<List<Review>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getMyReviews(
                    page = page
                )

                ApiResponse.Success(result)
            }
        }
    }

    suspend fun getUserReviews(
        page: Int,
        id: String
    ): ApiResponse<List<Review>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getUserReviews(
                    page = page,
                    id = id
                )

                ApiResponse.Success(result)
            }
        }
    }

    suspend fun getStoreReviews(
        page: Int,
        id: String
    ): ApiResponse<List<StoreReview>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getStoreReviews(
                    page = page,
                    id = id
                )

                ApiResponse.Success(result)
            }
        }
    }
}