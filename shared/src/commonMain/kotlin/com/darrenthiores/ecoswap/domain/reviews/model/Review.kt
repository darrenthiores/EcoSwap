package com.darrenthiores.ecoswap.domain.reviews.model

data class Review(
    val id: String,
    val review: String,
    val reviewedUserId: String,
    val userId: String,
    val username: String,
    val userImgUrl: String,
    val rating: Double,
    val date: Long
)
