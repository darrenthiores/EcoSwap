package com.darrenthiores.ecoswap.android.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.ecoswap.domain.core.utils.Constant
import com.darrenthiores.ecoswap.domain.item.use_cases.SearchItems
import com.darrenthiores.ecoswap.domain.store.use_cases.SearchStores
import com.darrenthiores.ecoswap.presentation.search.SearchEvent
import com.darrenthiores.ecoswap.presentation.search.SearchViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidSearchViewModel @Inject constructor(
    private val searchItems: SearchItems,
    private val searchStores: SearchStores,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val viewModel by lazy {
        SearchViewModel(
            searchItems = searchItems,
            searchStores = searchStores,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    var searchText by mutableStateOf("")
        private set

    init {
        savedStateHandle.get<String>("searchText")?.let { text ->
            searchText = text

            viewModel.onEvent(
                SearchEvent.OnSearch(
                    text = text
                )
            )
        }

        savedStateHandle.get<String>("categoryId")?.let { id ->
            viewModel.onEvent(
                SearchEvent.OnSelectCategory(
                    category = Constant.categoryById(
                        id = id
                    )
                )
            )
        }
    }

    fun onEvent(event: AndroidSearchEvent) {
        when (event) {
            is AndroidSearchEvent.OnSearchChange -> {
                searchText = event.text
            }
            AndroidSearchEvent.OnSearch -> {
                viewModel.onEvent(
                    SearchEvent.OnSearch(
                        text = searchText
                    )
                )
            }
            is AndroidSearchEvent.OnSelectCategory -> {
                viewModel.onEvent(
                    SearchEvent.OnSelectCategory(
                        category = event.category
                    )
                )
            }
        }
    }

    fun onEvent(event: SearchEvent) {
        viewModel.onEvent(event)
    }
}