package com.darrenthiores.ecoswap.domain.reviews.repository

import com.darrenthiores.ecoswap.domain.reviews.model.Review
import com.darrenthiores.ecoswap.domain.reviews.model.StoreReview
import com.darrenthiores.ecoswap.domain.utils.Resource

interface ReviewRepository {
    suspend fun getMyReviews(page: Int): Resource<List<Review>>
    suspend fun getUserReviews(
        page: Int,
        id: String
    ): Resource<List<Review>>
    suspend fun getStoreReviews(
        page: Int,
        id: String
    ): Resource<List<StoreReview>>
}