package com.darrenthiores.ecoswap.presentation.store_item_detail

import com.darrenthiores.ecoswap.domain.item.model.StoreItem
import com.darrenthiores.ecoswap.domain.store.model.Store

data class StoreItemDetailState(
    val item: StoreItem? = null,
    val store: Store? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isExpand: Boolean = false
)
