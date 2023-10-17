package com.darrenthiores.ecoswap.presentation.home

import com.darrenthiores.ecoswap.domain.item.use_cases.GetItems
import com.darrenthiores.ecoswap.domain.utils.Resource
import com.darrenthiores.ecoswap.utils.flow.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getItems: GetItems,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(HomeState())
    val state = _state.toCommonStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isRecommendationLoading = true
                )
            }

            val result = getItems(
                page = 1
            )

            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            recommendationError = result.message,
                            isRecommendationLoading = false
                        )
                    }
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            recommendations = result.data ?: emptyList(),
                            isRecommendationLoading = false
                        )
                    }
                }
            }
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    isNearbyLoading = true
                )
            }

            val result = getItems(
                page = 1
            )

            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            nearbyError = result.message,
                            isNearbyLoading = false
                        )
                    }
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            nearby = result.data ?: emptyList(),
                            isNearbyLoading = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.ToggleViewAll -> {
                _state.update {
                    it.copy(
                        viewAll = !it.viewAll
                    )
                }
            }
        }
    }
}