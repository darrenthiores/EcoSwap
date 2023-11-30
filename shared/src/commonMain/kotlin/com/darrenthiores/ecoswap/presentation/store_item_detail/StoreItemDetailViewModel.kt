package com.darrenthiores.ecoswap.presentation.store_item_detail

import com.darrenthiores.ecoswap.domain.item.use_cases.GetStoreItemById
import com.darrenthiores.ecoswap.domain.store.use_cases.GetStoreById
import com.darrenthiores.ecoswap.domain.utils.Resource
import com.darrenthiores.ecoswap.utils.flow.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StoreItemDetailViewModel(
    private val getStoreItemById: GetStoreItemById,
    private val getStoreById: GetStoreById,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(StoreItemDetailState())
    val state = _state.toCommonStateFlow()

    fun onEvent(event: StoreItemDetailEvent) {
        when (event) {
            StoreItemDetailEvent.ToggleExpand -> {
                _state.update {
                    it.copy(
                        isExpand = !it.isExpand
                    )
                }
            }
            is StoreItemDetailEvent.InitialFetch -> {
                fetchItem(id = event.id)
            }
        }
    }

    private fun fetchItem(
        id: String
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            val result = getStoreItemById(
                id = id
            )

            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            item = result.data
                        )
                    }

                    fetchUser()
                }
            }
        }
    }

    private fun fetchUser() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            val id = state.value.item?.storeId ?: return@launch
            val result = getStoreById(
                id = id
            )

            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            store = result.data
                        )
                    }
                }
            }
        }
    }
}