package com.darrenthiores.ecoswap.presentation.item_detail

import com.darrenthiores.ecoswap.domain.item.use_cases.GetItemById
import com.darrenthiores.ecoswap.domain.user.use_cases.GetUserById
import com.darrenthiores.ecoswap.domain.utils.Resource
import com.darrenthiores.ecoswap.utils.flow.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemDetailViewModel(
    private val getItemById: GetItemById,
    private val getUserById: GetUserById,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(ItemDetailState())
    val state = _state.toCommonStateFlow()

    fun onEvent(event: ItemDetailEvent) {
        when (event) {
            ItemDetailEvent.ToggleExpand -> {
                _state.update {
                    it.copy(
                        isExpand = !it.isExpand
                    )
                }
            }
            is ItemDetailEvent.InitialFetch -> {
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

            val result = getItemById(
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

            val id = state.value.item?.userId ?: return@launch
            val result = getUserById(
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
                            user = result.data
                        )
                    }
                }
            }
        }
    }
}