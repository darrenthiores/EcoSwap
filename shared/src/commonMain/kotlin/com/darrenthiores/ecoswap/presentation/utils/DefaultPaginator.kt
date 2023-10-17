package com.darrenthiores.ecoswap.presentation.utils

import com.darrenthiores.ecoswap.domain.utils.Paginator
import com.darrenthiores.ecoswap.domain.utils.Resource

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Resource<List<Item>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (String?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
): Paginator<Key, Item> {
    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if(isMakingRequest) return

        isMakingRequest = true
        onLoadUpdated(true)

        val result = onRequest(currentKey)

        isMakingRequest = false

        val items = when (result) {
            is Resource.Success -> result.data ?: emptyList()
            else -> {
                onError(result.message)
                onLoadUpdated(false)
                return
            }
        }

        currentKey = getNextKey(items)
        onSuccess(items, currentKey)
        onLoadUpdated(false)
    }

    override fun reset() {
        currentKey = initialKey
    }
}