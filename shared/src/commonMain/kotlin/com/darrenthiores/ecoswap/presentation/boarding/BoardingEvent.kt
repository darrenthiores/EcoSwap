package com.darrenthiores.ecoswap.presentation.boarding

sealed class BoardingEvent {
    data class UpdateBoarding(val page: Int): BoardingEvent()
    object Start: BoardingEvent()
}