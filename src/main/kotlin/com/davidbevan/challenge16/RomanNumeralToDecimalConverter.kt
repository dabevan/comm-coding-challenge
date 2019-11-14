package com.davidbevan.challenge16

fun romanNumeralStringToDecimal(romanString: String): Int {

    var decimalResult = 0

    var i = 0
    while (i < romanString.length) {
        val valueOfCurrentRomanNumeral: Int = romanNumeralCharacterToDecimal(romanString[i])
        if (i+1 < romanString.length) {
            val valueOfNextRomanNumeral = romanNumeralCharacterToDecimal(romanString[i+1])
            if (valueOfCurrentRomanNumeral >= valueOfNextRomanNumeral) {
                decimalResult += valueOfCurrentRomanNumeral
            } else {
                decimalResult = decimalResult + valueOfNextRomanNumeral - valueOfCurrentRomanNumeral
                i++
            }
        }
        else decimalResult += valueOfCurrentRomanNumeral
        i++
    }
    return decimalResult
}


fun romanNumeralCharacterToDecimal(romanChar: Char): Int {
    return when (romanChar) {
        'I' -> 1
        'V' -> 5
        'X' -> 10
        'L' -> 50
        'C' -> 100
        'D' -> 500
        'M' -> 1000
        else -> { throw RuntimeException("'$romanChar' is not a valid Roman Numeral")}
    }
}