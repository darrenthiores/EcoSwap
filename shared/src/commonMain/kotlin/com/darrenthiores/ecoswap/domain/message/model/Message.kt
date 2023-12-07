package com.darrenthiores.ecoswap.domain.message.model

import kotlinx.datetime.LocalDateTime

data class Message(
    val id: String,
    val inboxId: String,
    val sentToId: String,
    val sentToUsername: String,
    val sentToImageUrl: String,
    val sentFromId: String,
    val sentFromUsername: String,
    val sentFromImageUrl: String,
    val content: String,
    val mediaUrl: String,
    val date: LocalDateTime,
    val isRead: Boolean
)
