package com.davidbevan.challenge17

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions

class TestCardsForPontoon {
    @Test
    fun `Hand is Pontoon`() {
        Assertions.assertThat(isHandPontoon(listOf("QH", "AC"))).isTrue()
    }

    @Test
    fun `Hand has three cards, so is not Pontoon`() {
        Assertions.assertThat(isHandPontoon(listOf("QH", "1C", "AC"))).isFalse()
    }

    @Test
    fun `First card is not picture or Ace, so not Pontoon`() {
        Assertions.assertThat(isHandPontoon(listOf("9H", "AC"))).isFalse()
    }

    @Test
    fun `All non picture cards`() {
        Assertions.assertThat(isCardAPicture(("2H"))).isFalse()
        Assertions.assertThat(isCardAPicture(("3H"))).isFalse()
        Assertions.assertThat(isCardAPicture(("4H"))).isFalse()
        Assertions.assertThat(isCardAPicture(("5H"))).isFalse()
        Assertions.assertThat(isCardAPicture(("6H"))).isFalse()
        Assertions.assertThat(isCardAPicture(("7H"))).isFalse()
        Assertions.assertThat(isCardAPicture(("8H"))).isFalse()
        Assertions.assertThat(isCardAPicture(("9H"))).isFalse()
        Assertions.assertThat(isCardAPicture(("TH"))).isFalse()
        Assertions.assertThat(isCardAPicture(("AH"))).isFalse()

        Assertions.assertThat(isCardAPicture(("2C"))).isFalse()
        Assertions.assertThat(isCardAPicture(("3C"))).isFalse()
        Assertions.assertThat(isCardAPicture(("4C"))).isFalse()
        Assertions.assertThat(isCardAPicture(("5C"))).isFalse()
        Assertions.assertThat(isCardAPicture(("6C"))).isFalse()
        Assertions.assertThat(isCardAPicture(("7C"))).isFalse()
        Assertions.assertThat(isCardAPicture(("8C"))).isFalse()
        Assertions.assertThat(isCardAPicture(("9C"))).isFalse()
        Assertions.assertThat(isCardAPicture(("TC"))).isFalse()
        Assertions.assertThat(isCardAPicture(("AC"))).isFalse()

        Assertions.assertThat(isCardAPicture(("2S"))).isFalse()
        Assertions.assertThat(isCardAPicture(("3S"))).isFalse()
        Assertions.assertThat(isCardAPicture(("4S"))).isFalse()
        Assertions.assertThat(isCardAPicture(("5S"))).isFalse()
        Assertions.assertThat(isCardAPicture(("6S"))).isFalse()
        Assertions.assertThat(isCardAPicture(("7S"))).isFalse()
        Assertions.assertThat(isCardAPicture(("8S"))).isFalse()
        Assertions.assertThat(isCardAPicture(("9S"))).isFalse()
        Assertions.assertThat(isCardAPicture(("TS"))).isFalse()
        Assertions.assertThat(isCardAPicture(("AS"))).isFalse()

        Assertions.assertThat(isCardAPicture(("2D"))).isFalse()
        Assertions.assertThat(isCardAPicture(("3D"))).isFalse()
        Assertions.assertThat(isCardAPicture(("4D"))).isFalse()
        Assertions.assertThat(isCardAPicture(("5D"))).isFalse()
        Assertions.assertThat(isCardAPicture(("6D"))).isFalse()
        Assertions.assertThat(isCardAPicture(("7D"))).isFalse()
        Assertions.assertThat(isCardAPicture(("8D"))).isFalse()
        Assertions.assertThat(isCardAPicture(("9D"))).isFalse()
        Assertions.assertThat(isCardAPicture(("TD"))).isFalse()
        Assertions.assertThat(isCardAPicture(("AS"))).isFalse()
    }

    @Test
    fun `All picture cards`() {
        Assertions.assertThat(isCardAPicture(("KH"))).isTrue()
        Assertions.assertThat(isCardAPicture(("QH"))).isTrue()
        Assertions.assertThat(isCardAPicture(("JH"))).isTrue()

        Assertions.assertThat(isCardAPicture(("KC"))).isTrue()
        Assertions.assertThat(isCardAPicture(("QC"))).isTrue()
        Assertions.assertThat(isCardAPicture(("JC"))).isTrue()

        Assertions.assertThat(isCardAPicture(("KS"))).isTrue()
        Assertions.assertThat(isCardAPicture(("QS"))).isTrue()
        Assertions.assertThat(isCardAPicture(("JS"))).isTrue()

        Assertions.assertThat(isCardAPicture(("KD"))).isTrue()
        Assertions.assertThat(isCardAPicture(("QD"))).isTrue()
        Assertions.assertThat(isCardAPicture(("JD"))).isTrue()
    }
}