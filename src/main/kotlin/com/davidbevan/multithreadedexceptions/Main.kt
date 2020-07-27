package com.davidbevan.multithreadedexceptions

import io.vavr.control.Either
import kotlin.random.Random
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    println("Result=${launchMethod()}")
}

fun launchMethod(): String? {
    val numbers = listOf(1,2,3,4,5,6)
    val response = runBlocking {
        return@runBlocking numbers.map { number ->
            async {
                try {
                    threadMethod(number)
                } catch (e: Exception) {
                    println("Exception $e")
                    Either.left<String,String>("ERROR3")
                }
            }
        }.map { it.await() }.combine()
    }
    println()
    return response?.get()
}

suspend fun threadMethod(number: Int): Either<String, String> {
    val sleep = Random.nextLong(0, 5000)
    delay(sleep)
    println()
    println("number=$number, sleep=$sleep")
    var result = 100/(number-3)
    if(number==1) { println("Error1"); return Either.left("ERROR1") }
    if(number==4) { println("Error4"); return Either.left("ERROR4") }
    println("OK")
    return Either.right("$number")
}

fun List<Either<String, String>>.combine(): Either<String, String>? {
    println()
    println()
    var combinedResult: Either<String, String>? = null

    map {placementResult ->
            val accString = if (combinedResult == null) { "" } else { combinedResult!!.get() + ", " }
            if (placementResult.isLeft) {
                combinedResult = Either.right(accString + placementResult.left)
                println("Log Error=${placementResult.left}")
            }
            if (placementResult.isRight) {
                combinedResult = Either.right(accString + placementResult.get())
            }
    }

    return combinedResult
}
