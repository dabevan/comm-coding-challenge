package com.davidbevan.challenge3

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class SokobanTest {

    private val sokoban = Sokoban()

    private var boardStringArray : Array<String>? = null
    private var expectedBoardStringArray: Array<String>? = null

    @Test
    fun `Test player constrained by right boundary wall`() {
        //Given
        boardStringArray = arrayOf(
            "#######",
            "#     #",
            "#     #",
            "#  p  #",
            "#     #",
            "#     #",
            "#######"
        )

        //When
        move("R")
        move("R")
        move("R")
        move("R")
        move("R")

        //Then
        expectedBoardStringArray = arrayOf(
            "#######",
            "#     #",
            "#     #",
            "#    p#",
            "#     #",
            "#     #",
            "#######"
        )
        assertArrayEquals(expectedBoardStringArray, boardStringArray)
    }

    @Test
    fun `Test player constrained by top boundary wall`() {
        //Given
        boardStringArray = arrayOf(
            "#######",
            "#     #",
            "#     #",
            "#  p  #",
            "#     #",
            "#     #",
            "#######"
        )

        //When
        move("U")
        move("U")
        move("U")
        move("U")
        move("U")

        //Then
        expectedBoardStringArray = arrayOf(
            "#######",
            "#  p  #",
            "#     #",
            "#     #",
            "#     #",
            "#     #",
            "#######"
        )
        assertArrayEquals(expectedBoardStringArray, boardStringArray)
    }

    @Test
    fun `Test player constrained by left boundary wall`() {
        //Given
        boardStringArray = arrayOf(
            "#######",
            "#     #",
            "#     #",
            "#  p  #",
            "#     #",
            "#     #",
            "#######"
        )

        //When
        move("L")
        move("L")
        move("L")
        move("L")
        move("L")

        //Then
        expectedBoardStringArray = arrayOf(
            "#######",
            "#     #",
            "#     #",
            "#p    #",
            "#     #",
            "#     #",
            "#######"
        )
        assertArrayEquals(expectedBoardStringArray, boardStringArray)
    }


    @Test
    fun `Test player constrained by lower boundary wall`() {
        //Given
        boardStringArray = arrayOf(
            "#######",
            "#     #",
            "#     #",
            "#  p  #",
            "#     #",
            "#     #",
            "#######"
        )

        //When
        move("D")
        move("D")
        move("D")
        move("D")
        move("D")

        //Then
        expectedBoardStringArray = arrayOf(
            "#######",
            "#     #",
            "#     #",
            "#     #",
            "#     #",
            "#  p  #",
            "#######"
        )
        assertArrayEquals(expectedBoardStringArray, boardStringArray)
    }


    @Test
    fun `Test player constrained by inner wall`() {
        //Given
        boardStringArray = arrayOf(
            "#######",
            "#     #",
            "#     #",
            "#p  # #",
            "#     #",
            "#     #",
            "#######"
        )

        //When
        move("R")
        move("R")
        move("R")
        move("R")
        move("R")

        //Then
        expectedBoardStringArray = arrayOf(
            "#######",
            "#     #",
            "#     #",
            "#  p# #",
            "#     #",
            "#     #",
            "#######"
        )
        assertArrayEquals(expectedBoardStringArray, boardStringArray)
    }

    @Test
    fun `Test player pushing box until constrained by right boundary wall`() {
        //Given
        boardStringArray = arrayOf(
            "#######",
            "#     #",
            "#     #",
            "#  pb #",
            "#     #",
            "#     #",
            "#######"
        )

        //When
        move("R")
        move("R")
        move("R")
        move("R")
        move("R")

        //Then
        expectedBoardStringArray = arrayOf(
            "#######",
            "#     #",
            "#     #",
            "#   pb#",
            "#     #",
            "#     #",
            "#######"
        )
        assertArrayEquals(expectedBoardStringArray, boardStringArray)
    }

    @Test
    fun `Test player pushing box until constrained by top boundary wall`() {
        //Given
        boardStringArray = arrayOf(
            "#######",
            "#     #",
            "#  b  #",
            "#  p  #",
            "#     #",
            "#     #",
            "#######"
        )

        //When
        move("U")
        move("U")
        move("U")
        move("U")
        move("U")

        //Then
        expectedBoardStringArray = arrayOf(
            "#######",
            "#  b  #",
            "#  p  #",
            "#     #",
            "#     #",
            "#     #",
            "#######"
        )
        assertArrayEquals(expectedBoardStringArray, boardStringArray)
    }

    @Test
    fun `Test player pushing box until constrained by left boundary wall`() {
        //Given
        boardStringArray = arrayOf(
            "#######",
            "#     #",
            "#     #",
            "# bp  #",
            "#     #",
            "#     #",
            "#######"
        )

        //When
        move("L")
        move("L")
        move("L")
        move("L")
        move("L")

        //Then
        expectedBoardStringArray = arrayOf(
            "#######",
            "#     #",
            "#     #",
            "#bp   #",
            "#     #",
            "#     #",
            "#######"
        )
        assertArrayEquals(expectedBoardStringArray, boardStringArray)
    }


    @Test
    fun `Test player pushing box until constrained by lower boundary wall`() {
        //Given
        boardStringArray = arrayOf(
            "#######",
            "#     #",
            "#     #",
            "#  p  #",
            "#  b  #",
            "#     #",
            "#######"
        )

        //When
        move("D")
        move("D")
        move("D")
        move("D")
        move("D")

        //Then
        expectedBoardStringArray = arrayOf(
            "#######",
            "#     #",
            "#     #",
            "#     #",
            "#  p  #",
            "#  b  #",
            "#######"
        )
        assertArrayEquals(expectedBoardStringArray, boardStringArray)
    }

    @Test
    fun `Test player pushing box through a storage location`() {
        //Given
        boardStringArray = arrayOf(
            "##########",
            "#        #",
            "# p b *  #",
            "#        #",
            "##########"
        )

        //When
        move("R")
        move("R")
        move("R")

        //Then
        expectedBoardStringArray = arrayOf(
            "##########",
            "#        #",
            "#    pB  #",
            "#        #",
            "##########"
        )
        assertArrayEquals(expectedBoardStringArray, boardStringArray)

        //When
        move("R")

        //Then
        expectedBoardStringArray = arrayOf(
            "##########",
            "#        #",
            "#     Pb #",
            "#        #",
            "##########"
        )
        assertArrayEquals(expectedBoardStringArray, boardStringArray)

        //When
        move("R")

        //Then
        expectedBoardStringArray = arrayOf(
            "##########",
            "#        #",
            "#     *pb#",
            "#        #",
            "##########"
        )
        assertArrayEquals(expectedBoardStringArray, boardStringArray)

    }

    @Test
    fun `Test player completing simple challenge`() {
        //Given
        boardStringArray = arrayOf(
            "###########",
            "#    p    #",
            "#         #",
            "#         #",
            "#  b   b  #",
            "#  *   *  #",
            "###########"
        )

        //When
        move("D")
        move("D")

        //Then
        assertEquals(false, sokoban.isChallengeComplete())

        //When
        move("L")
        move("L")
        move("D")

        //Then
        expectedBoardStringArray = arrayOf(
            "###########",
            "#         #",
            "#         #",
            "#         #",
            "#  p   b  #",
            "#  B   *  #",
            "###########"
        )
        assertArrayEquals(expectedBoardStringArray, boardStringArray)
        assertEquals(false, sokoban.isChallengeComplete())

        //When
        move("U")
        move("R")
        move("R")
        move("R")
        move("R")
        move("D")

        //Then
        expectedBoardStringArray = arrayOf(
            "###########",
            "#         #",
            "#         #",
            "#         #",
            "#      p  #",
            "#  B   B  #",
            "###########"
        )
        assertArrayEquals(expectedBoardStringArray, boardStringArray)
        assertEquals(true, sokoban.isChallengeComplete())

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

