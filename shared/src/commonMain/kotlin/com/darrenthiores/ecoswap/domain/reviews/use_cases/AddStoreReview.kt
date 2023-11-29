package com.darrenthiores.ecoswap.domain.reviews.use_cases

import com.darrenthiores.ecoswap.domain.reviews.repository.ReviewRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class AddStoreReview(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(
        rating: Int,
        message: String,
        storeId: String
    ): Resource<Unit> {
        return repository
            .addStoreReview(
                rating = rating,
                message = message,
                storeId = storeId
            )
    }
}