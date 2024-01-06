package com.darrenthiores.ecoswap.presentation.store_profile

import com.darrenthiores.ecoswap.domain.item.use_cases.GetStoreItems
import com.darrenthiores.ecoswap.domain.reviews.use_cases.AddStoreReview
import com.darrenthiores.ecoswap.domain.reviews.use_cases.GetStoreReviews
import com.darrenthiores.ecoswap.domain.store.use_cases.GetStoreById
import com.darrenthiores.ecoswap.domain.utils.Resource
import com.darrenthiores.ecoswap.domain.utils.UiEvent
import com.darrenthiores.ecoswap.presentation.utils.DefaultPaginator
import com.darrenthiores.ecoswap.utils.flow.toCommonFlow
import com.darrenthiores.ecoswap.utils.flow.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StoreProfileViewModel(
    private val getStoreById: GetStoreById,
    private val getStoreItems: GetStoreItems,
    private val getStoreReviews: GetStoreReviews,
    private val addStoreReview: AddStoreReview,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(StoreProfileState())
    val state = _state.toCommonStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent
        .receiveAsFlow()
        .toCommonFlow()

    private val itemPaginator = DefaultPaginator(
        initialKey = state.value.items.page,
        onLoadUpdated = { isLoading ->
            _state.update {
                it.copy(
                    items = it.items.copy(
                        isLoading = isLoading
                    )
                )
            }
        },
        onRequest = { nextPage ->
            state.value.store?.id?.let { id ->
                getStoreItems(
                    page = nextPage,
                    id = id
                )
            } ?: Resource.Error("Unknown Error Just Occurred")
        },
        getNextKey = {
            state.value.items.page + 1
        },
        onError = { error ->
            _state.update {
                it.copy(
                    items = it.items.copy(
                        error = error,
                        endReached = true
                    )
                )
            }
        },
        onSuccess = { items, key ->
            _state.update {
                it.copy(
                    items = it.items.copy(
                        items = items,
                        page = key,
                        endReached = items.isEmpty()
                    )
                )
            }
        }
    )

    private val reviewPaginator = DefaultPaginator(
        initialKey = state.value.reviews.page,
        onLoadUpdated = { isLoading ->
            _state.update {
                it.copy(
                    reviews = it.reviews.copy(
                        isLoading = isLoading
                    )
                )
            }
        },
        onRequest = { nextPage ->
            state.value.store?.id?.let { id ->
                getStoreReviews(
                    page = nextPage,
                    id = id
                )
            } ?: Resource.Error("Unknown Error Just Occurred")
        },
        getNextKey = {
            state.value.reviews.page + 1
        },
        onError = { error ->
            _state.update {
                it.copy(
                    reviews = it.reviews.copy(
                        error = error,
                        endReached = true
                    )
                )
            }
        },
        onSuccess = { items, key ->
            _state.update {
                it.copy(
                    reviews = it.reviews.copy(
                        items = items,
                        page = key,
                        endReached = items.isEmpty()
                    )
                )
            }
        }
    )

    fun initialFetch(id: String) {
        fetchUser(
            id = id
        )
    }

    fun onEvent(event: StoreProfileEvent) {
        when (event) {
            StoreProfileEvent.LoadItemNextPage -> {
                viewModelScope.launch {
                    itemPaginator.loadNextItems()
                }
            }
            StoreProfileEvent.LoadReviewNextPage -> {
                viewModelScope.launch {
                    reviewPaginator.loadNextItems()
                }
            }
            StoreProfileEvent.AddReview -> {
                addReview()
            }
            is StoreProfileEvent.OnMessageChange -> {
                _state.update {
                    it.copy(
                        message = event.message
                    )
                }
            }
            is StoreProfileEvent.OnPickRating -> {
                _state.update {
                    it.copy(
                        rating = event.rating
                    )
                }
            }
            StoreProfileEvent.Reset -> {
                _state.update {
                    it.copy(
                        isSuccess = false
                    )
                }
            }
            StoreProfileEvent.HideError -> {
                _state.update {
                    it.copy(
                        showError = false
                    )
                }
            }
        }
    }

    private fun fetchUser(
        id: String
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isStoreLoading = true
                )
            }

            val result = getStoreById(
                id = id
            )

            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isStoreLoading = false,
                            storeError = result.message
                        )
                    }
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isStoreLoading = false,
                            store = result.data
                        )
                    }

                    viewModelScope.launch {
                        itemPaginator.loadNextItems()
                    }

                    viewModelScope.launch {
                        reviewPaginator.loadNextItems()
                    }
                }
            }
        }
    }

    private fun addReview() {
        viewModelScope.launch {
            if (state.value.rating == 0) {
                return@launch
            }

            if (state.value.message.isEmpty()) {
                return@launch
            }

            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            val result = addStoreReview(
                rating = state.value.rating,
                message = state.value.message,
                storeId = state.value.store?.id ?: return@launch
            )

            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message,
                            showError = true
                        )
                    }
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true
                        )
                    }

                    viewModelScope.launch {
                        _uiEvent.send(
                            UiEvent.Success
                        )
                    }

                    _state.update {
                        it.copy(
                            rating = 0,
                            message = ""
                        )
                    }
                }
            }
        }
    }
}