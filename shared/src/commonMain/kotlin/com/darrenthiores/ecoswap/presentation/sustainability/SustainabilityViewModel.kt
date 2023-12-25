package com.darrenthiores.ecoswap.presentation.sustainability

import com.darrenthiores.ecoswap.domain.carbon.model.CarbonView
import com.darrenthiores.ecoswap.domain.carbon.model.FootPrint
import com.darrenthiores.ecoswap.domain.carbon.use_cases.GetChallenges
import com.darrenthiores.ecoswap.domain.carbon.use_cases.GetFootPrint
import com.darrenthiores.ecoswap.domain.utils.Resource
import com.darrenthiores.ecoswap.presentation.utils.DefaultPaginator
import com.darrenthiores.ecoswap.utils.flow.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SustainabilityViewModel(
    private val getFootPrint: GetFootPrint,
    private val getChallenges: GetChallenges,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(SustainabilityState())
    val state = _state.toCommonStateFlow()

    private val challengePaginator = DefaultPaginator(
        initialKey = state.value.challenges.page,
        onLoadUpdated = { isLoading ->
            _state.update {
                it.copy(
                    challenges = it.challenges.copy(
                        isLoading = isLoading
                    )
                )
            }
        },
        onRequest = { nextPage ->
            getChallenges(
                isJoined = false,
                page = nextPage
            )
        },
        getNextKey = {
            state.value.challenges.page + 1
        },
        onError = { error ->
            _state.update {
                it.copy(
                    challenges = it.challenges.copy(
                        error = error,
                        endReached = true
                    )
                )
            }
        },
        onSuccess = { items, key ->
            _state.update {
                it.copy(
                    challenges = it.challenges.copy(
                        items = items,
                        page = key,
                        endReached = items.isEmpty()
                    )
                )
            }
        }
    )

    fun onEvent(event: SustainabilityEvent) {
        when (event) {
            SustainabilityEvent.LoadChallengeNextPage -> {
                viewModelScope.launch {
                    challengePaginator.loadNextItems()
                }
            }
            is SustainabilityEvent.OnViewModeClick -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            viewMode = event.mode,
                            footPrint = null
                        )
                    }

                    fetchFootPrint(
                        viewMode = event.mode
                    )
                }
            }
            is SustainabilityEvent.UpdateFootPrint -> {
                _state.update {
                    val newCarbons = it.footPrint?.carbons?.toMutableList() ?: return
                    newCarbons
                        .add(
                            FootPrint.Carbon(
                                categoryId = event.categoryId,
                                total = event.total
                            )
                        )

                    it.copy(
                        footPrint = it.footPrint.copy(
                            total = it.footPrint.total + event.total,
                            carbons = newCarbons
                        )
                    )
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            fetchFootPrint(
                viewMode = CarbonView.Daily
            )
        }

        viewModelScope.launch {
            challengePaginator.loadNextItems()
        }
    }

    private fun fetchFootPrint(
        viewMode: CarbonView
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            val result = getFootPrint(
                view = viewMode
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
                            footPrint = result.data
                        )
                    }
                }
            }
        }
    }
}
