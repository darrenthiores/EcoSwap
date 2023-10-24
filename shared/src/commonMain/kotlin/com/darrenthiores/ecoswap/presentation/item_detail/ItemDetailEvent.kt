package com.darrenthiores.ecoswap.presentation.item_detail

sealed class ItemDetailEvent {
    data class InitialFetch(
        val id: String
    ): ItemDetailEvent()
    object ToggleExpand: ItemDetailEvent()
}
