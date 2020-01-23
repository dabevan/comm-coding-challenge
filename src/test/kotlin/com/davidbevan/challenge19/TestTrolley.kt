package com.davidbevan.challenge19

import org.assertj.core.api.Assertions
import org.junit.Test

@ExperimentalStdlibApi
class TestTrolley {

    @Test
    fun testMovement() {
        val trolley = Trolley()
        var trolleyView = trolley.move()
        Assertions.assertThat(trolleyView.viewAhead).isEqualTo(listOf("O", "O", "O", "O", "O", "O", "O", "O", "O", "OR", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "OR", ""))
        println(trolleyView.referenceId)

        trolleyView = trolley.move("M", trolleyView.referenceId)
        Assertions.assertThat(trolleyView.viewAhead).isEqualTo(listOf("O", "O", "O", "O", "O", "O", "O", "O", "OR", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "OR", ""))

        trolleyView = trolley.move("M", trolleyView.referenceId)
        trolleyView = trolley.move("M", trolleyView.referenceId)
        trolleyView = trolley.move("M", trolleyView.referenceId)
        trolleyView = trolley.move("M", trolleyView.referenceId)
        trolleyView = trolley.move("M", trolleyView.referenceId)
        trolleyView = trolley.move("M", trolleyView.referenceId)
        trolleyView = trolley.move("M", trolleyView.referenceId)
        trolleyView = trolley.move("M", trolleyView.referenceId)
        trolleyView = trolley.move("M", trolleyView.referenceId)
        Assertions.assertThat(trolleyView.viewAhead).isEqualTo(listOf("O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "OR", ""))

        trolleyView = trolley.move("R", trolleyView.referenceId)
        Assertions.assertThat(trolleyView.viewAhead).isEqualTo(listOf("O", "O", "OLR", "O", "O", "O", "OLR", ""))

        trolleyView = trolley.move("M", trolleyView.referenceId)
        trolleyView = trolley.move("M", trolleyView.referenceId)
        trolleyView = trolley.move("M", trolleyView.referenceId)
        Assertions.assertThat(trolleyView.viewAhead).isEqualTo(listOf("O", "O", "O", "OLR", ""))

        trolleyView = trolley.move("L", trolleyView.referenceId)
        Assertions.assertThat(trolleyView.viewAhead).isEqualTo(listOf("O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "OLR", ""))
     }

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
    fun testGetSquareAndAdjacents() {
        val trolley = Trolley()
        Assertions.assertThat(trolley.getSquareAndAdjacentsAtPosition(Position(1,1),"E")).isEqualTo("*  ")
        Assertions.assertThat(trolley.getSquareAndAdjacentsAtPosition(Position(1,1),"W")).isEqualTo("  *")
        Assertions.assertThat(trolley.getSquareAndAdjacentsAtPosition(Position(2,1),"E")).isEqualTo("* *")
        Assertions.assertThat(trolley.getSquareAndAdjacentsAtPosition(Position(2,1),"W")).isEqualTo("* *")
        Assertions.assertThat(trolley.getSquareAndAdjacentsAtPosition(Position(2,1),"S")).isEqualTo("   ")
        Assertions.assertThat(trolley.getSquareAndAdjacentsAtPosition(Position(2,1),"N")).isEqualTo("   ")
        Assertions.assertThat(trolley.getSquareAndAdjacentsAtPosition(Position(1,2),"S")).isEqualTo("* *")
        Assertions.assertThat(trolley.getSquareAndAdjacentsAtPosition(Position(1,2),"N")).isEqualTo("* *")
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