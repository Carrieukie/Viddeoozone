package com.karis.videoozone.util

import kotlinx.coroutines.*

object Coroutines {

    //Working in the Main/UI thread
    fun main(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }

    //Working in the IO thread
    fun io(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }

    //Only called once
    fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
        return lazy {
            GlobalScope.async(start = CoroutineStart.LAZY) { block.invoke(this) }
        }
    }
}