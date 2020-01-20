package com.davidbevan.challenge19

import org.assertj.core.api.Assertions
import org.junit.Test

class TestTrolley {

    @Test
    fun testGetSquare() {
        val trolley = Trolley()
        Assertions.assertThat(trolley.getSquareAtPosition(Position(0,0))).isEqualTo("*")
        Assertions.assertThat(trolley.getSquareAtPosition(Position(1,1))).isEqualTo(" ")
        Assertions.assertThat(trolley.getSquareAtPosition(Position(2,1))).isEqualTo(" ")
        Assertions.assertThat(trolley.getSquareAtPosition(Position(2,2))).isEqualTo("*")
        Assertions.assertThat(trolley.getSquareAtPosition(Position(2,3))).isEqualTo("*")
        Assertions.assertThat(trolley.getSquareAtPosition(Position(2,4))).isEqualTo(" ")
        Assertions.assertThat(trolley.getSquareAtPosition(Position(2,5))).isEqualTo("*")
    }

    @Test
    fun testRotateLeft() {
        val trolley = Trolley()
        Assertions.assertThat(trolley.rotateTrolleyLeft("N")).isEqualTo("W")
        Assertions.assertThat(trolley.rotateTrolleyLeft("E")).isEqualTo("N")
        Assertions.assertThat(trolley.rotateTrolleyLeft("S")).isEqualTo("E")
        Assertions.assertThat(trolley.rotateTrolleyLeft("W")).isEqualTo("S")
    }

    @Test
    fun testRotateRight() {
        val trolley = Trolley()
        Assertions.assertThat(trolley.rotateTrolleyRight("N")).isEqualTo("E")
        Assertions.assertThat(trolley.rotateTrolleyRight("E")).isEqualTo("S")
        Assertions.assertThat(trolley.rotateTrolleyRight("S")).isEqualTo("W")
        Assertions.assertThat(trolley.rotateTrolleyRight("W")).isEqualTo("N")
    }
}