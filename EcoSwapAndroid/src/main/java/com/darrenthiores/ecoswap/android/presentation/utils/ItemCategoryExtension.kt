package com.darrenthiores.ecoswap.android.presentation.utils

import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.domain.item.model.ItemCategory

fun ItemCategory.getIcon(): Int {
    return when (id) {
        "1" -> R.drawable.clothing_icon
        "2" -> R.drawable.electronics_icon
        "3" -> R.drawable.books_icon
        "4" -> R.drawable.furniture_icon
        "5" -> R.drawable.gaming_icon
        "6" -> R.drawable.sport_icon
        "7" -> R.drawable.edible_icon
        "8" -> R.drawable.living_icon
        else -> R.drawable.ic_no_picture
    }
}