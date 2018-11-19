package com.karumi.kataloginlogoutkotlin

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

interface AsyncExecutor {

    val asyncContext: CoroutineContext
    val uiContext: CoroutineContext

    fun async(block: suspend CoroutineScope.() -> Unit) {
        GlobalScope.launch(uiContext) { block() }
    }

    suspend fun <T> CoroutineScope.await(block: suspend CoroutineScope.() -> T): T {
        return async(asyncContext) { block.invoke(this) }.await()
    }
}
