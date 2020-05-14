package com.davidbevan.challenge27


private val game = Game()

class KnownShipPositionsAlgorithm {
    fun execute():String {

        shoot(
            "B2B3B4B5B6" +
                    "D3D4D5" +
                    "E1F1G1H1" +
                    "H4H5" +
                    "E6" +
                    "A9" +
                    "I8I9",
            game,
            "DavidBevan0"
        )
        return game.declareResult()
    }
}


