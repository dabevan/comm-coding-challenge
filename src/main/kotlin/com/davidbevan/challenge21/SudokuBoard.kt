package com.davidbevan.challenge21

class SudokuBoard(var cells: IntArray) {

    fun getRow(rowNum: Int): IntArray {
        val startIndex = 9 * rowNum
        val endIndex = 9 * rowNum + 9
        return cells.copyOfRange(startIndex,endIndex)
    }

    fun getCol(colNum: Int): IntArray {
        var col = IntArray(9)
        for(rowNum in 0..8) {
            col.set(rowNum, cells.get(colNum + rowNum * 9))
        }
        return col
    }

    fun getCell(colNum: Int, rowNum: Int) = cells.get(colNum + (rowNum * 9))

    fun getMissingNumbers(placedNumbers: IntArray) = intArrayOf(1,2,3,4,5,6,7,8,9).filter {!placedNumbers.contains(it)}.toIntArray()

    fun getMissingNumbersInRow(rowNum: Int) = getMissingNumbers(getRow(rowNum))

    fun getMissingNumbersInCol(colNum: Int) = getMissingNumbers(getCol(colNum))

    fun getMissingNumbersInNonet(colNum: Int, rowNum: Int): IntArray {
        val nonetColOffset = (colNum / 3) * 3
        val nonetRowOffset = (rowNum / 3) * 3
        var placedNumbers = IntArray(0)
        for(row in 0..2) {
            for(col in 0..2) {
                placedNumbers = placedNumbers.plus(getCell(nonetColOffset + col, nonetRowOffset + row))
            }
        }
        return getMissingNumbers(placedNumbers)
    }

    fun tryToSetCell(colNum: Int, rowNum: Int): Boolean {
        if(getCell(colNum, rowNum) == 0) {
            val possibleValues = getMissingNumbersInCol(colNum).filter { num ->
                getMissingNumbersInRow(rowNum).contains(num)
            }.filter { num ->
                getMissingNumbersInNonet(colNum, rowNum).contains(num)
            }
            if (possibleValues.size == 1) {
                cells.set(colNum + (rowNum * 9),possibleValues.first())
                return true
            }
        }
        return false
    }

    fun walkBoardTryingToSetCells(): Boolean {
        var managedToSetACell = false
        cells.forEachIndexed() { index, cell ->
            val col = index.rem(9)
            val row = (Math.floor((index/9).toDouble())).toInt()
            if (tryToSetCell(col, row)) managedToSetACell = true
        }
        return managedToSetACell
    }

    fun solve(): IntArray {
        var managedToSetCell = true
        while (managedToSetCell) {
            managedToSetCell = walkBoardTryingToSetCells()
        }
        return cells
    }
}