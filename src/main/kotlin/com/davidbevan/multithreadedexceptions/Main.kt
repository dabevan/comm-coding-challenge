package com.davidbevan.multithreadedexceptions

import io.vavr.control.Either
import kotlin.random.Random
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    println("Result=${launchMethod()}")
}

fun launchMethod(): String {
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
    return response.get()
}

suspend fun threadMethod(number: Int): Either<String, String> {
    val sleep = Random.nextLong(0, 5000)
    delay(sleep)
    println()
    println("number=$number, sleep=$sleep")
    println("100/(number-3)=${100/(number-3)}")
    if(number==4) { println("Error4"); return Either.left("ERROR4") }
    return Either.right("$number")
}

fun List<Either<String, String>>.combine(): Either<String, String> {
    println()
    println()
    return reduce { acc, n ->
        when {
            acc.isLeft -> {println("Log.error(${acc.left})")
                acc}
            n.isLeft -> { println("Log.error(${n.left})")
                acc}
            else -> Either.right<String, String>("${acc.get()}, ${n.get()}")
        }
    }
}
