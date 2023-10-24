package com.darrenthiores.ecoswap.presentation.search

import com.darrenthiores.ecoswap.domain.item.use_cases.SearchItems
import com.darrenthiores.ecoswap.domain.store.use_cases.SearchStores
import com.darrenthiores.ecoswap.presentation.utils.DefaultPaginator
import com.darrenthiores.ecoswap.utils.flow.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchItems: SearchItems,
    private val searchStores: SearchStores,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(SearchState())
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
            searchItems(
                page = nextPage,
                text = state.value.searchText,
                categoryId = state.value.category?.id
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

    private val storePaginator = DefaultPaginator(
        initialKey = state.value.stores.page,
        onLoadUpdated = { isLoading ->
            _state.update {
                it.copy(
                    stores = it.stores.copy(
                        isLoading = isLoading
                    )
                )
            }
        },
        onRequest = { nextPage ->
            searchStores(
                page = nextPage,
                text = state.value.searchText,
                categoryId = state.value.category?.id
            )
        },
        getNextKey = {
            state.value.stores.page + 1
        },
        onError = { error ->
            _state.update {
                it.copy(
                    stores = it.stores.copy(
                        error = error,
                        endReached = true
                    )
                )
            }
        },
        onSuccess = { items, key ->
            _state.update {
                it.copy(
                    stores = it.stores.copy(
                        items = items,
                        page = key,
                        endReached = items.isEmpty()
                    )
                )
            }
        }
    )

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnSearch -> {
                if (
                    event.text == state.value.searchText
                ) {
                    return
                }

                _state.update {
                    it.copy(
                        searchText = event.text,
                        items = it.items.copy(
                            page = 1,
                            endReached = false
                        ),
                        stores = it.stores.copy(
                            page = 1,
                            endReached = false
                        )
                    )
                }

                viewModelScope.launch {
                    val item = async { itemPaginator.loadNextItems() }
                    val store = async { storePaginator.loadNextItems() }

                    awaitAll(item, store)
                }
            }
            is SearchEvent.OnSelectCategory -> {
                if (event.category == state.value.category) {
                    return
                }

                _state.update {
                    it.copy(
                        category = event.category,
                        items = it.items.copy(
                            page = 1,
                            endReached = false
                        ),
                        stores = it.stores.copy(
                            page = 1,
                            endReached = false
                        )
                    )
                }

                viewModelScope.launch {
                    val item = async { itemPaginator.loadNextItems() }
                    val store = async { storePaginator.loadNextItems() }

                    awaitAll(item, store)
                }
            }
            SearchEvent.LoadItemNextPage -> {
                viewModelScope.launch {
                    itemPaginator.loadNextItems()
                }
            }
            SearchEvent.LoadStoreNextPage -> {
                viewModelScope.launch {
                    storePaginator.loadNextItems()
                }
            }
        }
    }
}