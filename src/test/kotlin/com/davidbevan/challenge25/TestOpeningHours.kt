package com.davidbevan.challenge25

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TestOpeningHours {
    @Test
    fun `Should correctly identify am and pm for standard case`() {
        //Given
        val weekOpeningHours = WeekOpeningHours()
        //When
        weekOpeningHours.addOpeningHours("Monday", OpeningHours("10:00", "20:00"))
        //Then
        Assertions.assertThat(weekOpeningHours.findOpeningHoursForDay("Monday").toString()).isEqualTo("Mon:10am-8pm")
    }

    @Test
    fun `Should correctly identify am and pm for edge cases`() {
        //Given
        val weekOpeningHours = WeekOpeningHours()
        //When
        weekOpeningHours.addOpeningHours("Monday", OpeningHours("00:01", "12:01"))
        weekOpeningHours.addOpeningHours("Tuesday", OpeningHours("12:01", "24:01"))
        //Then
        Assertions.assertThat(weekOpeningHours.findOpeningHoursForDay("Monday").toString()).isEqualTo("Mon:12:01am-12:01pm")
        Assertions.assertThat(weekOpeningHours.findOpeningHoursForDay("Tuesday").toString()).isEqualTo("Tue:12:01pm-12:01am")
    }

    @Test
    fun `Should correctly identify Midnight and Midday`() {
        //Given
        val weekOpeningHours = WeekOpeningHours()
        //When
        weekOpeningHours.addOpeningHours("Monday", OpeningHours("00:00", "12:00"))
        weekOpeningHours.addOpeningHours("Tuesday", OpeningHours("12:00", "24:00"))
        //Then
        Assertions.assertThat(weekOpeningHours.findOpeningHoursForDay("Monday").toString()).isEqualTo("Mon:Midnight-Midday")
        Assertions.assertThat(weekOpeningHours.findOpeningHoursForDay("Tuesday").toString()).isEqualTo("Tue:Midday-Midnight")
    }

    @Test
    fun `Should correctly display multiple opening times for a day`() {
        //Given
        val weekOpeningHours = WeekOpeningHours()
        //When
        weekOpeningHours.addOpeningHours("Monday", OpeningHours("8:00", "11:00"))
        weekOpeningHours.addOpeningHours("Monday", OpeningHours("16:00", "23:30"))
        //Then
        Assertions.assertThat(weekOpeningHours.findOpeningHoursForDay("Monday").toString()).isEqualTo("Mon:8am-11am, 4pm-11:30pm")
    }
}