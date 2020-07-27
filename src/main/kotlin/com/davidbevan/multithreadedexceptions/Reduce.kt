package com.davidbevan.multithreadedexceptions

fun main() {
    val numbers = listOf(1,2,3,4,5,6)
    val result = numbers.reduce {acc, n ->
         println("acc=$acc, n=$n")
         acc + n
    }
    println(result)
}