package com.darrenthiores.ecoswap.android.presentation.utils

import androidx.compose.ui.graphics.Color

enum class ProgressColor(
    val color: Color
) {
    Transport(
        color = Color(0xFFA6A6FF)
    ),
    Energy(
        color = Color(0xFFA6FFAF)
    ),
    Challenge(
        color = Color(0xFFFFA6C6)
    );

    companion object {
        fun getColorByCategoryId(
            categoryId: String
        ): ProgressColor? {
            return when(categoryId) {
                "1" -> Transport
                "2" -> Energy
                "3" -> Challenge
                else -> null
            }
        }

        fun getColorByIndex(
            index: Int
        ): ProgressColor? {
            return when(index) {
                0 -> Transport
                1 -> Energy
                2 -> Challenge
                else -> null
            }
        }

        val defaultColor: Color = Color(0xFFA6A6FF)
    }
}