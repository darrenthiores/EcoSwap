package com.darrenthiores.ecoswap.data.carbon.mapper

import com.darrenthiores.ecoswap.domain.carbon.model.FootPrint
import com.darrenthiores.ecoswap.domain.carbon.model.ReducedCarbon

fun ReducedCarbon.toFootPrint(): FootPrint.Carbon {
    return FootPrint.Carbon(
        categoryId = categoryId,
        total = total
    )
}