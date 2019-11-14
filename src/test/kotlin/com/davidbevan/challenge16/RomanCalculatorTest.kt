package com.davidbevan.challenge16

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class RomanCalculatorTest {
    @Test
    fun testIPlusI() {
        val result = add("I", "I")
        val expectedValue = "II"
        Assertions.assertThat(result).isEqualTo(expectedValue)
    }

    @Test
    fun testVPlusV() {
        val result = add("V", "V")
        val expectedValue = "X"
        Assertions.assertThat(result).isEqualTo(expectedValue)
    }
}