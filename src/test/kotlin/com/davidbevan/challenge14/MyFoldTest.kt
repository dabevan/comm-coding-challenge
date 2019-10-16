package com.davidbevan.challenge14

import org.junit.Test

class MyFoldTest {

    @Test
    fun test1() {
        var list1 = listOf(1,2,3,4,5)
        var result = list1.fold(10) { acc, value ->
                acc + value
        }
        println(result)
    }
}