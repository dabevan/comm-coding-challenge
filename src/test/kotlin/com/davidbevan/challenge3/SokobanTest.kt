package com.davidbevan.challenge3

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class SokobanTest {

    private val sokoban = Sokoban()

    private var boardStringArray : Array<String>? = null

    @Test
    fun `Test player constrained by boundary walls`() {
        boardStringArray = arrayOf(
            "#######",
            "#     #",
            "#     #",
            "#  p  #",
            "#     #",
            "#     #",
            "#######"
            )

        move("R")
        move("R")
        move("R")
        move("R")
        move("R")

        var expectedBoardStringArray = arrayOf(
            "#######",
            "#     #",
            "#     #",
            "#    p#",
            "#     #",
            "#     #",
            "#######"
        )

        assertArrayEquals(expectedBoardStringArray, boardStringArray)

        move("R")
        move("R")
        move("R")
        move("R")
        move("R")
        move("U")
        move("U")
        move("U")
        move("U")
        move("U")
        move("U")
        move("U")

        expectedBoardStringArray = arrayOf(
            "#######",
            "#    p#",
            "#     #",
            "#     #",
            "#     #",
            "#     #",
            "#######"
        )

        assertArrayEquals(expectedBoardStringArray, boardStringArray)

        move("U")
        move("U")
        move("U")
        move("L")
        move("L")
        move("L")
        move("L")
        move("L")
        move("L")
        move("L")
        move("L")
        move("L")
        move("D")
        move("D")
        move("D")
        move("D")
        move("D")
        move("D")
        move("D")
        move("D")
        move("D")
        move("D")
        move("R")
        move("R")
        move("R")
        move("R")
        move("R")
        move("R")
        move("R")
        move("R")
        move("R")
        move("R")
        move("U")
        move("U")
        move("U")
        move("U")
        move("U")
        move("U")
        move("U")
        move("U")
        move("U")
        move("U")

        assertEquals(boardStringArray, boardStringArray)
    }

    fun move(direction : String) {
        println("Moving $direction")
        boardStringArray = sokoban.processSokobanMove(boardStringArray!!, direction)
        printBoard(boardStringArray!!)
        println()
    }

    fun printBoard(boardString :Array<String>) {
        boardString.map { println(it) }
    }
}

