package com.darrenthiores.ecoswap.domain.item.model

data class Item(
    val id: String,
    val categoryId: String,
    val name: String,
    val imgUrl: String,
    val location: String,
    val username: String,
    val userImgUrl: String,
    val userId: String,
    val rating: Double
)
