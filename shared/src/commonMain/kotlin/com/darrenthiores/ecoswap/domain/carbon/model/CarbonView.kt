package com.darrenthiores.ecoswap.domain.carbon.model

enum class CarbonView(
    val id: String
) {
    Daily(
        id = "1"
    ),
    Weekly(
        id = "2"
    ),
    Monthly(
        id = "3"
    );

    companion object {
        fun getById(id: String): CarbonView {
            return when (id) {
                "1" -> Daily
                "2" -> Weekly
                "3" -> Monthly
                else -> Daily
            }
        }

        fun getByIndex(index: Int): CarbonView {
            return when (index) {
                0 -> Daily
                1 -> Weekly
                2 -> Monthly
                else -> Daily
            }
        }
    }
}