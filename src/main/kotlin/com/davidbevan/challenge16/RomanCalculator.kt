package com.davidbevan.challenge16

fun add(romanNumeralString1: String, romanNumeralString2: String) : String {
    return decimalToRomanNumeralString(romanNumeralStringToDecimal(romanNumeralString1) + romanNumeralStringToDecimal(romanNumeralString2))
}

fun subtract(romanNumeralString1: String, romanNumeralString2: String) : String {
    return decimalToRomanNumeralString(romanNumeralStringToDecimal(romanNumeralString1) - romanNumeralStringToDecimal(romanNumeralString2))
}

fun multiply(romanNumeralString1: String, romanNumeralString2: String) : String {
    return decimalToRomanNumeralString(romanNumeralStringToDecimal(romanNumeralString1) * romanNumeralStringToDecimal(romanNumeralString2))
}

fun divide(romanNumeralString1: String, romanNumeralString2: String) : String {
    return decimalToRomanNumeralString(romanNumeralStringToDecimal(romanNumeralString1) / romanNumeralStringToDecimal(romanNumeralString2))
}