package com.darrenthiores.ecoswap.domain.message.model

data class Inbox(
    val id: String,
    val sentFromId: String,
    val sentToId: String,
    val sentToUsername: String,
    val sentToImageUrl: String,
    val lastMessage: String,
    val lastSendUserId: String
)
