package com.darrenthiores.ecoswap.android.presentation.components.buttons

import androidx.compose.ui.graphics.Color

sealed interface ButtonType {
    object Fill: ButtonType
    data class Outline(val background: Color? = null): ButtonType
}