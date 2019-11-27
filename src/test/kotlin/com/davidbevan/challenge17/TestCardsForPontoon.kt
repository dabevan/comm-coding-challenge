package com.davidbevan.challenge17

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions

class TestCardsForPontoon {
    @Test
    fun `Hand is Pontoon`() {
        Assertions.assertThat(isHandPontoon(arrayOf("QH", "AC"))).isTrue()
    }

    @Test
    fun `Hand has three cards, so is not Pontoon`() {
        Assertions.assertThat(isHandPontoon(arrayOf("QH", "1C", "AC"))).isFalse()
    }

    @Test
    fun `First card is not picture or Ace, so not Pontoon`() {
        Assertions.assertThat(isHandPontoon(arrayOf("9H", "AC"))).isFalse()
    }


}