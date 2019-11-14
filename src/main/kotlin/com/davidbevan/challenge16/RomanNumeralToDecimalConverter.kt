package com.davidbevan.challenge16

fun romanNumeralStringToDecimal(romanString: String): Int {

    var decimalResult = 0

    var i = 0
    while (i < romanString.length)
    {
        val valueOfCurrentRomanNumeral = romanNumeralCharacterToDecimal(romanString[i]);

        if (i+1 < romanString.length)
        {
            val valueOfNextRomanNumeral = romanNumeralCharacterToDecimal(romanString[i+1]);

            if (valueOfCurrentRomanNumeral >= valueOfNextRomanNumeral)
            {
                decimalResult = decimalResult + valueOfCurrentRomanNumeral;
            }
            else
            {
                decimalResult = decimalResult + valueOfNextRomanNumeral - valueOfCurrentRomanNumeral;
                i++;
            }
        }
        else
        {
            decimalResult = decimalResult + valueOfCurrentRomanNumeral;

        }
        i++
    }
    return decimalResult;
}


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