package com.darrenthiores.ecoswap.domain.reviews.use_cases

import com.darrenthiores.ecoswap.domain.reviews.model.StoreReview
import com.darrenthiores.ecoswap.domain.reviews.repository.ReviewRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class GetStoreReviews(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(
        page: Int,
        id: String
    ): Resource<List<StoreReview>> {
        return repository
            .getStoreReviews(
                page = page,
                id = id
            )
    }
}