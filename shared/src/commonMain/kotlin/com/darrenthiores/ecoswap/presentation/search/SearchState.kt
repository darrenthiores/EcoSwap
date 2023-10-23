package com.darrenthiores.ecoswap.presentation.search

import com.darrenthiores.ecoswap.domain.item.model.Item
import com.darrenthiores.ecoswap.domain.item.model.ItemCategory
import com.darrenthiores.ecoswap.domain.store.model.Store
import com.darrenthiores.ecoswap.presentation.utils.PagingState

data class SearchState(
    val searchText: String = "",
    val category: ItemCategory? = null,
    val items: PagingState<Item> = PagingState(),
    val stores: PagingState<Store> = PagingState()
)
