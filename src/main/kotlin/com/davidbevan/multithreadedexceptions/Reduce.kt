package com.davidbevan.multithreadedexceptions

import io.vavr.control.Either
import io.vavr.control.Either.left
import io.vavr.control.Either.right
import kotlin.collections.reduce

fun main() {
    //val numbers: List<Either<Int,Int>> = listOf(right(1),left(2),right(3),right(4),left(5))
    //val numbers: List<Either<Int,Int>> = listOf(left(1), left(2))
    //val numbers: List<Either<Int,Int>> = listOf(left(1))
    val numbers: List<Either<Int,Int>> = listOf(right(1))
    var result = numbers.reduce { acc, n ->
        println("acc=$acc, n=$n")
        var accx = acc.getOrElseGet{acc.left}
        var nx = n.getOrElseGet {n.left}
        println("accx=$accx, nx=$nx")
        Either.right(accx + nx)
    }
    println(result.getOrElse{result.left})
}


//fun main() {
//    val numbers: List<Either<Int,Int>> = listOf(right(1),right(2),right(3),right(4),right(5))
//    println( numbers.reduce { acc, n ->
//        var accx = if(acc.isLeft) acc.left else acc.get()
//        var nx = if(n.isLeft) n.left else n.get()
//        println("accx=$accx, nx=$nx")
//        Either.right(accx + nx)
//    }.get())
//}