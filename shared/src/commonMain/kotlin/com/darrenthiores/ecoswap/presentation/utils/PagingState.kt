package com.darrenthiores.ecoswap.presentation.utils

data class PagingState<T>(
    val isLoading: Boolean = false,
    val items: List<T> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1
)