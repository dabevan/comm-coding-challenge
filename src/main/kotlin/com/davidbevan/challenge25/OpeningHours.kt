package com.davidbevan.challenge25


class WeekOpeningHours() {
    var daysOpeningHours: MutableList<DayOpeningHours> = mutableListOf(
                                    DayOpeningHours("Monday"),
                                    DayOpeningHours("Tuesday"),
                                    DayOpeningHours("Wednesday"),
                                    DayOpeningHours("Thursday"),
                                    DayOpeningHours("Friday"),
                                    DayOpeningHours("Saturday"),
                                    DayOpeningHours("Sunday"))


    fun addOpeningHours(day:String, openingHours: OpeningHours) {
        findOpeningHoursForDay(day).addOpeningHours(openingHours)
    }

    fun findOpeningHoursForDay(day:String): DayOpeningHours {
        return daysOpeningHours.filter { dayOpeningHours -> dayOpeningHours.day == day }.first()
    }
}

class DayOpeningHours(val day:String) {
    private val openingHoursForThisDay = mutableListOf<OpeningHours>()

    fun shortDay() = day.substring(0,3)

    fun addOpeningHours(openingHours: OpeningHours) {
        openingHoursForThisDay.add(openingHours)
    }

    fun getOpeningHours(): String {
        return openingHoursForThisDay.map{ it.toString()}.joinToString { it.toString() }
    }

    override fun toString(): String {
        return this.day.substring(0,3) + ":" + getOpeningHours()
    }
}

data class OpeningHours(val open: String, val close: String) {
    override fun toString(): String {
        return "${shortTimeFormat(open)}-${shortTimeFormat(close)}"
    }

    fun shortTimeFormat(time:String):String {
        val hour = time.substringBefore(":")
        val minute = time.substringAfter(":")
        val minuteString = if (minute == "00") "" else ":$minute"
        if (hour.toInt() == 0 && minute.toInt() == 0) return "Midnight"
        if (hour.toInt() == 0) return "${12}${minuteString}am"
        if (hour.toInt() < 12) return "${hour}${minuteString}am"
        if (hour.toInt() == 12 && minute.toInt() == 0) return "Midday"
        if (hour.toInt() == 12) return "${hour}${minuteString}pm"
        if (hour.toInt() == 24 && minute.toInt() == 0) return "Midnight"
        if (hour.toInt() == 24) return "${12}${minuteString}am"
        if (hour.toInt() > 12) return "${(hour.toInt() - 12)}${minuteString}pm"
        return time
    }

}
