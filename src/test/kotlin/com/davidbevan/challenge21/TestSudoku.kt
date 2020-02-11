package com.davidbevan.challenge21

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TestSudoku {

    val fullSudoku = SudokuBoard(intArrayOf(
        6,5,9,0,1,0,2,8,0,
        1,0,0,0,5,0,0,3,0,
        2,0,0,8,0,0,0,1,0,
        0,0,0,1,3,5,0,7,0,
        8,0,0,9,0,0,0,0,2,
        0,0,3,0,7,8,6,4,0,
        3,0,2,0,0,9,0,0,4,
        0,0,0,0,0,1,8,0,0,
        0,0,8,7,6,0,0,0,0
    ))

   val fullSudokuSolution = SudokuBoard(intArrayOf(
        6,5,9,3,1,4,2,8,7,
        1,8,7,6,5,2,4,3,9,
        2,3,4,8,9,7,5,1,6,
        4,2,6,1,3,5,9,7,8,
        8,7,1,9,4,6,3,5,2,
        5,9,3,2,7,8,6,4,1,
        3,1,2,5,8,9,7,6,4,
        7,6,5,4,2,1,8,9,3,
        9,4,8,7,6,3,1,2,5
    ))

    @Test
    fun `test getRow returns correct row data for full board`() {
        Assertions.assertThat(fullSudoku.getRow(1)).isEqualTo(intArrayOf(1,0,0,0,5,0,0,3,0))
        Assertions.assertThat(fullSudoku.getRow(5)).isEqualTo(intArrayOf(0,0,3,0,7,8,6,4,0))
    }

    @Test
    fun `test getCol returns correct row data for full board`() {
        Assertions.assertThat(fullSudoku.getCol(1)).isEqualTo(intArrayOf(5,0,0,0,0,0,0,0,0))
        Assertions.assertThat(fullSudoku.getCol(8)).isEqualTo(intArrayOf(0,0,0,0,2,0,4,0,0))
    }
    
    @Test
    fun `test getCell returns correct cell for top left`() {
        Assertions.assertThat(fullSudoku.getCell(0,0)).isEqualTo(6)
    }

    @Test
    fun `test getCell returns correct cell for bottom right`() {
        Assertions.assertThat(fullSudoku.getCell(8,8)).isEqualTo(0)
    }

    @Test
    fun `test getCell returns correct cell for first col`() {
        Assertions.assertThat(fullSudoku.getCell(0,1)).isEqualTo(1)
    }

    @Test
    fun `test getCell returns correct cell for first row`() {
        Assertions.assertThat(fullSudoku.getCell(3,0)).isEqualTo(0)
    }

    @Test
    fun `test getCell returns correct cell in mid of board`() {
        Assertions.assertThat(fullSudoku.getCell(1,3)).isEqualTo(0)
    }



    @Test
    fun `test missingNumbers in first row`() {
        Assertions.assertThat(fullSudoku.getMissingNumbersInRow(0)).isEqualTo(intArrayOf(3,4,7))
    }

    @Test
    fun `test missingNumbers in mid row`() {
        Assertions.assertThat(fullSudoku.getMissingNumbersInRow(3)).isEqualTo(intArrayOf(2,4,6,8,9))
    }

    @Test
    fun `test missingNumbers in first col`() {
        Assertions.assertThat(fullSudoku.getMissingNumbersInCol(0)).isEqualTo(intArrayOf(4,5,7,9))
    }

    @Test
    fun `test missingNumbers in mid col`() {
        Assertions.assertThat(fullSudoku.getMissingNumbersInCol(3)).isEqualTo(intArrayOf(2,3,4,5,6))
    }

    @Test
    fun `test missingNumbers in Nonet Top Left`() {
        Assertions.assertThat(fullSudoku.getMissingNumbersInNonet(1,1)).isEqualTo(intArrayOf(3,4,7,8))
    }

    @Test
    fun `test missingNumbers in Nonet mid mid`() {
        Assertions.assertThat(fullSudoku.getMissingNumbersInNonet(3,3)).isEqualTo(intArrayOf(2,4,6))
    }
    @Test
    fun `test missingNumbers in Nonet mid right`() {
        Assertions.assertThat(fullSudoku.getMissingNumbersInNonet(8,3)).isEqualTo(intArrayOf(1,3,5,8,9))
    }

    @Test
    fun `test missingNumbers in Nonet bottom left`() {
        Assertions.assertThat(fullSudoku.getMissingNumbersInNonet(0,7)).isEqualTo(intArrayOf(1,4,5,6,7,9))
    }

    @Test
    fun `test solving a sudoku`() {
        Assertions.assertThat(fullSudoku.solve()).isEqualTo(fullSudokuSolution.cells)
        fullSudoku.cells.forEachIndexed() { index, cell ->
          if (index.rem(9) == 0) println()
          if (index.rem(27) == 0) println()
          if (index.rem(3) == 0) print(" ")
          if (cell == 0) {
            print("_ ")
          } else {
            print("$cell ")
          }
        }
    }

    
}