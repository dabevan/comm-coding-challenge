package com.davidbevan.challenge3

import java.lang.RuntimeException

class Sokoban {

    fun processSokobanMove(boardString :Array<String>, direction :String) :Array<String> {
        Board.createBoard(boardString)

        when (direction) {
            "U" -> Board.player.moveUp()
            "D" -> Board.player.moveDown()
            "L" -> Board.player.moveLeft()
            "R" -> Board.player.moveRight()
            else -> throw RuntimeException("$direction is an invalid direction, valid directions are U,D,L,R")
        }

        return Board.toStringArray()
    }

    fun isChallengeComplete(): Boolean {
        return Board.isChallengeComplete()
    }


}