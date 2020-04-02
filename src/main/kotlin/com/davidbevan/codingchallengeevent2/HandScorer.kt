package com.davidbevan.codingchallengeevent2

fun score(hand: List<String>): Int {
    if(isFour(hand)) return 2
    if(isFlush(hand)) return 4
    if(isStraight(hand)) return 5
    if(isThree(hand)) return 6
    if(isPair(hand)) return 8
    return 0
}

fun isFlush(hand: List<String>): Boolean {
    return hand.map {it.substring(1)}.groupBy { it }.values.map {it.size}.max()!! > 4
}

fun isFour(hand: List<String>): Boolean {
    return hand.map {it.substring(0,1)}.groupBy { it }.values.map {it.size}.max()!! == 4
}

fun isThree(hand: List<String>): Boolean {
    return hand.map {it.substring(0,1)}.groupBy { it }.values.map {it.size}.max()!! == 3
}

fun isPair(hand: List<String>): Boolean {
    return hand.map {it.substring(0,1)}.groupBy { it }.values.map {it.size}.max()!! == 2
}

fun isStraight(hand: List<String>): Boolean {
    val orderedListOfCardValues =  hand.map {
        val value = it.substring(0, 1)
        val number = when (value) {
            "A" -> 1
            "T" -> 10
            "J" -> 11
            "Q" -> 12
            "K" -> 13
            else -> value.toInt()
        }
        number
    }.sorted()
    println(orderedListOfCardValues)

    println(orderedListOfCardValues.subList(0,5).mapIndexed { index, i -> index + orderedListOfCardValues[0] == i  })
    println(orderedListOfCardValues.subList(1,6).mapIndexed { index, i -> index + orderedListOfCardValues[1] == i  })
    println(orderedListOfCardValues.subList(2,7).mapIndexed { index, i -> index + orderedListOfCardValues[2] == i  })

    return  !(orderedListOfCardValues.subList(0,5).mapIndexed { index, i -> index + orderedListOfCardValues[0] == i  }.contains(false) &&
            orderedListOfCardValues.subList(1,6).mapIndexed { index, i -> index + orderedListOfCardValues[1] == i  }.contains(false) &&
            orderedListOfCardValues.subList(2,7).mapIndexed { index, i -> index + orderedListOfCardValues[2] == i  }.contains(false))
}

fun main() {
    //println(isStraight(listOf("2H", "3H", "4H", "5H", "7S", "7C", "9C")))

    val listOfBooleans1 = listOf(true,true,true,false,true,true)
    val listOfBooleans2 = listOf(true,true,true,true,true,true)

    println(listOfBooleans1.contains(false))
    println(listOfBooleans2.contains(false))
}
