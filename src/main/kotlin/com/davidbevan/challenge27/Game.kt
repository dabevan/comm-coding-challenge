package com.davidbevan.challenge27

class Game(val name: String = "TESTGAME") {
    var numberOfShots = 0
    var squaresShotAtAlready = mutableMapOf<String, String?>()

    fun recordShots(shotsString: String, shotResults: ShotResults) {
        shotsString.chunked(2).forEachIndexed{index, shot -> squaresShotAtAlready.put(shot,shotResults.results[index])}
        numberOfShots = squaresShotAtAlready.size
    }

    fun declareResult(): String{
        var message = ("### NUMBER OF SHOTS FIRED: ${numberOfShots}.")
        if (squaresShotAtAlready.values.count { it == "S" || it == "H"} == 18) {
            message += " ALL ENEMY SHIPS SUNK ###"
        } else {
            message += " ENEMY NOT DESTROYED ###"
        }
        return message
    }

}
