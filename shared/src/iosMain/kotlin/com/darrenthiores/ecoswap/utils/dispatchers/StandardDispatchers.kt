package com.darrenthiores.ecoswap.utils.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class StandardDispatchers: DispatchersProvider {
    actual override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    actual override val io: CoroutineDispatcher
        get() = Dispatchers.Default
    actual override val default: CoroutineDispatcher
        get() = Dispatchers.Default
    actual override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}