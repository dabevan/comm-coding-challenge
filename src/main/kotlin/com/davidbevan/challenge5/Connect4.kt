package com.davidbevan.challenge5

class Connect4(var board :Array<String>) {

    var lengthOfWinningLine = 4

    fun getGridStatus() :String {
        val winner = checkWinner()
        if(winner != null) return winner

        val draw = checkDraw()
        if(draw != null) return draw

        val whosNext = whosNext()
        if(whosNext != null) return whosNext

        return "BOARD ERROR"
    }


    fun checkWinner():String? {
        var rowIndex = 0
        var colIndex = 0
        board.map { row ->
            for(position in row) {
                if (position != '.') {
                    if(checkForWinningLines(colIndex,rowIndex,position,lengthOfWinningLine)) {
                        when (position) {
                            'y','Y' -> return "Yellow wins"
                            'r','R' -> return "Red wins"
                        }
                    }
                }
                colIndex++
            }
            colIndex=0
            rowIndex++
        }
        return null
    }


    fun checkDraw():String? {
        board.map { row ->
            for(position in row) {
                if (position == '.') { return null }
            }
        }
        return "Draw"
    }


    fun whosNext():String? {
        board.map { row ->
            for(position in row) {
                if (position == 'Y') { return "Red plays next" }
                if (position == 'R') { return "Yellow plays next" }
            }
        }
        return null
    }


    fun getItemAtBoardPosition(col :Int, row:Int):Char? {
        var colIndex=0
        if (row < board.size && row >= 0) {
            for (position in board[row]) {
                if (colIndex == col) {
                    return position
                }
                colIndex++
            }
        }
        return null
    }


    private fun checkForWinningLines(col :Int, row :Int, colour: Char, length :Int): Boolean {
        return (checkForWinningLineRight(col+1, row,colour,length-1) ||
                checkForWinningLineDown(col, row+1,colour,length-1) ||
                checkForWinningLineDiagonalUpRight(col+1, row-1,colour,length-1) ||
                checkForWinningLineDiagonalDownRight(col+1, row+1,colour,length-1)
                )
    }


    private fun checkForWinningLineRight(col :Int, row :Int, colour: Char, length :Int): Boolean {
        while (length > 0) {
            if( getItemAtBoardPosition(col,row)?.toUpperCase() == colour.toUpperCase()) {
                return checkForWinningLineRight(col+1, row,colour,length-1)
            } else {
                return false
            }
        }
        return true
    }


    private fun checkForWinningLineDown(col :Int, row :Int, colour: Char, length :Int): Boolean {
        while (length > 0) {
            if( getItemAtBoardPosition(col,row)?.toUpperCase() == colour.toUpperCase()) {
                return checkForWinningLineDown(col, row+1,colour,length-1)
            } else {
                return false
            }
        }
        return true
    }


    private fun checkForWinningLineDiagonalUpRight(col :Int, row :Int, colour: Char, length :Int): Boolean {
        while (length > 0) {
            if( getItemAtBoardPosition(col,row)?.toUpperCase() == colour.toUpperCase()) {
                return checkForWinningLineDiagonalUpRight(col+1, row-1,colour,length-1)
            } else {
                return false
            }
        }
        return true
    }


    private fun checkForWinningLineDiagonalDownRight(col :Int, row :Int, colour: Char, length :Int): Boolean {
        while (length > 0) {
            if( getItemAtBoardPosition(col,row)?.toUpperCase() == colour.toUpperCase()) {
                return checkForWinningLineDiagonalDownRight(col+1, row+1,colour,length-1)
            } else {
                return false
            }
        }
        return true
    }

}