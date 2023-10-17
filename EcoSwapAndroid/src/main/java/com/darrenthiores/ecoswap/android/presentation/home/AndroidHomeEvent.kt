package com.darrenthiores.ecoswap.android.presentation.home

sealed interface AndroidHomeEvent {
    data class OnSearchChange(
        val text: String
    ): AndroidHomeEvent
}