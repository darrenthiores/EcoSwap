package com.darrenthiores.ecoswap.utils.flow

import kotlinx.coroutines.flow.MutableStateFlow

expect class CommonMutableStateFlow<T>(
    flow: MutableStateFlow<T>
): MutableStateFlow<T>

fun <T> MutableStateFlow<T>.toCommonMutableStateFlow() = CommonMutableStateFlow(this)