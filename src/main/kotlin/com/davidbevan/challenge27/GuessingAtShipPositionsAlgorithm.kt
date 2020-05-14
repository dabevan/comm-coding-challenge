package com.davidbevan.challenge27


private val game = Game()

class GuessingAtShipPositionsAlgorithm {
    fun execute():String {

        shoot(
            "A1D5J2C6E2",
            game,
            "DavidBevan2"
        )
        return game.declareResult()
    }
}


