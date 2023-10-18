package com.darrenthiores.ecoswap.domain.store.model

data class Store(
    val id: String,
    val categoryId: String,
    val name: String,
    val imgUrl: String,
    val location: String,
    val rating: Double,
    val description: String
)
