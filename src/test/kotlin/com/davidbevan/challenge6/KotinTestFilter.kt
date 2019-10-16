package com.davidbevan.challenge6

import io.kotlintest.matchers.*
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

//Note, our project is now using Kotlin.test for testing so I thought I'd write a Kotlin.test
//version of the unit tests. These are exactly the same test conditions as FilterTest (which is JUnit)

class	KotlinTestFilter(private val hello: String) :	StringSpec()	{
    init {

        var filters = Filters()

        "test filter numbers less than 5 where 2 numbers qualify" {
            println("hello:$hello")
            filters.myFilter(arrayOf(3, 4, 5, 6, 7, 8), (filters.numberIsLessThan5)) shouldBe arrayOf(3,4)
        }

        "test filter numbers less than 5 where 0 numbers qualify" {
            filters.myFilter(arrayOf(5, 6, 7, 8), (filters.numberIsLessThan5)).size shouldBe 0
        }

        "test filter numbers less than 5 where all numbers qualify" {
            filters.myFilter(arrayOf(1,2,3,4), (filters.numberIsLessThan5)) shouldBe arrayOf(1,2,3,4)
        }

        "test filter even numbers in 1 to 9" {
            filters.myFilter(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9), (filters.numberIsEven)) shouldBe arrayOf(2, 4, 6, 8)
        }

        "test filter odd numbers in 1 to 9" {
            filters.myFilter(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9), (filters.numberIsOdd)) shouldBe arrayOf(1, 3, 5, 7, 9)
        }
    }
}


