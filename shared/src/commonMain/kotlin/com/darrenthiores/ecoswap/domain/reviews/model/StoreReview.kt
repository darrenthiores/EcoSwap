package com.darrenthiores.ecoswap.domain.reviews.model

data class StoreReview(
    val id: String,
    val review: String,
    val storeId: String,
    val userId: String,
    val username: String,
    val userImgUrl: String,
    val rating: Double,
    val date: Long
)
