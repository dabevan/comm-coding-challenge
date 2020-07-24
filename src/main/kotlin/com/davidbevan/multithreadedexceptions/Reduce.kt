package com.davidbevan.multithreadedexceptions

fun main() {
    val numbers = listOf(1,2,3,4,5,6)
    val result = numbers.reduce {acc, n ->
         acc + n
    }
    println(result)
}