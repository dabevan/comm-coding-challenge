package com.davidbevan.challenge17

fun valueOfHand(hand: List<String>): Int {
    var valueOfHand = 0
    hand.map{ card ->
        valueOfHand += valueOfCard(card)
    }
    return valueOfHand
}

fun valueOfCard(card: String): Int {
    val firstCharOfCard = card.substring(0,1)
    if (firstCharOfCard == "K") return 10
    if (firstCharOfCard == "Q") return 10
    if (firstCharOfCard == "J") return 10
    if (firstCharOfCard == "T") return 10
    if (firstCharOfCard == "A") return 10
    return firstCharOfCard.toInt()
}