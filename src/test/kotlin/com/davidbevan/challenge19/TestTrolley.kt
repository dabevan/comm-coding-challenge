package com.davidbevan.challenge19

import org.assertj.core.api.Assertions
import org.junit.Test

@ExperimentalStdlibApi
class TestTrolley {

    @Test
    fun testMovement() {
        var trolleyView = move()
        Assertions.assertThat(trolleyView.viewAhead).isEqualTo(listOf("O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "OR", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "OR", ""))

        trolleyView = move("M", trolleyView.referenceId)
        Assertions.assertThat(trolleyView.viewAhead).isEqualTo(listOf("O", "O", "O", "O", "O", "O", "O", "O", "O", "OR", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "OR", ""))

        trolleyView = move("M", trolleyView.referenceId)
        trolleyView = move("M", trolleyView.referenceId)
        trolleyView = move("M", trolleyView.referenceId)
        trolleyView = move("M", trolleyView.referenceId)
        trolleyView = move("M", trolleyView.referenceId)
        trolleyView = move("M", trolleyView.referenceId)
        trolleyView = move("M", trolleyView.referenceId)
        trolleyView = move("M", trolleyView.referenceId)
        trolleyView = move("M", trolleyView.referenceId)
        trolleyView = move("M", trolleyView.referenceId)
        Assertions.assertThat(trolleyView.viewAhead).isEqualTo(listOf("O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "OR", ""))

        trolleyView = move("R", trolleyView.referenceId)
        Assertions.assertThat(trolleyView.viewAhead).isEqualTo(listOf("O", "O", "OLR", "O", "O", "O", "OLR", ""))

        trolleyView = move("M", trolleyView.referenceId)
        trolleyView = move("M", trolleyView.referenceId)
        trolleyView = move("M", trolleyView.referenceId)
        Assertions.assertThat(trolleyView.viewAhead).isEqualTo(listOf("O", "O", "O", "OLR", ""))

        trolleyView = move("L", trolleyView.referenceId)
        Assertions.assertThat(trolleyView.viewAhead).isEqualTo(listOf("O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "OLR", ""))
     }


    @Test
    fun testGetSquare() {
        Assertions.assertThat(getSquareAtPosition(Position(0,0))).isEqualTo("*")
        Assertions.assertThat(getSquareAtPosition(Position(1,1))).isEqualTo(" ")
        Assertions.assertThat(getSquareAtPosition(Position(2,1))).isEqualTo(" ")
        Assertions.assertThat(getSquareAtPosition(Position(2,2))).isEqualTo("*")
        Assertions.assertThat(getSquareAtPosition(Position(2,3))).isEqualTo("*")
        Assertions.assertThat(getSquareAtPosition(Position(2,4))).isEqualTo(" ")
        Assertions.assertThat(getSquareAtPosition(Position(2,5))).isEqualTo("*")
    }


    @Test
    fun testGetSquareAndAdjacents() {
        Assertions.assertThat(getSquareAndAdjacentsAtPosition(Position(1,1),"E")).isEqualTo("*  ")
        Assertions.assertThat(getSquareAndAdjacentsAtPosition(Position(1,1),"W")).isEqualTo("  *")
        Assertions.assertThat(getSquareAndAdjacentsAtPosition(Position(2,1),"E")).isEqualTo("* *")
        Assertions.assertThat(getSquareAndAdjacentsAtPosition(Position(2,1),"W")).isEqualTo("* *")
        Assertions.assertThat(getSquareAndAdjacentsAtPosition(Position(2,1),"S")).isEqualTo("   ")
        Assertions.assertThat(getSquareAndAdjacentsAtPosition(Position(2,1),"N")).isEqualTo("   ")
        Assertions.assertThat(getSquareAndAdjacentsAtPosition(Position(1,2),"S")).isEqualTo("* *")
        Assertions.assertThat(getSquareAndAdjacentsAtPosition(Position(1,2),"N")).isEqualTo("* *")
    }


    @Test
    fun testRotateLeft() {
        Assertions.assertThat(rotateTrolleyLeft("N")).isEqualTo("W")
        Assertions.assertThat(rotateTrolleyLeft("E")).isEqualTo("N")
        Assertions.assertThat(rotateTrolleyLeft("S")).isEqualTo("E")
        Assertions.assertThat(rotateTrolleyLeft("W")).isEqualTo("S")
    }


    @Test
    fun testRotateRight() {
        Assertions.assertThat(rotateTrolleyRight("N")).isEqualTo("E")
        Assertions.assertThat(rotateTrolleyRight("E")).isEqualTo("S")
        Assertions.assertThat(rotateTrolleyRight("S")).isEqualTo("W")
        Assertions.assertThat(rotateTrolleyRight("W")).isEqualTo("N")
    }
}