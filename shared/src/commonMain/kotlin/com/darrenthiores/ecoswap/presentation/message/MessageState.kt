package com.darrenthiores.ecoswap.presentation.message

import com.darrenthiores.ecoswap.domain.message.model.Message
import com.darrenthiores.ecoswap.domain.user.model.User

data class MessageState(
    val messages: List<Message> = emptyList(),
    val messagesLoading: Boolean = false,
    val messagesError: String? = null,
    val user: User? = null,
    val userLoading: Boolean = false,
    val userError: String? = null,
    val newMessage: String = "",
    val error: String? = null,
    val isSendingMessage: Boolean = false
)
