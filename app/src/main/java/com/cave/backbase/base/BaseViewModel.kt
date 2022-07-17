package com.cave.backbase.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    private val mainScope = CoroutineScope(Dispatchers.Main)

    private val ioScope = CoroutineScope(Dispatchers.Default)

    private val job = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + job
}
