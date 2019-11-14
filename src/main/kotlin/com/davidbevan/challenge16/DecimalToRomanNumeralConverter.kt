package com.davidbevan.challenge16

private val numberBases = arrayOf(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000)
private val symbolBases = arrayOf("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M")

fun decimalToRomanNumeralString(decimalValue: Int): String {

    var i = 12
    var workingNumber = decimalValue
    var romanNumeralString = ""

    while (workingNumber > 0) {
            var div = workingNumber / numberBases[i]
            workingNumber %= numberBases[i]
            while (0 < div--) {
                romanNumeralString += symbolBases[i]
            }
            i--
        }

    return romanNumeralString
}
