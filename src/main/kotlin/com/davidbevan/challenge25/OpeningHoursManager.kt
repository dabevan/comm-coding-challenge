package com.davidbevan.challenge25

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper


fun displayWeeksOpeningHours(inputJson: String): String {
    val weekOpeningHours = importOpeningHours(inputJson)
    return(formatOpeningHours(weekOpeningHours.daysOpeningHours))

}

fun formatOpeningHours(myarray: MutableList<DayOpeningHours>, startDayPointer: Int=0, endDayOffset: Int=1, output:String=""):String {
    if(isEndDayBeyondEndOfWeek(myarray, startDayPointer, endDayOffset)) {
        if(areWeComparingAdjacentDays(endDayOffset)) { return(output + myarray[startDayPointer]) } else { return (output) }
    }
    if(adjacentDaysHaveSameOpeningHours(myarray,startDayPointer,endDayOffset)) {
        if(isEndDayAtEndOfWeek(myarray,startDayPointer ,endDayOffset)) return formatOpeningHours(myarray, startDayPointer,endDayOffset+1, output + startDayEndDayAndOpeningHours(myarray, startDayPointer, endDayOffset))
        return formatOpeningHours(myarray, startDayPointer,endDayOffset+1, output)
    }
    return if(areWeComparingAdjacentDays(endDayOffset)) {
        formatOpeningHours(myarray, startDayPointer + endDayOffset, 1, output + oneBeforeEndDayAndOpeningHours(myarray, startDayPointer, endDayOffset))    } else {
        formatOpeningHours(myarray, startDayPointer + endDayOffset, 1, output + startDayOneBeforeEndDayAndOpeningHours(myarray, startDayPointer, endDayOffset))
    }

}

fun oneBeforeEndDayAndOpeningHours(myarray: MutableList<DayOpeningHours>, startDayPointer: Int, endDayOffset: Int) = "${myarray[startDayPointer + endDayOffset - 1].shortDay()}:${myarray[startDayPointer + endDayOffset -1].getOpeningHours()}| "
fun startDayOneBeforeEndDayAndOpeningHours(myarray: MutableList<DayOpeningHours>, startDayPointer: Int, endDayOffset: Int) = "${myarray[startDayPointer].shortDay()}-${myarray[startDayPointer + endDayOffset - 1].shortDay()}:${myarray[startDayPointer + endDayOffset -1].getOpeningHours()}| "
fun startDayEndDayAndOpeningHours(myarray: MutableList<DayOpeningHours>, startDayPointer: Int, endDayOffset: Int) = "${myarray[startDayPointer].shortDay()}-${myarray[startDayPointer+endDayOffset].shortDay()}:${myarray[startDayPointer+endDayOffset].getOpeningHours()}"
fun adjacentDaysHaveSameOpeningHours(myarray: MutableList<DayOpeningHours>, startDayPointer: Int, endDayOffset: Int) = myarray[startDayPointer+endDayOffset].getOpeningHours() == myarray[startDayPointer].getOpeningHours()
fun isEndDayBeyondEndOfWeek(myarray: MutableList<DayOpeningHours>, startDayPointer: Int, endDayOffset: Int) = myarray.size == startDayPointer+endDayOffset
fun isEndDayAtEndOfWeek(myarray: MutableList<DayOpeningHours>, startDayPointer: Int, endDayOffset: Int) = myarray.size == startDayPointer+endDayOffset+1
fun areWeComparingAdjacentDays(endDayOffset: Int) = endDayOffset==1


fun importOpeningHours(inputJson: String): WeekOpeningHours {
    //TODO change this to be an import of the JSON
    var weekOpeningHours = WeekOpeningHours()
    val openingHoursRaw = jacksonObjectMapper().readValue(inputJson, OpeningHoursSpecification::class.java)
    openingHoursRaw.openingHoursSpecification.map{ weekOpeningHours.addOpeningHours(it.dayOfWeek, it.opens, it.closes)}

    return weekOpeningHours
}

data class OpeningHoursSpecification(
    val openingHoursSpecification: List<DayOpeningHoursSpecification>
)

data class DayOpeningHoursSpecification(
    val dayOfWeek: List<String>,
    val opens: String,
    val closes: String
)


