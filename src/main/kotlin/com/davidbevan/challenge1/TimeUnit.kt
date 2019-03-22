package com.davidbevan.challenge1

import kotlin.math.floor

class TimeUnit(val name: String, val secondsPerUnit: Int, val childTimeUnit: TimeUnit?) {

    fun getFormattedTime(seconds: Int) : String {
        val formattedText = getFormattedChildTime(seconds)
        return if(formattedText == "") { "none" } else { formattedText }
    }

    fun getFormattedChildTime(seconds : Int) : String {
        val numberOfThisUnit = floor((seconds/secondsPerUnit).toDouble()).toInt()
        val  remainingSeconds = seconds.rem(secondsPerUnit)
        var formattedText = getFormattedTextForThisUnit(numberOfThisUnit)
        val childFormattedText = (childTimeUnit?.getFormattedChildTime(remainingSeconds))?:""
        formattedText = combineUnitsWithAppropriateConjunction(formattedText, childFormattedText)
        return formattedText
    }

    private fun getFormattedTextForThisUnit(numberOfThisUnit: Int) : String {
        if (numberOfThisUnit == 0) { return "" }
        if (numberOfThisUnit == 1) { return "1 $name" }
        return "$numberOfThisUnit ${name}s"
    }

    private fun combineUnitsWithAppropriateConjunction(formattedText: String, childFormattedText: String) : String {
        if (formattedText == "") { return childFormattedText }
        if (childFormattedText == "") { return formattedText }
        if (!childFormattedText.contains(" and ")) { return "${formattedText} and ${childFormattedText}" }
        return "${formattedText}, ${childFormattedText}"
    }

}