package com.darrenthiores.ecoswap.android.presentation.store_item_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.ecoswap.domain.item.use_cases.GetStoreItemById
import com.darrenthiores.ecoswap.domain.store.use_cases.GetStoreById
import com.darrenthiores.ecoswap.presentation.store_item_detail.StoreItemDetailEvent
import com.darrenthiores.ecoswap.presentation.store_item_detail.StoreItemDetailViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidStoreItemDetailViewModel @Inject constructor(
    private val getStoreItemById: GetStoreItemById,
    private val getStoreById: GetStoreById,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val viewModel by lazy {
        StoreItemDetailViewModel(
            getStoreItemById = getStoreItemById,
            getStoreById = getStoreById,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    init {
        savedStateHandle.get<String>("itemId")?.let { id ->
            viewModel.onEvent(
                event = StoreItemDetailEvent.InitialFetch(id)
            )
        }
    }

    fun onEvent(event: StoreItemDetailEvent) {
        viewModel.onEvent(event)
    }
}