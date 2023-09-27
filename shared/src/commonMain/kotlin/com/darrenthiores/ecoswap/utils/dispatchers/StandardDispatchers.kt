package com.darrenthiores.ecoswap.utils.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

expect class StandardDispatchers: DispatchersProvider {
    override val main: CoroutineDispatcher
    override val io: CoroutineDispatcher
    override val default: CoroutineDispatcher
    override val unconfined: CoroutineDispatcher
}