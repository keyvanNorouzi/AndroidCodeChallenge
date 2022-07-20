package com.cave.backbase.utils

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {
    val io: CoroutineContext
    val main: CoroutineContext
}

internal class CoroutineContextProviderImpl : CoroutineContextProvider {
    override val io: CoroutineContext = Dispatchers.IO
    override val main: CoroutineContext = Dispatchers.Main
}

class TestCoroutineContextProvider : CoroutineContextProvider {
    override val io: CoroutineContext = Dispatchers.Unconfined
    override val main: CoroutineContext = Dispatchers.Unconfined
}
