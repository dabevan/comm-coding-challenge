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
    fun `Should correctly rotate cube once`() {
        //Given
        val startCube = charArrayOf('G','G','G','G','G','G','G','G','G', //Front
                                             'Y','Y','Y','Y','Y','Y','Y','Y','Y', //Back
                                             'O','O','O','O','O','O','O','O','O', //Left
                                             'R','R','R','R','R','R','R','R','R', //Right
                                             'W','W','W','W','W','W','W','W','W', //Top
                                             'B','B','B','B','B','B','B','B','B') //Bottom
        //When
        val newCube = rotateWholeCubeBy90Deg(startCube)
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
        val newCube = rotateWholeCubeBy90Deg(startCube,4)
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