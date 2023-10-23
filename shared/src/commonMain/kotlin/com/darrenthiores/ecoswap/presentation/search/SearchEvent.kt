package com.darrenthiores.ecoswap.presentation.search

import com.darrenthiores.ecoswap.domain.item.model.ItemCategory

sealed class SearchEvent {
    data class OnSearch(
        val text: String
    ): SearchEvent()

    data class OnSelectCategory(
        val category: ItemCategory?
    ): SearchEvent()

    object LoadItemNextPage: SearchEvent()
    object LoadStoreNextPage: SearchEvent()
}
