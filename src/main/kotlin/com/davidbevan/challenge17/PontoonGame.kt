package com.davidbevan.challenge17

const val DEALER_WINS = "Dealer wins: "
const val PLAYER_WINS = "Player wins: "
const val PONTOON = "'Pontoon'"
const val FIVE_CARD_TRICK = "'Five Card Trick'"
const val PLAYER_IS_BUST = "Player is 'Bust'"
const val HIGHEST_VALUE_HAND_IS = "Highest value hand is "


fun whoWins(playersHand: Array<String>, dealersHand: Array<String>): String {
    val dealersHandRanking = calcHandRank(dealersHand)
    val playersHandRanking = calcHandRank(playersHand)

    if (dealersHandRanking.handType == HandType.PONTOON) return DEALER_WINS + PONTOON
    if (playersHandRanking.handType == HandType.PONTOON) return PLAYER_WINS + PONTOON

    if (dealersHandRanking.handType == HandType.FIVE_CARD_TRICK) return DEALER_WINS + FIVE_CARD_TRICK
    if (playersHandRanking.handType == HandType.FIVE_CARD_TRICK) return PLAYER_WINS + FIVE_CARD_TRICK

    if (playersHandRanking.handType == HandType.BUST) return DEALER_WINS + PLAYER_IS_BUST

    if (playersHandRanking.handValue > dealersHandRanking.handValue) return PLAYER_WINS + HIGHEST_VALUE_HAND_IS + playersHandRanking.handValue
    return DEALER_WINS + HIGHEST_VALUE_HAND_IS + dealersHandRanking.handValue
}


fun calcHandRank(hand: Array<String>): HandRanking {
    if (isHandPontoon(hand)) return HandRanking(HandType.PONTOON)
    if (isHandFiveCardTrick(hand)) return HandRanking(HandType.FIVE_CARD_TRICK)
    if (valueOfHandAcesHighOrLow(hand) > 21) return HandRanking(HandType.BUST)
    return HandRanking(HandType.HIGHEST, valueOfHandAcesHighOrLow(hand))
}

data class HandRanking (
    val handType: Enum<HandType>,
    val handValue: Int = 0
)

enum class HandType {
    PONTOON,
    FIVE_CARD_TRICK,
    HIGHEST,
    BUST
}