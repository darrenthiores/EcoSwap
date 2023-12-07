package com.darrenthiores.ecoswap.presentation.inbox

import com.darrenthiores.ecoswap.domain.message.model.Inbox

data class InboxState(
    val inboxes: List<Inbox> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
