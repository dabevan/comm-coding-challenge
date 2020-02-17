package com.davidbevan.challenge21.java;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestSudoku {
    SudokuBoard fullSudoku = new SudokuBoard(Arrays.asList(
        6,5,9,0,1,0,2,8,0,
        1,0,0,0,5,0,0,3,0,
        2,0,0,8,0,0,0,1,0,
        0,0,0,1,3,5,0,7,0,
        8,0,0,9,0,0,0,0,2,
        0,0,3,0,7,8,6,4,0,
        3,0,2,0,0,9,0,0,4,
        0,0,0,0,0,1,8,0,0,
        0,0,8,7,6,0,0,0,0
    ));

   SudokuBoard fullSudokuSolution = new SudokuBoard(Arrays.asList(
        6,5,9,3,1,4,2,8,7,
        1,8,7,6,5,2,4,3,9,
        2,3,4,8,9,7,5,1,6,
        4,2,6,1,3,5,9,7,8,
        8,7,1,9,4,6,3,5,2,
        5,9,3,2,7,8,6,4,1,
        3,1,2,5,8,9,7,6,4,
        7,6,5,4,2,1,8,9,3,
        9,4,8,7,6,3,1,2,5
    ));

   @Test
    public void test_getRow_returns_correct_row_data_for_full_board() {
        Assertions.assertThat(fullSudoku.getRow(1)).isEqualTo(Arrays.asList(1,0,0,0,5,0,0,3,0));
        Assertions.assertThat(fullSudoku.getRow(5)).isEqualTo(Arrays.asList(0,0,3,0,7,8,6,4,0));
   }

   @Test
    public void test_getCol_returns_correct_row_data_for_full_board() {
        Assertions.assertThat(fullSudoku.getCol(1)).isEqualTo(Arrays.asList(5,0,0,0,0,0,0,0,0));
        Assertions.assertThat(fullSudoku.getCol(8)).isEqualTo(Arrays.asList(0,0,0,0,2,0,4,0,0));
    }

    @Test
    public void test_getCell_returns_correct_cell_for_top_left() {
        Assertions.assertThat(fullSudoku.getCell(0,0)).isEqualTo(6);
    }

    @Test
    public void test_getCell_returns_correct_cell_for_bottom_right() {
        Assertions.assertThat(fullSudoku.getCell(8,8)).isEqualTo(0);
    }

    @Test
    public void test_getCell_returns_correct_cell_for_first_col() {
        Assertions.assertThat(fullSudoku.getCell(0,1)).isEqualTo(1);
    }

    @Test
    public void test_getCell_returns_correct_cell_for_first_row() {
        Assertions.assertThat(fullSudoku.getCell(3,0)).isEqualTo(0);
    }

    @Test
    public void test_getCell_returns_correct_cell_in_mid_of_board() {
        Assertions.assertThat(fullSudoku.getCell(1,3)).isEqualTo(0);
    }


    @Test
    public void  test_missingNumbers_in_first_row() {
        Assertions.assertThat(fullSudoku.getMissingNumbersInRow(0)).isEqualTo(Arrays.asList(3,4,7));
    }

    @Test
    public void  test_missingNumbers_in_mid_row() {
        Assertions.assertThat(fullSudoku.getMissingNumbersInRow(3)).isEqualTo(Arrays.asList(2,4,6,8,9));
    }

    @Test
    public void  test_missingNumbers_in_first_col() {
        Assertions.assertThat(fullSudoku.getMissingNumbersInCol(0)).isEqualTo(Arrays.asList(4,5,7,9));
    }

    @Test
    public void  test_missingNumbers_in_mid_col() {
        Assertions.assertThat(fullSudoku.getMissingNumbersInCol(3)).isEqualTo(Arrays.asList(2,3,4,5,6));
    }

    @Test
    public void test_missingNumbers_in_Nonet_Top_Left() {
        Assertions.assertThat(fullSudoku.getMissingNumbersInNonet(1,1)).isEqualTo(Arrays.asList(3,4,7,8));
    }

    @Test
    public void test_missingNumbers_in_Nonet_mid_mid() {
        Assertions.assertThat(fullSudoku.getMissingNumbersInNonet(3,3)).isEqualTo(Arrays.asList(2,4,6));
    }
    @Test
    public void test_missingNumbers_in_Nonet_mid_right() {
        Assertions.assertThat(fullSudoku.getMissingNumbersInNonet(8,3)).isEqualTo(Arrays.asList(1,3,5,8,9));
    }

    @Test
    public void test_missingNumbers_in_Nonet_bottom_left() {
        Assertions.assertThat(fullSudoku.getMissingNumbersInNonet(0,7)).isEqualTo(Arrays.asList(1,4,5,6,7,9));
    }

    @Test
    public void test_solving_a_sudoku() {
        Assertions.assertThat(fullSudoku.solve()).isEqualTo(fullSudokuSolution.cells);

        List<Integer> solvedBoard = fullSudoku.solve();
        for (int cellIndex = 0; cellIndex < 81; cellIndex++) {
            if(cellIndex % 9 ==0) System.out.println("");
            if(cellIndex % 27 ==0) System.out.println("");
            if(cellIndex % 3 ==0) System.out.print(" ");
            if(solvedBoard.get(cellIndex) == 0) {
                System.out.print("_ ");
            } else {
                System.out.print(solvedBoard.get(cellIndex) + " ");
            }
        }
    }


}
