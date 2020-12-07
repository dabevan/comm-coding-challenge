package com.davidbevan.lambda

fun main() {
    var a = listOf(1,2,3,4,5)
    println(a.last(::isEven))

}

fun isEven(num: Int) = num%2 == 0
