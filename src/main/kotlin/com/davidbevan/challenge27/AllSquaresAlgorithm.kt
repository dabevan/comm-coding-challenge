package com.davidbevan.challenge27



class AllSquaresAlgorithm {

    private val game = Game()

    fun execute():String{
        shoot(
            "A0B0C0D0E0F0G0H0I0J0" +
                    "A1B1C1D1E1F1G1H1I1J1" +
                    "A2B2C2D2E2F2G2H2I2J2" +
                    "A3B3C3D3E3F3G3H3I3J3" +
                    "A4B4C4D4E4F4G4H4I4J4" +
                    "A5B5C5D5E5F5G5H5I5J5" +
                    "A6B6C6D6E6F6G6H6I6J6" +
                    "A7B7C7D7E7F7G7H7I7J7" +
                    "A8B8C8D8E8F8G8H8I8J8" +
                    "A9B9C9D9E9F9G9H9I9J9",
            game,
            "DavidBevan1"
        )
        return game.declareResult()
    }
}
