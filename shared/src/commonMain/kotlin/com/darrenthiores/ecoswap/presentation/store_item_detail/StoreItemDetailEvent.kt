package com.darrenthiores.ecoswap.presentation.store_item_detail

sealed class StoreItemDetailEvent {
    data class InitialFetch(
        val id: String
    ): StoreItemDetailEvent()
    object ToggleExpand: StoreItemDetailEvent()
}
