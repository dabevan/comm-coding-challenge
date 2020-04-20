package com.davidbevan.challenge25


fun displayWeeksOpeningHours(inputJson: String) {
    val weekOpeningHours = importOpeningHours(inputJson)
    println(blahblah(weekOpeningHours.daysOpeningHours))

}

fun blahblah(myarray: MutableList<DayOpeningHours>, n: Int=0, i: Int=1, output:String=""):String {
    if(isEndDayBeyondEndOfWeek(myarray, n, i)) {
        if(areWeComparingAdjacentDays(i)) { return(output + myarray[n]) } else { return (output) }
    }
    if(adjacentDaysHaveSameOpeningHours(myarray,n,i)) {
        if(isEndDayAtEndOfWeek(myarray,n ,i)) return blahblah(myarray, n,i+1, output + startDayEndDayAndOpeningHours(myarray, n, i))
        return blahblah(myarray, n,i+1, output)
    }
    return if(areWeComparingAdjacentDays(i)) {
        blahblah(myarray, n + i, 1, output + oneBeforeEndDayAndOpeningHours(myarray, n, i))    } else {
        blahblah(myarray, n + i, 1, output + startDayOneBeforeEndDayAndOpeningHours(myarray, n, i))
    }

}

fun oneBeforeEndDayAndOpeningHours(myarray: MutableList<DayOpeningHours>, n: Int, i: Int) = myarray[n + i - 1].shortDay() + ":" + myarray[n + i -1].getOpeningHours() + "| "
fun startDayOneBeforeEndDayAndOpeningHours(myarray: MutableList<DayOpeningHours>, n: Int, i: Int) = myarray[n].shortDay() + "-" + myarray[n + i - 1].shortDay() + ":" + myarray[n + i -1].getOpeningHours() + "| "
fun startDayEndDayAndOpeningHours(myarray: MutableList<DayOpeningHours>, n: Int, i: Int) = myarray[n].shortDay() + "-" + myarray[n+i].shortDay() + ":" + myarray[n+i].getOpeningHours()
fun adjacentDaysHaveSameOpeningHours(myarray: MutableList<DayOpeningHours>, n: Int, i: Int) = myarray[n+i].getOpeningHours() == myarray[n].getOpeningHours()
fun isEndDayBeyondEndOfWeek(myarray: MutableList<DayOpeningHours>, n: Int, i: Int) = myarray.size == n+i
fun isEndDayAtEndOfWeek(myarray: MutableList<DayOpeningHours>, n: Int, i: Int) = myarray.size == n+i+1
fun areWeComparingAdjacentDays(i: Int) = i==1


fun importOpeningHours(inputJson: String): WeekOpeningHours {
    //TODO change this to be an import of the JSON
    var weekOpeningHours = WeekOpeningHours()
    weekOpeningHours.addOpeningHours("Monday", OpeningHours("10:00", "11:00"))
    weekOpeningHours.addOpeningHours("Tuesday", OpeningHours("10:00", "11:00"))
    weekOpeningHours.addOpeningHours("Wednesday", OpeningHours("10:00", "11:00"))
    weekOpeningHours.addOpeningHours("Thursday", OpeningHours("10:00", "11:00"))
    weekOpeningHours.addOpeningHours("Friday", OpeningHours("10:00", "11:00"))
    weekOpeningHours.addOpeningHours("Saturday", OpeningHours("10:00", "12:00"))
    weekOpeningHours.addOpeningHours("Sunday", OpeningHours("10:00", "15:00"))
    return weekOpeningHours
}

fun main() {
    displayWeeksOpeningHours("")

    val myarray = listOf<Int>(1,2,4,6,7,8,10,12,13,14,15,16)
    println(blah(myarray))
}

fun blah(myarray: List<Int>, n: Int=0, i: Int=1, output:String=""):String {
    if(myarray.size == n+i) {
        if(i==1) { return(output + myarray[n]) } else { return (output) }
    }
    if(myarray[n+i] == myarray[n] + i) {
        if(myarray.size == n+i+1) return blah(myarray, n,i+1, output + myarray[n] + "-" + myarray[n+i] + ",")
        return blah(myarray, n,i+1, output)
    }
    if(i==1) {
        return blah(myarray, n + i, 1, output + myarray[n + i - 1] + ",")
    } else {
        return blah(myarray, n + i, 1, output + myarray[n] + "-" + myarray[n + i - 1] + ",")
    }
}