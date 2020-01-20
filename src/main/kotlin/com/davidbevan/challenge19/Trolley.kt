package com.davidbevan.challenge19

class Trolley {

    val storeMap =
            "**************************\n" +
            "*                        *\n" +
            "* ********** *********** *\n" +
            "* ********** *********** *\n" +
            "*                        *\n" +
            "* ********** *********** *\n" +
            "* ********** *********** *\n" +
            "* ********** *********** *\n" +
            "*                        *\n" +
            "**************************\n"

    var storeMapList = emptyList<String>()

    init {
        storeMapList = storeMap.split("\n")
    }

    fun move(command: String, referenceId: String? = null): String {
        var position = Position(getXFromReferenceId(referenceId),getYFromReferenceId(referenceId))
        var direction = getDirectionFromReferenceId(referenceId)
        val trolleyId = getTrolleyIdFromReferenceId(referenceId)
        when {
            command == "M" -> position = position.moveOneSquare(direction)
            command == "L" -> direction = rotateTrolleyLeft(direction)
            command == "R" -> direction = rotateTrolleyRight(direction)
        }
        calculateView(position,direction)
        return ""
    }

    fun calculateView(position: Position,direction: String) {
        
    }

    fun getSquareAtPosition(position: Position): String {
        return storeMapList[position.y].subSequence(position.x,position.x+1).toString()
    }

    fun rotateTrolleyLeft(direction:String): String {
        return when {
            direction == "N" -> "W"
            direction == "E" -> "N"
            direction == "S" -> "E"
            direction == "W" -> "S"
            else -> "E"
        }
    }

    fun rotateTrolleyRight(direction:String): String {
        return when (direction) {
            "N" -> "E"
            "E" -> "S"
            "S" -> "W"
            "W" -> "N"
            else -> "E"
        }
    }

    fun getXFromReferenceId(referenceId: String?) = decodeReferenceId(referenceId).substringBefore(",").toInt()

    fun getYFromReferenceId(referenceId: String?) = decodeReferenceId(referenceId).substringAfter(",").substringBefore(":").toInt()

    fun getDirectionFromReferenceId(referenceId: String?) = decodeReferenceId(referenceId).substringAfter(":")

    fun getTrolleyIdFromReferenceId(referenceId: String?) = decodeReferenceId(referenceId).substringAfter(":")

    fun newTrolleyId() = (Math.random() * 999999).toString()

    fun decodeReferenceId(referenceId: String?): String {
        if (referenceId == null) return "1,1:E#${newTrolleyId()}"
        return referenceId //TODO Decoding
    }

    fun endecodeReferenceId(x: String, y: String, direction: String): String {
        return "$x,$y:$direction" //TODO Encoding
    }

}

data class Position(val x: Int,val y: Int) {
    fun moveOneSquare(direction:String): Position {
        return when (direction) {
            "N" -> Position(x,y-1)
            "E" -> Position(x+1,y)
            "S" -> Position(x,y+1)
            "W" -> Position(x-1,y)
            else -> Position(x,y)
        }
    }
}