package com.davidbevan.coroutinesdemo

import kotlinx.coroutines.*
import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.coroutines.coroutineContext

fun main() = runBlocking<Unit> {
    println("START")
    val deferred1 = async { hello("Dave") }
    val deferred2 = async { hello("Fred") }
    delay(100)
    deferred1.await()
    deferred2.await()
    println("END ")
}


suspend fun hello(name: String) {
    println(coroutineContext[Job]!!.isActive)
    println("Hello $name")
    delay(1000)
    println("Goodbye $name")
}