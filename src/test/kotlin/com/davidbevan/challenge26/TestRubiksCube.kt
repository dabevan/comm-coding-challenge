package com.davidbevan.challenge26

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TestRubiksCube {

    @Test
    fun `Should correctly show faces for Top CW`() {
        //Given
        val startCube = listOf("GGGGGGGGG","YYYYYYYYY","OOOOOOOOO","RRRRRRRRR","WWWWWWWWW","BBBBBBBBB")
        //When
        val newCube = rotateCube(startCube, "Top", "CW")
        //Then
        val expectedNewCube = listOf("RRRGGGGGG","OOOYYYYYY","GGGOOOOOO","YYYRRRRRR","WWWWWWWWW","BBBBBBBBB")
        Assertions.assertThat(newCube).isEqualTo(expectedNewCube)
    }

   @Test
    fun `Should correctly show faces for Bottom CW`() {
        //Given
        val startCube = listOf("GGGGGGGGG","YYYYYYYYY","OOOOOOOOO","RRRRRRRRR","WWWWWWWWW","BBBBBBBBB")
        //When
        val newCube = rotateCube(startCube, "Bottom", "CW")
        //Then
        val expectedNewCube = listOf("GGGGGGOOO","YYYYYYRRR","OOOOOOYYY","RRRRRRGGG","WWWWWWWWW","BBBBBBBBB")
        Assertions.assertThat(newCube).isEqualTo(expectedNewCube)
    }

   @Test
    fun `Should correctly show faces for Left CW`() {
        //Given
        val startCube = listOf("GGGGGGGGG","YYYYYYYYY","OOOOOOOOO","RRRRRRRRR","WWWWWWWWW","BBBBBBBBB")
        //When
        val newCube = rotateCube(startCube, "Left", "CW")
        //Then
        val expectedNewCube = listOf("WGGWGGWGG","YYBYYBYYB","OOOOOOOOO","RRRRRRRRR","YWWYWWYWW","GBBGBBGBB")
        Assertions.assertThat(newCube).isEqualTo(expectedNewCube)
    }

   @Test
    fun `Should correctly show faces for Right CW`() {
        //Given
        val startCube = listOf("GGGGGGGGG","YYYYYYYYY","OOOOOOOOO","RRRRRRRRR","WWWWWWWWW","BBBBBBBBB")
        //When
        val newCube = rotateCube(startCube, "Right", "CW")
        //Then
        val expectedNewCube = listOf("GGBGGBGGB","WYYWYYWYY","OOOOOOOOO","RRRRRRRRR","WWGWWGWWG","BBYBBYBBY")
        Assertions.assertThat(newCube).isEqualTo(expectedNewCube)
    }

   @Test
    fun `Should correctly show faces for Front CW`() {
        //Given
        val startCube = listOf("GGGGGGGGG","YYYYYYYYY","OOOOOOOOO","RRRRRRRRR","WWWWWWWWW","BBBBBBBBB")
        //When
        val newCube = rotateCube(startCube, "Front", "CW")
        //Then
        val expectedNewCube = listOf("GGGGGGGGG","YYYYYYYYY","OOBOOBOOB","WRRWRRWRR","WWWWWWOOO","RRRBBBBBB")
        Assertions.assertThat(newCube).isEqualTo(expectedNewCube)
    }

   @Test
    fun `Should correctly show faces for Back CW`() {
        //Given
        val startCube = listOf("GGGGGGGGG","YYYYYYYYY","OOOOOOOOO","RRRRRRRRR","WWWWWWWWW","BBBBBBBBB")
        //When
        val newCube = rotateCube(startCube, "Back", "CW")
        //Then
        val expectedNewCube = listOf("GGGGGGGGG","YYYYYYYYY","WOOWOOWOO","RRBRRBRRB","RRRWWWWWW","BBBBBBOOO")
        Assertions.assertThat(newCube).isEqualTo(expectedNewCube)
    }


    fun rotateClockwiseThreeTimes(startCube: List<String>, layer: String) = rotateCube(rotateCube(rotateCube(startCube, layer, "CW"), layer, "CW"), layer, "CW")

    @Test
    fun `Should correctly show faces for Top CCW`() {
        //Given
        val startCube = listOf("GGGGGGGGG","YYYYYYYYY","OOOOOOOOO","RRRRRRRRR","WWWWWWWWW","BBBBBBBBB")
        //When
        val newCube = rotateCube(startCube, "Top", "CCW")
        //Then
        val expectedNewCube = rotateClockwiseThreeTimes(startCube, "Top")
        Assertions.assertThat(newCube).isEqualTo(expectedNewCube)
    }

   @Test
    fun `Should correctly show faces for Bottom CCW`() {
        //Given
        val startCube = listOf("GGGGGGGGG","YYYYYYYYY","OOOOOOOOO","RRRRRRRRR","WWWWWWWWW","BBBBBBBBB")
        //When
        val newCube = rotateCube(startCube, "Bottom", "CCW")
        //Then
        val expectedNewCube = rotateClockwiseThreeTimes(startCube, "Bottom")
        Assertions.assertThat(newCube).isEqualTo(expectedNewCube)
    }

   @Test
    fun `Should correctly show faces for Left CCW`() {
        //Given
        val startCube = listOf("GGGGGGGGG","YYYYYYYYY","OOOOOOOOO","RRRRRRRRR","WWWWWWWWW","BBBBBBBBB")
        //When
        val newCube = rotateCube(startCube, "Left", "CCW")
        //Then
        val expectedNewCube = rotateClockwiseThreeTimes(startCube, "Left")
        Assertions.assertThat(newCube).isEqualTo(expectedNewCube)
    }

   @Test
    fun `Should correctly show faces for Right CCW`() {
        //Given
        val startCube = listOf("GGGGGGGGG","YYYYYYYYY","OOOOOOOOO","RRRRRRRRR","WWWWWWWWW","BBBBBBBBB")
        //When
        val newCube = rotateCube(startCube, "Right", "CCW")
        //Then
        val expectedNewCube = rotateClockwiseThreeTimes(startCube, "Right")
        Assertions.assertThat(newCube).isEqualTo(expectedNewCube)
    }

   @Test
    fun `Should correctly show faces for Front CCW`() {
        //Given
        val startCube = listOf("GGGGGGGGG","YYYYYYYYY","OOOOOOOOO","RRRRRRRRR","WWWWWWWWW","BBBBBBBBB")
        //When
        val newCube = rotateCube(startCube, "Front", "CCW")
        //Then
        val expectedNewCube = rotateClockwiseThreeTimes(startCube, "Front")
        Assertions.assertThat(newCube).isEqualTo(expectedNewCube)
    }

   @Test
    fun `Should correctly show faces for Back CCW`() {
        //Given
        val startCube = listOf("GGGGGGGGG","YYYYYYYYY","OOOOOOOOO","RRRRRRRRR","WWWWWWWWW","BBBBBBBBB")
        //When
        val newCube = rotateCube(startCube, "Back", "CCW")
        //Then
        val expectedNewCube = rotateClockwiseThreeTimes(startCube, "Back")
        Assertions.assertThat(newCube).isEqualTo(expectedNewCube)
    }




   @Test
    fun `Should correctly rotate cube once`() {
        //Given
        val startCube = charArrayOf('G','G','G','G','G','G','G','G','G', //Front
                                             'Y','Y','Y','Y','Y','Y','Y','Y','Y', //Back
                                             'O','O','O','O','O','O','O','O','O', //Left
                                             'R','R','R','R','R','R','R','R','R', //Right
                                             'W','W','W','W','W','W','W','W','W', //Top
                                             'B','B','B','B','B','B','B','B','B') //Bottom
        //When
        val newCube = rotateWholeCubeRightBy90Deg(startCube)
        //Then
        val expectedNewCube = charArrayOf('G','G','G','G','G','G','G','G','G', //Front
                                             'Y','Y','Y','Y','Y','Y','Y','Y','Y', //Back
                                             'B','B','B','B','B','B','B','B','B', //Left
                                             'W','W','W','W','W','W','W','W','W', //Right
                                             'O','O','O','O','O','O','O','O','O', //Top
                                             'R','R','R','R','R','R','R','R','R') //Bottom
        Assertions.assertThat(newCube).containsExactly(*expectedNewCube)
    }

   @Test
    fun `Should correctly rotate cube 4 times`() {
        //Given
        val startCube = charArrayOf('G','G','G','G','G','G','G','G','G', //Front
                                             'Y','Y','Y','Y','Y','Y','Y','Y','Y', //Back
                                             'O','O','O','O','O','O','O','O','O', //Left
                                             'R','R','R','R','R','R','R','R','R', //Right
                                             'W','W','W','W','W','W','W','W','W', //Top
                                             'B','B','B','B','B','B','B','B','B') //Bottom
        //When
        val newCube = rotateWholeCubeRightBy90Deg(startCube,4)
        //Then
        val expectedNewCube = charArrayOf('G','G','G','G','G','G','G','G','G', //Front
                                             'Y','Y','Y','Y','Y','Y','Y','Y','Y', //Back
                                             'O','O','O','O','O','O','O','O','O', //Left
                                             'R','R','R','R','R','R','R','R','R', //Right
                                             'W','W','W','W','W','W','W','W','W', //Top
                                             'B','B','B','B','B','B','B','B','B') //Bottom
        Assertions.assertThat(newCube).containsExactly(*expectedNewCube)
    }



}