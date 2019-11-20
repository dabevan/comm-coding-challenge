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
    fun `should return X when V plus V to prove that the next roman numeral is used`() {
        val result = add("V", "V")
        val expectedValue = "X"
        Assertions.assertThat(result).isEqualTo(expectedValue)
    }

    @Test
    fun testMMPlusM() {
        val result = add("MM", "M")
        val expectedValue = "MMM"
        Assertions.assertThat(result).isEqualTo(expectedValue)
    }

    @Test
    fun testMCMXCVPlusXXIV() {
        val result = add("MCMXCV", "XXIV")
        val expectedValue = "MMXIX"
        Assertions.assertThat(result).isEqualTo(expectedValue)
    }
}