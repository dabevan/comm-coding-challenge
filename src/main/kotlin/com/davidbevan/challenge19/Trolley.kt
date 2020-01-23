package com.davidbevan.challenge19

import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.*
import kotlin.math.roundToInt


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


    
    fun move(command: String? = null, referenceId: String? = null): TrolleyView {
        var position = Position(getXFromReferenceId(referenceId),getYFromReferenceId(referenceId))
        var direction = getDirectionFromReferenceId(referenceId)
        val trolleyId = getTrolleyIdFromReferenceId(referenceId)
        when {
            command == null -> position = position.moveOneSquare(direction)
            command == "M" -> position = position.moveOneSquare(direction)
            command == "L" -> direction = rotateTrolleyLeft(direction)
            command == "R" -> direction = rotateTrolleyRight(direction)
        }
        return TrolleyView(calculateView(position,direction), encodeReferenceId(position.x, position.y, direction, trolleyId))
    }

    fun calculateView(position: Position,direction: String): MutableList<String> {
        var viewAhead = mutableListOf<String>()
        var currentSquareAndAdjacents = getSquareAndAdjacentsAtPosition(position, direction)
        var viewPosition = position
        while(currentSquareAndAdjacents.getMiddleSquare() != "*") {
            viewPosition = viewPosition.moveOneSquare(direction)
            currentSquareAndAdjacents = getSquareAndAdjacentsAtPosition(viewPosition, direction)
            viewAhead.add(currentSquareAndAdjacents.convertToDirectionOptions())
        }
        return viewAhead
    }

    fun getSquareAndAdjacentsAtPosition(position: Position, direction: String): String {
        return when(direction){
            "N" -> getSquareAtPosition(Position(position.x-1,position.y)) + getSquareAtPosition(position) + getSquareAtPosition(Position(position.x+1,position.y))
            "S" -> getSquareAtPosition(Position(position.x+1,position.y)) + getSquareAtPosition(position) + getSquareAtPosition(Position(position.x-1,position.y))
            "E" -> getSquareAtPosition(Position(position.x,position.y-1)) + getSquareAtPosition(position) + getSquareAtPosition(Position(position.x,position.y+1))
            "W" -> getSquareAtPosition(Position(position.x,position.y+1)) + getSquareAtPosition(position) + getSquareAtPosition(Position(position.x,position.y-1))
            else -> ""
        }
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

    
    fun getDirectionFromReferenceId(referenceId: String?) = decodeReferenceId(referenceId).substringAfter(":").substringBefore("#")

    
    fun getTrolleyIdFromReferenceId(referenceId: String?) = decodeReferenceId(referenceId).substringAfter("#")

    fun newTrolleyId() = (Math.random() * 999999).roundToInt().toString()

    
    fun decodeReferenceId(referenceId: String?): String {
        if (referenceId == null) return "1,1:E#${newTrolleyId()}"
        return decode(referenceId)
    }

    fun encodeReferenceId(x: Int, y: Int, direction: String, trolleyId: String): String {
        return encode("$x,$y:$direction#$trolleyId")
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

data class TrolleyView(val viewAhead: List<String>, val referenceId: String)

fun String.getMiddleSquare(): String = this.substring(1,2)

fun String.convertToDirectionOptions(): String {

    return when(this) {
        "***" -> ""
        "* *" -> "O"
        "  *" -> "OL"
        "*  " -> "OR"
        "   " -> "OLR"
        else -> ""
    }
}



@UseExperimental(ExperimentalStdlibApi::class)
fun decode(encodedText: String): String {
    return Base64.getDecoder().decode(encodedText).decodeToString()
}

fun encode(plainText: String): String {
    var data = ByteArray(0)

    try {
        data = plainText.toByteArray()
    } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
    } finally {
        return Base64.getEncoder().encodeToString(data)
    }
}


fun main() {
    var x = 0
    var y = 0
    while(y < 10) {
        while(x < 26) {
            print(Trolley().getSquareAtPosition(Position(x,y)))
            x++
        }
        x=0
        y++
        print("\n")
    }

    println("1,1,E |${Trolley().getSquareAndAdjacentsAtPosition(Position(1,1),"E")}|")
    println("1,1,W |${Trolley().getSquareAndAdjacentsAtPosition(Position(1,1),"W")}|")
    println("2,1,E |${Trolley().getSquareAndAdjacentsAtPosition(Position(2,1),"E")}|")
    println("2,1,W |${Trolley().getSquareAndAdjacentsAtPosition(Position(2,1),"W")}|")
    println("2,1,S |${Trolley().getSquareAndAdjacentsAtPosition(Position(2,1),"S")}|")
    println("2,1,N |${Trolley().getSquareAndAdjacentsAtPosition(Position(2,1),"N")}|")
    println("1,2,S |${Trolley().getSquareAndAdjacentsAtPosition(Position(1,2),"S")}|")
    println("1,2,N |${Trolley().getSquareAndAdjacentsAtPosition(Position(1,2),"N")}|")
}

