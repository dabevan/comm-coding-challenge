package com.davidbevan.challenge1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class TimeFormatterTest {

    private val timeFormatter = TimeFormatter()

    val second = 1
    val minute = second * 60
    val hour = minute * 60
    val day = hour * 24
    val year = day * 365

    @Test
    fun `Should display none for zero seconds`() {
        val timeInSeconds = 0
        val expectedTimeFormatedAsString = "none"
        assertEquals(expectedTimeFormatedAsString, timeFormatter.formatTime(timeInSeconds))
    }

    @Test
    fun `Should display exactly one second`() {
        val timeInSeconds = second
        val expectedTimeFormatedAsString = "1 second"
        assertEquals(expectedTimeFormatedAsString, timeFormatter.formatTime(timeInSeconds))
    }

    @Test
    fun `Should display exactly two seconds`() {
        val timeInSeconds = 2 * second
        val expectedTimeFormatedAsString = "2 seconds"
        assertEquals(expectedTimeFormatedAsString, timeFormatter.formatTime(timeInSeconds))
    }

    @Test
    fun `Should display exactly one minute`() {
        val timeInSeconds = minute
        val expectedTimeFormatedAsString = "1 minute"
        assertEquals(expectedTimeFormatedAsString, timeFormatter.formatTime(timeInSeconds))
    }

    @Test
    fun `Should display exactly two minutes`() {
        val timeInSeconds = 2 * minute
        val expectedTimeFormatedAsString = "2 minutes"
        assertEquals(expectedTimeFormatedAsString, timeFormatter.formatTime(timeInSeconds))
    }

    @Test
    fun `Should display exactly one hour`() {
        val timeInSeconds = hour
        val expectedTimeFormatedAsString = "1 hour"
        assertEquals(expectedTimeFormatedAsString, timeFormatter.formatTime(timeInSeconds))
    }

    @Test
    fun `Should display exactly two hours`() {
        val timeInSeconds = 2 * hour
        val expectedTimeFormatedAsString = "2 hours"
        assertEquals(expectedTimeFormatedAsString, timeFormatter.formatTime(timeInSeconds))
    }

    @Test
    fun `Should display exactly one day`() {
        val timeInSeconds = day
        val expectedTimeFormatedAsString = "1 day"
        assertEquals(expectedTimeFormatedAsString, timeFormatter.formatTime(timeInSeconds))
    }

    @Test
    fun `Should display exactly two days`() {
        val timeInSeconds = 2 * day
        val expectedTimeFormatedAsString = "2 days"
        assertEquals(expectedTimeFormatedAsString, timeFormatter.formatTime(timeInSeconds))
    }

    @Test
    fun `Should display one of every unit`() {
        val timeInSeconds = year + day + hour + minute + second
        val expectedTimeFormatedAsString = "1 year, 1 day, 1 hour, 1 minute and 1 second"
        assertEquals(expectedTimeFormatedAsString, timeFormatter.formatTime(timeInSeconds))
    }

    @Test
    fun `Should display two of every unit`() {
        val timeInSeconds = 2*year + 2*day + 2* hour + 2*minute + 2*second
        val expectedTimeFormatedAsString = "2 years, 2 days, 2 hours, 2 minutes and 2 seconds"
        assertEquals(expectedTimeFormatedAsString, timeFormatter.formatTime(timeInSeconds))
    }

    @Test
    fun `Should display one of every unit except seconds`() {
        val timeInSeconds = year + day + hour + minute
        val expectedTimeFormatedAsString = "1 year, 1 day, 1 hour and 1 minute"
        assertEquals(expectedTimeFormatedAsString, timeFormatter.formatTime(timeInSeconds))
    }

    @Test
    fun `Should display one of every unit except seconds and minutes`() {
        val timeInSeconds = year + day + hour
        val expectedTimeFormatedAsString = "1 year, 1 day and 1 hour"
        assertEquals(expectedTimeFormatedAsString, timeFormatter.formatTime(timeInSeconds))
    }

    @Test
    fun `Should display one of every unit except seconds, minutes and hours`() {
        val timeInSeconds = year + day
        val expectedTimeFormatedAsString = "1 year and 1 day"
        assertEquals(expectedTimeFormatedAsString, timeFormatter.formatTime(timeInSeconds))
    }

    @Test
    fun `Should display years, no days, hours, no minutes, seconds`() {
        val timeInSeconds = year + hour + second
        val expectedTimeFormatedAsString = "1 year, 1 hour and 1 second"
        assertEquals(expectedTimeFormatedAsString, timeFormatter.formatTime(timeInSeconds))
    }

    @Test
    fun `Should display years and seconds`() {
        val timeInSeconds = year + second
        val expectedTimeFormatedAsString = "1 year and 1 second"
        assertEquals(expectedTimeFormatedAsString, timeFormatter.formatTime(timeInSeconds))
    }

    @Test
    fun `Should display days and minutes`() {
        val timeInSeconds = day + minute
        val expectedTimeFormatedAsString = "1 day and 1 minute"
        assertEquals(expectedTimeFormatedAsString, timeFormatter.formatTime(timeInSeconds))
    }

    @Test
    fun `Should display days, hours and minutes`() {
        val timeInSeconds = day + hour + minute
        val expectedTimeFormatedAsString = "1 day, 1 hour and 1 minute"
        assertEquals(expectedTimeFormatedAsString, timeFormatter.formatTime(timeInSeconds))
    }
//    @Test
//    fun a() {
//      println(timeFormatter.formatTime(1))
//      println(timeFormatter.formatTime(60))
//      println(timeFormatter.formatTime(3600))
//      println(timeFormatter.formatTime(86400))
//      println(timeFormatter.formatTime(31536000))
//
//      println(timeFormatter.formatTime(2))
//      println(timeFormatter.formatTime(61))
//      println(timeFormatter.formatTime(3601))
//      println(timeFormatter.formatTime(86401))
//      println(timeFormatter.formatTime(31536001))
//      println(timeFormatter.formatTime(41536001))
//}
}