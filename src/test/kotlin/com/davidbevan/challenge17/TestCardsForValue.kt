package com.davidbevan.challenge17

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions

class TestCardsForValue {
    @Test
    fun `Single card`() {
        Assertions.assertThat(valueOfCard("2H")).isEqualTo(2)
        Assertions.assertThat(valueOfCard("3H")).isEqualTo(3)
        Assertions.assertThat(valueOfCard("4H")).isEqualTo(4)
        Assertions.assertThat(valueOfCard("5H")).isEqualTo(5)
        Assertions.assertThat(valueOfCard("6H")).isEqualTo(6)
        Assertions.assertThat(valueOfCard("7H")).isEqualTo(7)
        Assertions.assertThat(valueOfCard("8H")).isEqualTo(8)
        Assertions.assertThat(valueOfCard("9H")).isEqualTo(9)
        Assertions.assertThat(valueOfCard("TH")).isEqualTo(10)
        Assertions.assertThat(valueOfCard("KH")).isEqualTo(10)
        Assertions.assertThat(valueOfCard("QH")).isEqualTo(10)
        Assertions.assertThat(valueOfCard("JH")).isEqualTo(10)
        Assertions.assertThat(valueOfCard("AH")).isEqualTo(1)
    }

    @Test
    fun `Two cards`() {
        Assertions.assertThat(valueOfHand(arrayOf("2H", "2C"))).isEqualTo(4)
        Assertions.assertThat(valueOfHand(arrayOf("TD", "8C"))).isEqualTo(18)
        Assertions.assertThat(valueOfHand(arrayOf("KD", "QH"))).isEqualTo(20)
    }

}