package com.davidbevan.challenge25


fun displayWeeksOpeningHours(inputJson: String) {
    val weekOpeningHours = importOpeningHours(inputJson)
    val groupedOpeningHours = weekOpeningHours.daysOpeningHours.groupBy { it.getOpeningHours() }
//    println(groupedOpeningHours)
//    println(groupedOpeningHours.map { it.key })
//    println(groupedOpeningHours.map { it.key }.map { groupedOpeningHours[it]}.map {it})
//    println(groupedOpeningHours.keys)
//    println(groupedOpeningHours[groupedOpeningHours.keys.first()]?.first()?.day)
//    println(groupedOpeningHours.keys.map {groupedOpeningHours[it]?.map {it.day}})
    lookForConsecutivlyEqualOpeningHours(0, weekOpeningHours)

}

fun lookForConsecutivlyEqualOpeningHours(start: Int, weekOpeningHours: WeekOpeningHours) {
    val firstDay = weekOpeningHours.daysOpeningHours[start].day
    val firstDayOpeningHours = weekOpeningHours.daysOpeningHours[start].getOpeningHours()

    var start1 = start
    var dayIndex = start1
    while(dayIndex < 6 ) {
        val startDay = weekOpeningHours.daysOpeningHours[start1]
        val thisDay = weekOpeningHours.daysOpeningHours[dayIndex]
        val nextDay = weekOpeningHours.daysOpeningHours[++dayIndex]
        //println(thisDay)
        if (thisDay.getOpeningHours() == nextDay.getOpeningHours()){
            if(dayIndex == 6) {
                if(dayIndex == start1 +1) {
                    print("${startDay.day.substring(0,3)}:${startDay.getOpeningHours()}")
                    start1 = ++dayIndex
                } else {
                    print("${startDay.day.substring(0,3)}-${thisDay.day.substring(0,3)}:${startDay.getOpeningHours()}")
                    start1 = dayIndex
                }
            }
        } else {
            if (dayIndex == start1 + 1) {
                print("${startDay.day.substring(0, 3)}:${startDay.getOpeningHours()}, ")
                start1 = ++dayIndex
            } else {
                print("${startDay.day.substring(0, 3)}-${thisDay.day.substring(0,3)}:${startDay.getOpeningHours()}, ")
                start1 = dayIndex
            }
        }
        if(dayIndex == 6) {
            print("${nextDay.day.substring(0,3)}:${nextDay.getOpeningHours()}")
        }
    }
}

fun importOpeningHours(inputJson: String): WeekOpeningHours {
    var weekOpeningHours = WeekOpeningHours()
    weekOpeningHours.addOpeningHours("Monday", OpeningHours("10:00", "20:00"))
    weekOpeningHours.addOpeningHours("Tuesday", OpeningHours("6:00", "20:00"))
    weekOpeningHours.addOpeningHours("Wednesday", OpeningHours("9:00", "19:00"))
    weekOpeningHours.addOpeningHours("Thursday", OpeningHours("10:00", "20:00"))
    weekOpeningHours.addOpeningHours("Friday", OpeningHours("10:00", "20:00"))
    weekOpeningHours.addOpeningHours("Saturday", OpeningHours("10:00", "20:00"))
    weekOpeningHours.addOpeningHours("Sunday", OpeningHours("7:00", "20:00"))
    return weekOpeningHours
}

fun main() {
    displayWeeksOpeningHours("")
}