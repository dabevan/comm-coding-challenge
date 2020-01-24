package com.davidbevan.codingchallengeevent

import org.http4k.client.JavaHttpClient
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request

class Client {

    fun getURL(url: String): String {
        val request = Request(Method.GET, url)
        val client: HttpHandler = JavaHttpClient()
        println(url)
        return client(request).bodyString()
    }

    fun getViewWrapper(command: String, referenceid: String): String {
        return getURL("https://challenge20.appspot.com/?command=$command&referenceid=$referenceid")
    }

}

val listOfVisitedReferenceIds = mutableListOf<String>()

fun main() {
    val url = "https://challenge20.appspot.com"
    val response = Client().getURL(url).split(",")

    val firstRefAndSquare = RefAndNextSquare(
        referenceid = response[0],
        nextSquare = response[1]
    )

    walkMaze(whatToDoNext(firstRefAndSquare), firstRefAndSquare.referenceid)
    println("FINISHED")

}

fun walkMaze(command: String, referenceid: String) {
    var currentRef = referenceid
    lateinit var refAndNextSquare: RefAndNextSquare

    command
        .toCharArray()
        .forEach {
            refAndNextSquare = getRefAndNextSquare(it.toString(), currentRef)
            currentRef = refAndNextSquare.referenceid
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
    return when (referenceAndNextSquare.nextSquare) {
        "O", "OL" -> "M"
        "OR", "OLR" -> "MR"
        "X" -> ""
        else -> "L"
    }
}