package com.davidbevan.challenge1

class TimeFormatter {
    fun formatTime(timeInSeconds: Int) : String {
        val timeUnitHierachy = TimeUnit("year", 31536000,
                               //TimeUnit("quarter", 7884000, //other units of time can be easily incorporated
                               //TimeUnit("month", 2628000,
                               TimeUnit("day", 86400,
                               TimeUnit("hour", 3600,
                               TimeUnit("minute", 60,
                               TimeUnit("second", 1, null)))))

        val formattedTime = timeUnitHierachy.getFormattedTime(timeInSeconds)
        println("${timeInSeconds} seconds equals ${formattedTime}")
        return formattedTime
    }
}