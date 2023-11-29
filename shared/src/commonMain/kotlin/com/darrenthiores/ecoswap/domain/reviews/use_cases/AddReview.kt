package com.darrenthiores.ecoswap.domain.reviews.use_cases

import com.darrenthiores.ecoswap.domain.reviews.repository.ReviewRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class AddReview(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(
        rating: Int,
        message: String,
        userId: String
    ): Resource<Unit> {
        return repository
            .addReview(
                rating = rating,
                message = message,
                userId = userId
            )
    }
}