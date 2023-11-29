package com.darrenthiores.ecoswap.data.reviews.remote.service

import com.darrenthiores.ecoswap.domain.core.utils.Dummy
import com.darrenthiores.ecoswap.domain.reviews.model.Review
import com.darrenthiores.ecoswap.domain.reviews.model.StoreReview
import io.ktor.client.HttpClient

class KtorReviewService(
    private val client: HttpClient
): ReviewService {
    override suspend fun getMyReviews(page: Int): List<Review> {
        return Dummy.reviews
    }

    override suspend fun getUserReviews(
        page: Int,
        id: String
    ): List<Review> {
        return Dummy
            .reviews
            .filter {
                it.reviewedUserId == id
            }
    }

    override suspend fun getStoreReviews(
        page: Int,
        id: String
    ): List<StoreReview> {
        return Dummy
            .storeReviews
            .filter {
                it.storeId == id
            }
    }

    override suspend fun addUserReview(
        rating: Int,
        message: String,
        userId: String
    ) {
        // should add review
    }

}