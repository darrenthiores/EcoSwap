package com.darrenthiores.ecoswap.domain.carbon.model

data class FootPrint(
    val total: Double,
    val carbons: List<Carbon>
) {
    data class Carbon(
        val categoryId: String,
        val total: Double
    )
}
