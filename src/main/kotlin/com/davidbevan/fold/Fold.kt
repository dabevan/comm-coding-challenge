package com.davidbevan.fold

fun main() {
    var a = listOf(1,2,3,4,5)
    println(a.fold(1 ) {acc, element -> acc+element})

}