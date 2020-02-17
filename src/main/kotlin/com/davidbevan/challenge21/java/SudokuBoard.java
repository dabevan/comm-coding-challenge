package com.davidbevan.challenge21.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SudokuBoard {

    public List<Integer> cells;

    public SudokuBoard(List<Integer> aCells) {
        cells = aCells;
    }

    public List<Integer> getRow(Integer rowNum) {
        Integer startIndex = 9 * rowNum;
        Integer endIndex = 9 * rowNum + 9;
        return cells.subList(startIndex, endIndex);
    }

    public List<Integer> getCol(Integer colNum) {
        List<Integer> col = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0);
        for (int rowNum = 0; rowNum < 9; rowNum++) {
            col.set(rowNum, cells.get(colNum + rowNum * 9));
        }
        return col;
    }

    public Integer getCell(Integer colNum, Integer rowNum) {
        return cells.get(colNum + (rowNum * 9));
    }

    public List<Integer> getMissingNumbers(List<Integer> placedNumbers) {
        List<Integer> allNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Predicate<Integer> notPlaced = celValue -> !placedNumbers.contains(celValue);
        return allNumbers.stream().filter(notPlaced).collect(Collectors.toList());
    }

    public List<Integer> getMissingNumbersInRow(Integer rowNum) {
        return getMissingNumbers(getRow(rowNum));
    }

    public List<Integer> getMissingNumbersInCol(Integer colNum) {
        return getMissingNumbers(getCol(colNum));
    }

    public List<Integer> getMissingNumbersInNonet(Integer colNum, Integer rowNum) {
        Integer nonetColOffset = (int) Math.floor((colNum / 3) * 3);
        Integer nonetRowOffset = (int) Math.floor((rowNum / 3) * 3);
        List<Integer> placedNumbers = new ArrayList<Integer>();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                placedNumbers.add(getCell(nonetColOffset + col, nonetRowOffset + row));
            }
        }
        return getMissingNumbers(placedNumbers);
    }

    public boolean tryToSetCell(Integer colNum, Integer rowNum) {
        if (getCell(colNum, rowNum) == 0) {
            List<Integer> valuesNotInCol = getMissingNumbersInCol(colNum);
            List<Integer> valuesNotInRow = getMissingNumbersInRow(rowNum);
            List<Integer> valuesNotInNonet = getMissingNumbersInNonet(colNum, rowNum);

            List<Integer> possibleValues = valuesNotInRow.stream().filter(valuesNotInCol::contains).collect(Collectors.toList())
                    .stream().filter(valuesNotInNonet::contains).collect(Collectors.toList());
            if (possibleValues.size() == 1) {
                cells.set(colNum + (rowNum * 9), possibleValues.get(0));
                return true;
            }
        }
        return false;
    }

    public boolean walkBoardTryingToSetCells() {
        boolean managedToSetACell = false;
        for (int cellIndex = 0; cellIndex < 81; cellIndex++) {
            int col = cellIndex % 9;
            int row = (int) Math.floor(cellIndex / 9);
            if (tryToSetCell(col, row)) {
                managedToSetACell = true;
            }
        }
        return managedToSetACell;
    }

    public List<Integer> solve() {
        boolean managedToSetACell = true;
        while (managedToSetACell) {
            managedToSetACell = walkBoardTryingToSetCells();
        }
        return cells;
    }
}
