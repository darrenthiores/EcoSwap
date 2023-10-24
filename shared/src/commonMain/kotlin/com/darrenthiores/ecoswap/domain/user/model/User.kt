package com.darrenthiores.ecoswap.domain.user.model

data class User(
    val id: String,
    val name: String,
    val imageUrl: String,
    val email: String,
    val rating: Double,
    val totalReview: Int,
    val location: String
)
