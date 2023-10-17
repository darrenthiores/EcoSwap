package com.darrenthiores.ecoswap.domain.utils

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}