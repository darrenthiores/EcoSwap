package com.darrenthiores.ecoswap.domain.reviews.use_cases

import com.darrenthiores.ecoswap.domain.reviews.model.Review
import com.darrenthiores.ecoswap.domain.reviews.repository.ReviewRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class GetUserReviews(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(
        page: Int,
        id: String
    ): Resource<List<Review>> {
        return repository
            .getUserReviews(
                page = page,
                id = id
            )
    }
}