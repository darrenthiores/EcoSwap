package com.darrenthiores.ecoswap.presentation.other_profile

import com.darrenthiores.ecoswap.domain.item.use_cases.GetUserItems
import com.darrenthiores.ecoswap.domain.reviews.use_cases.AddReview
import com.darrenthiores.ecoswap.domain.reviews.use_cases.GetUserReviews
import com.darrenthiores.ecoswap.domain.user.use_cases.GetUserById
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

class OtherProfileViewModel(
    private val getUserById: GetUserById,
    private val getUserItems: GetUserItems,
    private val getUserReviews: GetUserReviews,
    private val addReview: AddReview,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(OtherProfileState())
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
            state.value.user?.id?.let { id ->
                getUserItems(
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
            state.value.user?.id?.let { id ->
                getUserReviews(
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

    fun onEvent(event: OtherProfileEvent) {
        when (event) {
            OtherProfileEvent.LoadItemNextPage -> {
                viewModelScope.launch {
                    itemPaginator.loadNextItems()
                }
            }
            OtherProfileEvent.LoadReviewNextPage -> {
                viewModelScope.launch {
                    reviewPaginator.loadNextItems()
                }
            }
            OtherProfileEvent.AddReview -> {
                addReview()
            }
            is OtherProfileEvent.OnMessageChange -> {
                _state.update {
                    it.copy(
                        message = event.message
                    )
                }
            }
            is OtherProfileEvent.OnPickRating -> {
                _state.update {
                    it.copy(
                        rating = event.rating
                    )
                }
            }
            OtherProfileEvent.Reset -> {
                _state.update {
                    it.copy(
                        isSuccess = false
                    )
                }
            }
            OtherProfileEvent.HideError -> {
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
                    isUserLoading = true
                )
            }

            val result = getUserById(
                id = id
            )

            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isUserLoading = false,
                            userError = result.message
                        )
                    }
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isUserLoading = false,
                            user = result.data
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

            val result = addReview(
                rating = state.value.rating,
                message = state.value.message,
                userId = state.value.user?.id ?: return@launch
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

                    _uiEvent.send(
                        UiEvent.Success
                    )

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