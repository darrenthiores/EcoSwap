package com.darrenthiores.ecoswap.data.reviews.remote.service

import com.darrenthiores.ecoswap.domain.reviews.model.Review
import com.darrenthiores.ecoswap.domain.reviews.model.StoreReview

interface ReviewService {
    suspend fun getMyReviews(
        page: Int
    ): List<Review>
    suspend fun getUserReviews(
        page: Int,
        id: String
    ): List<Review>
    suspend fun getStoreReviews(
        page: Int,
        id: String
    ): List<StoreReview>

    suspend fun addUserReview(
        rating: Int,
        message: String,
        userId: String
    )

    suspend fun addStoreReview(
        rating: Int,
        message: String,
        storeId: String
    )

    companion object {
        private const val BASE_URL = ""
        const val GET_MY_REVIEWS = "$BASE_URL/"
        const val GET_USER_REVIEWS = "$BASE_URL/"
        const val GET_STORE_REVIEWS = "$BASE_URL/"
        const val ADD_USER_REVIEW = "$BASE_URL/"
        const val ADD_STORE_REVIEW = "$BASE_URL/"
    }
}