package com.darrenthiores.ecoswap.presentation.message

sealed class MessageEvent {
    data class OnMessageChange(val newText: String): MessageEvent()
    object SendMessage: MessageEvent()
}
