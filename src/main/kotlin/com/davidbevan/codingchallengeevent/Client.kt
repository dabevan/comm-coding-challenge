package com.davidbevan.codingchallengeevent

import com.davidbevan.codingchallengeevent.Direction.*
import org.http4k.client.JavaHttpClient
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request

var numberOfSteps = 0

class Client {

    fun getURL(url: String): String {
        val request = Request(Method.GET, url)
        val client: HttpHandler = JavaHttpClient()
        println(url)
        numberOfSteps++
        return client(request).bodyString()
    }

    fun getViewWrapper(command: String, referenceid: String): String {
        return getURL("https://challenge20.appspot.com/?command=$command&referenceid=$referenceid")
    }

}

val listOfPositions = mutableListOf(Position(1, 1))
val listOfVisitedReferenceIds = mutableListOf<String>()
var direction: Direction = EAST

data class Position(val x: Int, val y: Int) {
    fun moveOneSquare(direction: Direction): Position {
        return when (direction) {
            NORTH -> Position(x, y - 1)
            EAST -> Position(x + 1, y)
            SOUTH -> Position(x, y + 1)
            WEST -> Position(x - 1, y)
        }
    }
}

enum class Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    fun turn(command: String): Direction {
        return when (command) {
            "L" -> when (this) {
                NORTH -> WEST
                SOUTH -> EAST
                EAST -> NORTH
                WEST -> SOUTH
            }
            "R" -> when (this) {
                NORTH -> EAST
                SOUTH -> WEST
                EAST -> SOUTH
                WEST -> NORTH
            }
            else -> this
        }
    }
}


fun main() {
    val url = "https://challenge20.appspot.com"
    val response = Client().getURL(url).split(",")

    val firstRefAndSquare = RefAndNextSquare(
        referenceid = response[0],
        nextSquare = response[1]
    )

    walkMaze(whatToDoNext(firstRefAndSquare), firstRefAndSquare.referenceid)
    //println("FINISHED in $numberOfSteps steps")
//    println(listOfPositions)
    drawRoute()
}

fun walkMaze(command: String, referenceid: String) {
    drawRoute()
    var currentRef = referenceid
    lateinit var refAndNextSquare: RefAndNextSquare

    command
        .toCharArray()
        .forEach {
            refAndNextSquare = getRefAndNextSquare(it.toString(), currentRef)
            currentRef = refAndNextSquare.referenceid
            recordBreadcrumbs(it.toString())
        }

    val command = whatToDoNext(refAndNextSquare) //.also { println("""$refAndNextSquare Next Command: $it""") }
    listOfVisitedReferenceIds.add(currentRef)

    if (command != "") {
        walkMaze(command, refAndNextSquare.referenceid)
    }
}

fun getRefAndNextSquare(command: String, referenceid: String): RefAndNextSquare {
    val response = Client().getViewWrapper(command, referenceid).split(",")
    return RefAndNextSquare(
        referenceid = response[0],
        nextSquare = response.getOrElse(1, { "" })
    )
}

data class RefAndNextSquare(val referenceid: String, val nextSquare: String)

fun whatToDoNext(referenceAndNextSquare: RefAndNextSquare): String {
//    println(listOfVisitedReferenceIds)
    if (listOfVisitedReferenceIds.contains(referenceAndNextSquare.referenceid)) {
        listOfVisitedReferenceIds.clear()
        return "L"
    }
//    return when (referenceAndNextSquare.nextSquare) {
//        "O", "OL" -> "M"
//        "OR", "OLR" -> "MR"
//        "X" -> ""
//        else -> "L"
//    }
    return when (referenceAndNextSquare.nextSquare) {
        "O", "OR" -> "M"
        "OL", "OLR" -> "ML"
        "X" -> ""
        else -> "R"
    }
}

fun recordBreadcrumbs(command: String) {
    when (command) {
        "M" -> listOfPositions.add(listOfPositions.last().moveOneSquare(direction))
        else -> direction = direction.turn(command)
    }
}

fun drawRoute() {
    fun emptyLine() = (1..20).map { "   " }.toMutableList()
    val grid = (1..20).map { emptyLine() }.toMutableList()

    listOfPositions.forEachIndexed { index, position ->
        grid[position.y][position.x] = index.toString().padStart(3, ' ')
    }

    Thread.sleep(1000)
    println()
    print(grid.map {
        it.joinToString("")
    }.joinToString("\n"))
}





