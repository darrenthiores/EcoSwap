package com.darrenthiores.ecoswap.presentation.store_profile

import com.darrenthiores.ecoswap.domain.item.model.StoreItem
import com.darrenthiores.ecoswap.domain.reviews.model.StoreReview
import com.darrenthiores.ecoswap.domain.store.model.Store
import com.darrenthiores.ecoswap.presentation.utils.PagingState

data class StoreProfileState(
    val store: Store? = null,
    val isStoreLoading: Boolean = false,
    val storeError: String? = null,
    val items: PagingState<StoreItem> = PagingState(),
    val reviews: PagingState<StoreReview> = PagingState(),
    val rating: Int = 0,
    val message: String = "",
    val error: String? = null,
    val showError: Boolean = false,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false
)
