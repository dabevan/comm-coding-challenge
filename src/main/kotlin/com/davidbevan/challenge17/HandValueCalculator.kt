package com.davidbevan.challenge17

fun valueOfHand(hand: Array<String>): Int {
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
    if (firstCharOfCard == "A") return 1
    return firstCharOfCard.toInt()
}

fun valueOfHandAcesHighOrLow(hand: Array<String>): Int {
    if (!doesHandContainAnAce(hand)) return valueOfHand(hand)
    if (valueOfHand(hand) > 11) return valueOfHand(hand)
    return valueOfHand(hand) + 10
}


fun doesHandContainAnAce(hand: Array<String>): Boolean {
    hand.map{ card ->
        if (isCardAnAce(card)) return true
    }
    return false
}