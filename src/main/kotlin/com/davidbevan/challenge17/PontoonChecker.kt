package com.davidbevan.challenge17

fun isHandPontoon(hand: Array<String>): Boolean {
    if (hand.size != 2) return false
    if (isCardAPicture(hand[0]) && isCardAnAce(hand[1])) return true
    if (isCardAnAce(hand[0]) && isCardAPicture(hand[1])) return true
    return false
}

fun isCardAPicture(card: String): Boolean {
    if (card.startsWith('K')) return true
    if (card.startsWith('Q')) return true
    if (card.startsWith('J')) return true
    return false
}

fun isCardAnAce(card: String): Boolean {
    if (card.startsWith('A')) return true
    return false
}