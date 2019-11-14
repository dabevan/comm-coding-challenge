package com.davidbevan.challenge16

fun romanNumeralCharacterToDecimal(romanChar: Char): Int {
    when (romanChar) {
        'I' -> return 1
        'V' -> return 5
        'X' -> return 10
        'L' -> return 50
        'C' -> return 100
        'D' -> return 500
        'M' -> return 1000
        else -> { throw RuntimeException("'$romanChar' is not a valid Roman Numeral")}
    }
}