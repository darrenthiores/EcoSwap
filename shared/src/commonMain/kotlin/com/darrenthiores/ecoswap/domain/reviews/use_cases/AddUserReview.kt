package com.darrenthiores.ecoswap.domain.reviews.use_cases

import com.darrenthiores.ecoswap.domain.reviews.repository.ReviewRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class AddUserReview(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(
        rating: Int,
        message: String,
        userId: String
    ): Resource<Unit> {
        return repository
            .addUserReview(
                rating = rating,
                message = message,
                userId = userId
            )
    }
}