package com.davidbevan.challenge5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Connect4Test {



    @Test
    fun redWinsVertical() {
        val connect4 = Connect4(arrayOf(
            ".......",
            ".......",
            ".R.....",
            ".r.....",
            ".ry....",
            ".ryyy..")
        )
        assertEquals("Red wins", connect4.getGridStatus() )
    }

    @Test
    fun redWinsHorrzontal() {
        val connect4 = Connect4(arrayOf(
            ".......",
            ".......",
            ".......",
            ".yy....",
            ".rrRr..",
            ".ryyy..")
        )
        assertEquals("Red wins", connect4.getGridStatus() )
    }

    @Test
    fun redWinsDiagonal() {
        val connect4 = Connect4(arrayOf(
            ".......",
            ".......",
            "....r..",
            "...ry..",
            "..Ryr..",
            ".ryyyr.")
        )
        assertEquals("Red wins", connect4.getGridStatus() )
    }

    @Test
    fun YellowWinsDiagonal() {
        val connect4 = Connect4(arrayOf(
            ".......",
            ".......",
            "...y...",
            "...ry..",
            "...ryy.",
            "...rrrY")
        )
        assertEquals("Yellow wins", connect4.getGridStatus() )
    }

    @Test
    fun redPlaysNext() {
        val connect4 = Connect4(arrayOf(
            ".......",
            ".......",
            ".......",
            "...ry..",
            "...ryy.",
            "..yrrrY")
        )
        assertEquals("Red plays next", connect4.getGridStatus() )
    }

    @Test
    fun yellowPlaysNext() {
        val connect4 = Connect4(arrayOf(
            ".......",
            ".......",
            ".......",
            "...ry..",
            "..Rryy.",
            "..yrrry")
        )
        assertEquals("Yellow plays next", connect4.getGridStatus() )
    }

    @Test
    fun draw() {
        val connect4 = Connect4(arrayOf(
            "rrryyyr",
            "yyyrrry",
            "rrryyyr",
            "yyyrrry",
            "rrryyyr",
            "yyyrrry")
        )
        assertEquals("Draw", connect4.getGridStatus() )
    }
}