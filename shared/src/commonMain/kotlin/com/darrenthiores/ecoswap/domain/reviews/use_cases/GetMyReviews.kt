package com.darrenthiores.ecoswap.domain.reviews.use_cases

import com.darrenthiores.ecoswap.domain.reviews.model.Review
import com.darrenthiores.ecoswap.domain.reviews.repository.ReviewRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class GetMyReviews(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(
        page: Int
    ): Resource<List<Review>> {
        return repository
            .getMyReviews(
                page = page
            )
    }
}