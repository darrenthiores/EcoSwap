package com.darrenthiores.ecoswap.android.presentation.search

import com.darrenthiores.ecoswap.domain.item.model.ItemCategory

sealed interface AndroidSearchEvent {
    data class OnSearchChange(
        val text: String
    ): AndroidSearchEvent

    data class OnSelectCategory(
        val category: ItemCategory?
    ): AndroidSearchEvent

    object OnSearch: AndroidSearchEvent
}