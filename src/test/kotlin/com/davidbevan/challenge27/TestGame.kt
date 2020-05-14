package com.davidbevan.challenge27

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TestGame {

    fun getMessage(win: Boolean, numberOfShots: Int):String {
        var message = "### NUMBER OF SHOTS FIRED: $numberOfShots."
        if (win){
            message += " ALL ENEMY SHIPS SUNK ###"
        } else {
            message += " ENEMY NOT DESTROYED ###"
        }
        return message
    }

    @Test
    fun `Should correctly count one unique squares in one move`() {
        //Given
        val game = Game()
        //When
        shoot("A1", game)
        //Then
        val expectedShots = 1
        Assertions.assertThat(game.numberOfShots).isEqualTo(expectedShots)
    }

    @Test
    fun `Should correctly count two unique squares in one move`() {
        //Given
        val game = Game()
        //When
        shoot("A1B1", game)
        //Then
        val expectedShots = 2
        Assertions.assertThat(game.numberOfShots).isEqualTo(expectedShots)
    }

    @Test
    fun `Should correctly count two unique squares in two moves`() {
        //Given
        val game = Game()
        //When
        shoot("A1", game)
        shoot("B1", game)
        //Then
        val expectedShots = 2
        Assertions.assertThat(game.numberOfShots).isEqualTo(expectedShots)
    }

    @Test
    fun `Should correctly count two unique squares in two moves where one square is repeated`() {
        //Given
        val game = Game()
        //When
        shoot("A1B1", game)
        shoot("B1", game)
        //Then
        val expectedShots = 2
        Assertions.assertThat(game.numberOfShots).isEqualTo(expectedShots)
    }

    @Test
    fun `Should sink all ships after firing at all squares`() {
        //Given
        //When
        val expectedResult = AllSquaresAlgorithm().execute()
        //Then
        Assertions.assertThat(getMessage(true, 100)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should sink all ships after firing at minimum number of squares`() {
        //Given
        //When
        val expectedResult = KnownShipPositionsAlgorithm().execute()
        //Then
        Assertions.assertThat(getMessage(true, 18)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should report that not all ships sunk when not all ships have been sunk`() {
        //Given
        //When
        val expectedResult = GuessingAtShipPositionsAlgorithm().execute()
        //Then
        Assertions.assertThat(getMessage(false, 5)).isEqualTo(expectedResult)
    }





}