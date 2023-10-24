package com.darrenthiores.ecoswap.android.presentation.item_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.ecoswap.domain.item.use_cases.GetItemById
import com.darrenthiores.ecoswap.domain.user.use_cases.GetUserById
import com.darrenthiores.ecoswap.presentation.item_detail.ItemDetailEvent
import com.darrenthiores.ecoswap.presentation.item_detail.ItemDetailViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidItemDetailViewModel @Inject constructor(
    private val getItemById: GetItemById,
    private val getUserById: GetUserById,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val viewModel by lazy {
        ItemDetailViewModel(
            getItemById = getItemById,
            getUserById = getUserById,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    init {
        savedStateHandle.get<String>("itemId")?.let { id ->
            viewModel.onEvent(
                event = ItemDetailEvent.InitialFetch(id)
            )
        }
    }

    fun onEvent(event: ItemDetailEvent) {
        viewModel.onEvent(event)
    }
}