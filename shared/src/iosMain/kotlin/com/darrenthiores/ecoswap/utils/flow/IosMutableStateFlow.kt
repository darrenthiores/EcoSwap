package com.darrenthiores.ecoswap.utils.flow

import kotlinx.coroutines.flow.MutableStateFlow

class IosMutableStateFlow<T>(
    initialValue: T
): CommonMutableStateFlow<T>(
    MutableStateFlow(initialValue)
)