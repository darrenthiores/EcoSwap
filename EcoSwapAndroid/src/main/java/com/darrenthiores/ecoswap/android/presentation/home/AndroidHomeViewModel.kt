package com.darrenthiores.ecoswap.android.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.ecoswap.domain.item.use_cases.GetItems
import com.darrenthiores.ecoswap.presentation.home.HomeEvent
import com.darrenthiores.ecoswap.presentation.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidHomeViewModel @Inject constructor(
    private val getItems: GetItems
): ViewModel() {
    private val viewModel by lazy {
        HomeViewModel(
            getItems = getItems,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    var searchText by mutableStateOf("")
        private set

    fun onEvent(event: AndroidHomeEvent) {
        when (event) {
            is AndroidHomeEvent.OnSearchChange -> {
                searchText = event.text
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        viewModel.onEvent(event)
    }
}