package com.darrenthiores.ecoswap.domain.item.model

data class Item(
    val id: String,
    val categoryId: String,
    val name: String,
    val imgUrl: List<String>,
    val description: String,
    val conditionId: String,
    val total: Int,
    val brand: String,
    val location: String,
    val username: String,
    val userImgUrl: String,
    val userId: String,
    val rating: Double,
    val statusId: String
)
