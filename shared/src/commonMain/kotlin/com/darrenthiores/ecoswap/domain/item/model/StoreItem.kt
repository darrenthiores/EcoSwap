package com.darrenthiores.ecoswap.domain.item.model

data class StoreItem(
    val id: String,
    val categoryId: String,
    val name: String,
    val imgUrl: List<String>,
    val description: String,
    val location: String,
    val storeName: String,
    val storeImgUrl: String,
    val storeId: String,
    val rating: Double
)
