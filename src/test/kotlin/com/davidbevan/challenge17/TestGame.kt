package com.davidbevan.challenge17

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TestGame {

    val pontoonHand = arrayOf("AH", "JS")
    val fiveCardTrickHand = arrayOf("1H", "2H","3H", "4H","5H")
    val perfect21Hand = arrayOf("1H", "2H","3H", "4H","5H","5D","1D")
    val perfect21WhenAcesHighHand = arrayOf("AH", "TH")
    val perfect21WhenAcesLowHand = arrayOf("TD", "TH", "AH")
    val sixteenHand = arrayOf("1H", "2H","3H", "4H","5D","1D")
    val twentyFiveBustHand = arrayOf("1H", "2H","3H", "4H","5D","5S","5C")


    @Test
    fun `Player wins Pontoon, Dealer not Pontoon`() {
        Assertions.assertThat(whoWins(pontoonHand,fiveCardTrickHand)).isEqualTo(PLAYER_WINS + PONTOON)
    }

    @Test
    fun `Dealer wins Pontoon, Player not Pontoon`() {
        Assertions.assertThat(whoWins(fiveCardTrickHand,pontoonHand)).isEqualTo(DEALER_WINS + PONTOON)
    }

    @Test
    fun `Dealer wins Pontoon, Player Pontoon`() {
        Assertions.assertThat(whoWins(pontoonHand,pontoonHand)).isEqualTo(DEALER_WINS + PONTOON)
    }

    @Test
    fun `Dealer wins Five Card Trick, Player not Five Card Trick`() {
        Assertions.assertThat(whoWins(perfect21Hand, fiveCardTrickHand)).isEqualTo(DEALER_WINS + FIVE_CARD_TRICK)
    }

    @Test
    fun `Player wins Five Card Trick, Dealer not Five Card Trick`() {
        Assertions.assertThat(whoWins(fiveCardTrickHand, perfect21Hand)).isEqualTo(PLAYER_WINS + FIVE_CARD_TRICK)
    }

    @Test
    fun `Dealer wins Five Card Trick, Player also Five Card Trick`() {
        Assertions.assertThat(whoWins(fiveCardTrickHand, fiveCardTrickHand)).isEqualTo(DEALER_WINS + FIVE_CARD_TRICK)
    }

    @Test
    fun `Dealer wins Highest Value, Player bust`() {
        Assertions.assertThat(whoWins(twentyFiveBustHand, sixteenHand)).isEqualTo(DEALER_WINS + PLAYER_IS_BUST)
    }

    @Test
    fun `Player wins Highest Value, Dealer bust`() {
        Assertions.assertThat(whoWins(sixteenHand, twentyFiveBustHand)).isEqualTo(PLAYER_WINS + HIGHEST_VALUE_HAND_IS + 16)
    }

    @Test
    fun `Player wins Highest Value, Dealer lower value`() {
        Assertions.assertThat(whoWins(perfect21Hand, sixteenHand)).isEqualTo(PLAYER_WINS + HIGHEST_VALUE_HAND_IS + 21)
    }

    @Test
    fun `Dealer wins Highest Value, Player lower value`() {
        Assertions.assertThat(whoWins(sixteenHand, perfect21Hand)).isEqualTo(DEALER_WINS + HIGHEST_VALUE_HAND_IS + 21)
    }

    @Test
    fun `Dealer wins Highest Value, Player equal value`() {
        Assertions.assertThat(whoWins(sixteenHand, sixteenHand)).isEqualTo(DEALER_WINS + HIGHEST_VALUE_HAND_IS + 16)
    }

    @Test
    fun `Player wins Highest Value with Aces low, Dealer lower value`() {
        Assertions.assertThat(whoWins(perfect21WhenAcesLowHand, sixteenHand)).isEqualTo(PLAYER_WINS + HIGHEST_VALUE_HAND_IS + 21)
    }

    @Test
    fun `Player wins Highest Value with Aces high, Dealer lower value`() {
        Assertions.assertThat(whoWins(perfect21WhenAcesHighHand, sixteenHand)).isEqualTo(PLAYER_WINS + HIGHEST_VALUE_HAND_IS + 21)
    }
}