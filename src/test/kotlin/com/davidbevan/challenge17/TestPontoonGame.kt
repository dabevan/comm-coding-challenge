package com.davidbevan.challenge17

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions


class TestPontoonGame {

    @Test
    fun `Hand is Pontoon`() {
        Assertions.assertThat(isHandPontoon(listOf("QH", "AC"))).equals(true)
    }

    @Test
    fun `Hand has three cards, so is not Pontoon`() {
        Assertions.assertThat(isHandPontoon(listOf("QH", "1C", "AC"))).equals(false)
    }

    @Test
    fun `First card is not picture or Ace, so not Pontoon`() {
        Assertions.assertThat(isHandPontoon(listOf("9H", "AC"))).equals(false)
    }
}
