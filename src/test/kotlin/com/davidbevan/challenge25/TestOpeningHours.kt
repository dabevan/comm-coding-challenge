package com.davidbevan.challenge25

import complexOpeningHoursSpecification
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import simpleOpeningHoursSpecification

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

    @Test
    fun `Should correctly display when closed`() {
        //Given
        val weekOpeningHours = WeekOpeningHours()
        //When
        weekOpeningHours.addOpeningHours("Monday", OpeningHours("00:00", "00:00"))
        //Then
        Assertions.assertThat(weekOpeningHours.findOpeningHoursForDay("Monday").toString()).isEqualTo("Mon:CLOSED")
    }

    @Test
    fun `Should correctly format when all days same opening hours`() {
        //Given
        val weekOpeningHours = WeekOpeningHours()
        //When
        weekOpeningHours.addOpeningHours("Monday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Tuesday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Wednesday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Thursday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Friday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Saturday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Sunday", OpeningHours("10:00", "23:00"))
        //Then
        Assertions.assertThat(formatOpeningHours(weekOpeningHours.daysOpeningHours)).isEqualTo("Mon-Sun:10am-11pm")
    }

    @Test
    fun `Should correctly format when last day is different opening hours`() {
        //Given
        val weekOpeningHours = WeekOpeningHours()
        //When
        weekOpeningHours.addOpeningHours("Monday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Tuesday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Wednesday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Thursday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Friday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Saturday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Sunday", OpeningHours("10:00", "15:00"))
        //Then
        Assertions.assertThat(formatOpeningHours(weekOpeningHours.daysOpeningHours)).isEqualTo("Mon-Sat:10am-11pm| Sun:10am-3pm")
    }

    @Test
    fun `Should correctly format when first day is different opening hours`() {
        //Given
        val weekOpeningHours = WeekOpeningHours()
        //When
        weekOpeningHours.addOpeningHours("Monday", OpeningHours("10:00", "16:00"))
        weekOpeningHours.addOpeningHours("Tuesday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Wednesday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Thursday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Friday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Saturday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Sunday", OpeningHours("10:00", "23:00"))
        //Then
        Assertions.assertThat(formatOpeningHours(weekOpeningHours.daysOpeningHours)).isEqualTo("Mon:10am-4pm| Tue-Sun:10am-11pm")
    }

    @Test
    fun `Should correctly format when Wednesday day is different opening hours`() {
        //Given
        val weekOpeningHours = WeekOpeningHours()
        //When
        weekOpeningHours.addOpeningHours("Monday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Tuesday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Wednesday", OpeningHours("10:00", "17:00"))
        weekOpeningHours.addOpeningHours("Thursday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Friday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Saturday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Sunday", OpeningHours("10:00", "23:00"))
        //Then
        Assertions.assertThat(formatOpeningHours(weekOpeningHours.daysOpeningHours)).isEqualTo("Mon-Tue:10am-11pm| Wed:10am-5pm| Thu-Sun:10am-11pm")
    }

    @Test
    fun `Should correctly format when middle two days are different opening hours from others`() {
        //Given
        val weekOpeningHours = WeekOpeningHours()
        //When
        weekOpeningHours.addOpeningHours("Monday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Tuesday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Wednesday", OpeningHours("10:00", "17:00"))
        weekOpeningHours.addOpeningHours("Thursday", OpeningHours("10:00", "17:00"))
        weekOpeningHours.addOpeningHours("Friday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Saturday", OpeningHours("10:00", "23:00"))
        weekOpeningHours.addOpeningHours("Sunday", OpeningHours("10:00", "23:00"))
        //Then
        Assertions.assertThat(formatOpeningHours(weekOpeningHours.daysOpeningHours)).isEqualTo("Mon-Tue:10am-11pm| Wed-Thu:10am-5pm| Fri-Sun:10am-11pm")
    }

    @Test
    fun `Should correctly format when all days are different opening hours`() {
        //Given
        val weekOpeningHours = WeekOpeningHours()
        //When
        weekOpeningHours.addOpeningHours("Monday", OpeningHours("10:00", "13:00"))
        weekOpeningHours.addOpeningHours("Tuesday", OpeningHours("10:00", "14:00"))
        weekOpeningHours.addOpeningHours("Wednesday", OpeningHours("10:00", "15:00"))
        weekOpeningHours.addOpeningHours("Thursday", OpeningHours("10:00", "16:00"))
        weekOpeningHours.addOpeningHours("Friday", OpeningHours("10:00", "17:00"))
        weekOpeningHours.addOpeningHours("Saturday", OpeningHours("10:00", "18:00"))
        weekOpeningHours.addOpeningHours("Sunday", OpeningHours("10:00", "19:00"))
        //Then
        Assertions.assertThat(formatOpeningHours(weekOpeningHours.daysOpeningHours)).isEqualTo("Mon:10am-1pm| Tue:10am-2pm| Wed:10am-3pm| Thu:10am-4pm| Fri:10am-5pm| Sat:10am-6pm| Sun:10am-7pm")
    }

//   @Test
//    fun `Should show correctly formatted opening hours from simple JSON example`() {
//        //Given
//        //When
//        val inputJSON = simpleOpeningHoursSpecification
//        //Then
//        Assertions.assertThat(displayWeeksOpeningHours(simpleOpeningHoursSpecification)).isEqualTo("Mon-Fri:Midday-10pm| Sat-Sun:Midday-11pm")
//    }

   @Test
    fun `Should show correctly formatted opening hours from complex JSON example`() {
        //Given
        //When
        val inputJSON = complexOpeningHoursSpecification
        //Then
        Assertions.assertThat(displayWeeksOpeningHours(complexOpeningHoursSpecification)).isEqualTo("Mon:CLOSED| Tue:5pm-10pm| Wed-Thu:Midday-2pm, 5pm-10pm| Fri-Sat:Midday-10:30pm| Sun:Midday-5pm")
    }



}