package com.davidbevan.codingchallengeevent2

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class HandScorerTest {

    @Test
    fun `Should find a flush`() {
        //Given
        val hand = listOf("2H", "3H", "4H", "5H", "2S", "7H", "3C")
        val expectedResult = 4
        //When
        val result = score(hand)
        //Then
        Assertions.assertEquals(expectedResult, result)
    }

    @Test
    fun `Should not find a flush`() {
        //Given
        val hand = listOf("2H", "3H", "4H", "5H", "2S", "7C", "3C")
        val notExpectedResult = 4
        //When
        val result = score(hand)
        //Then
        Assertions.assertNotEquals(notExpectedResult, result)
    }

    @Test
    fun `Should find a four of a kind`() {
        //Given
        val hand = listOf("2H", "2C", "2S", "2D", "3D", "7H", "3C")
        val expectedResult = 2
        //When
        val result = score(hand)
        //Then
        Assertions.assertEquals(expectedResult, result)
    }

    @Test
    fun `Should not find a four of a kind`() {
        //Given
        val hand = listOf("2H", "3H", "4H", "5H", "2S", "7C", "3C")
        val notExpectedResult = 2
        //When
        val result = score(hand)
        //Then
        Assertions.assertNotEquals(notExpectedResult, result)
    }

   @Test
    fun `Should find a three of a kind`() {
        //Given
        val hand = listOf("2H", "2C", "2S", "4D", "3D", "7H", "3C")
        val expectedResult = 6
        //When
        val result = score(hand)
        //Then
        Assertions.assertEquals(expectedResult, result)
    }

    @Test
    fun `Should not find a three of a kind`() {
        //Given
        val hand = listOf("2H", "3H", "4H", "5H", "2S", "7C", "3C")
        val notExpectedResult = 6
        //When
        val result = score(hand)
        //Then
        Assertions.assertNotEquals(notExpectedResult, result)
    }

   @Test
    fun `Should find a pair`() {
        //Given
        val hand = listOf("2H", "2C", "6S", "4D", "3D", "7H", "3C")
        val expectedResult = 8
        //When
        val result = score(hand)
        //Then
        Assertions.assertEquals(expectedResult, result)
    }

    @Test
    fun `Should not find a pair`() {
        //Given
        val hand = listOf("2H", "3H", "4H", "5H", "8S", "7C", "9C")
        val notExpectedResult = 8
        //When
        val result = score(hand)
        //Then
        Assertions.assertNotEquals(notExpectedResult, result)
    }

   @Test
    fun `Should find a low straight`() {
        //Given
        val hand = listOf("2H", "3C", "4S", "5D", "6D", "8H", "9C")
        val expectedResult = 5
        //When
        val result = score(hand)
        //Then
        Assertions.assertEquals(expectedResult, result)
    }

    @Test
    fun `Should find a mid straight`() {
        //Given
        val hand = listOf("2H", "4S", "5D", "6D", "7H","8H", "KC")
        val expectedResult = 5
        //When
        val result = score(hand)
        //Then
        Assertions.assertEquals(expectedResult, result)
    }

    @Test
    fun `Should find a high straight`() {
        //Given
        val hand = listOf("2H", "3S", "5D", "6D", "7H","8H", "9C")
        val expectedResult = 5
        //When
        val result = score(hand)
        //Then
        Assertions.assertEquals(expectedResult, result)
    }

    @Test
    fun `Should find a straight with pics`() {
        //Given
        val hand = listOf("2H", "3S", "8D", "9D", "TH", "JH","QH")
        val expectedResult = 5
        //When
        val result = score(hand)
        //Then
        Assertions.assertEquals(expectedResult, result)
    }

    @Test
    fun `Should not find a straight`() {
        //Given
        val hand = listOf("2H", "3H", "4H", "5H", "8S", "7C", "9C")
        val notExpectedResult = 5
        //When
        val result = score(hand)
        //Then
        Assertions.assertNotEquals(notExpectedResult, result)
    }

}

