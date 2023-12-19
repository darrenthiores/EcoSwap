package com.darrenthiores.ecoswap.domain.carbon.model

data class ReducedCarbon(
    val id: String,
    val categoryId: String,
    val taskId: String,
    val taskTitle: String,
    val date: Long,
    val total: Double
)
