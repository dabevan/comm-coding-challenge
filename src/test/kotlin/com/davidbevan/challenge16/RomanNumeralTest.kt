package com.davidbevan.challenge16

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class RomanNumeralTest {
    @Test
    fun testI() {
        val result = romanNumeralCharacterToDecimal('I')
        val expectedValue = 1
        Assertions.assertThat(result).isEqualTo(expectedValue)
    }

    @Test
    fun testV() {
        val result = romanNumeralCharacterToDecimal('V')
        val expectedValue = 5
        Assertions.assertThat(result).isEqualTo(expectedValue)
    }

    @Test
    fun testX() {
        val result = romanNumeralCharacterToDecimal('X')
        val expectedValue = 10
        Assertions.assertThat(result).isEqualTo(expectedValue)
    }

    @Test
    fun testL() {
        val result = romanNumeralCharacterToDecimal('L')
        val expectedValue = 50
        Assertions.assertThat(result).isEqualTo(expectedValue)
    }

    @Test
    fun testC() {
        val result = romanNumeralCharacterToDecimal('C')
        val expectedValue = 100
        Assertions.assertThat(result).isEqualTo(expectedValue)
    }

    @Test
    fun testD() {
        val result = romanNumeralCharacterToDecimal('D')
        val expectedValue = 500
        Assertions.assertThat(result).isEqualTo(expectedValue)
    }

    @Test
    fun testM() {
        val result = romanNumeralCharacterToDecimal('M')
        val expectedValue = 1000
        Assertions.assertThat(result).isEqualTo(expectedValue)
    }

}