package com.davidbevan.challenge6

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class FilterTest {

    private var filters = Filters()

    @Test
    fun `test filter numbers less than 5 where 2 numbers qualify`() {
        val result = filters.myFilter(arrayOf(3, 4, 5, 6, 7, 8), (filters.numberIsLessThan5))
        Assertions.assertThat(result).containsExactlyElementsOf(arrayOf(3,4).asIterable())
    }

    @Test
    fun `test filter numbers less than 5 where 0 numbers qualify`() {
        val result = filters.myFilter(arrayOf(5, 6, 7, 8), (filters.numberIsLessThan5))
        Assertions.assertThat(result.size).isEqualTo(0)
    }

    @Test
    fun `test filter numbers less than 5 where all numbers qualify`() {
        val result = filters.myFilter(arrayOf(1,2,3,4), (filters.numberIsLessThan5))
        Assertions.assertThat(result).containsExactlyElementsOf(arrayOf(1,2,3,4).asIterable())
    }


    @Test
    fun `test filter even numbers in 1 to 9`() {
        val result = filters.myFilter(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9), (filters.numberIsEven))
        Assertions.assertThat(result).containsExactlyElementsOf(arrayOf(2, 4, 6, 8).asIterable())
    }

    @Test
    fun `test filter odd numbers in 1 to 9`() {
        val result = filters.myFilter(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9), (filters.numberIsOdd))
        Assertions.assertThat(result).containsExactlyElementsOf(arrayOf(1, 3, 5, 7, 9).asIterable())
    }
}