package com.darrenthiores.ecoswap.presentation.profile

import com.darrenthiores.ecoswap.domain.item.model.Item
import com.darrenthiores.ecoswap.domain.reviews.model.Review
import com.darrenthiores.ecoswap.domain.user.model.User
import com.darrenthiores.ecoswap.presentation.utils.PagingState

data class ProfileState(
    val user: User? = null,
    val isUserLoading: Boolean = false,
    val userError: String? = null,
    val items: PagingState<Item> = PagingState(),
    val reviews: PagingState<Review> = PagingState()
)
