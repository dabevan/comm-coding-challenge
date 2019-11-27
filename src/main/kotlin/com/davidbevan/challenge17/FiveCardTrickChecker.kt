package com.davidbevan.challenge17

fun isHandFiveCardTrick(hand: Array<String>): Boolean {
    if (hand.size != 5) return false
    if (valueOfHand(hand) > 21) return false
    return true
}
