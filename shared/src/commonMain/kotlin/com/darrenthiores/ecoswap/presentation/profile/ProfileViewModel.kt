package com.darrenthiores.ecoswap.presentation.profile

import com.darrenthiores.ecoswap.domain.item.use_cases.GetMyItems
import com.darrenthiores.ecoswap.domain.reviews.use_cases.GetMyReviews
import com.darrenthiores.ecoswap.domain.user.use_cases.GetUser
import com.darrenthiores.ecoswap.domain.utils.Resource
import com.darrenthiores.ecoswap.presentation.utils.DefaultPaginator
import com.darrenthiores.ecoswap.utils.flow.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUser: GetUser,
    private val getItems: GetMyItems,
    private val getReviews: GetMyReviews,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.toCommonStateFlow()

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
            getItems(
                page = nextPage,
            )
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
            getReviews(
                page = nextPage,
            )
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

    init {
        fetchUser()

        viewModelScope.launch {
            itemPaginator.loadNextItems()
        }

        viewModelScope.launch {
            reviewPaginator.loadNextItems()
        }
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.LoadItemNextPage -> {
                viewModelScope.launch {
                    itemPaginator.loadNextItems()
                }
            }
            ProfileEvent.LoadReviewNextPage -> {
                viewModelScope.launch {
                    reviewPaginator.loadNextItems()
                }
            }
        }
    }

    private fun fetchUser() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isUserLoading = true
                )
            }

            when (val result = getUser()) {
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
                }
            }
        }
    }
}