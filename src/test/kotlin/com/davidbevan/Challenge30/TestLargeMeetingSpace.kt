package com.davidbevan.Challenge30

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TestLargeMeetingSpace {
    @Test
    fun `should accept large meeting space booking when slot is available`() {
        //given
        val location = Location(listOf(LargeMeetingSpace("12345",4)))
        val largeMeetingSpace = location.getBookable("12345")
        val slot = Slot(LocalDateTime.of(2020, 1, 1, 12, 0, 0), 4)
        val attendee1 = Attendee("David Bevan", "david.bevan@johnlewis.co.uk", true)
        //when
        val result = largeMeetingSpace!!.bookSlot(slot, listOf(attendee1))
        //then
        Assertions.assertThat(result.isRight)
        Assertions.assertThat(result.get()).isEqualTo(BookingConfirmation("Confirmed"))

    }

    @Test
    fun `should reject large meeting space booking when slot not available due to start time match`() {
        //given
        val location = Location(listOf(LargeMeetingSpace("12345",4)))
        val largeMeetingSpace = location.getBookable("12345")
        val slotFirstMeeting = Slot(LocalDateTime.of(2020, 1, 1, 12, 0, 0), 4)
        val slotSecondMeeting = Slot(LocalDateTime.of(2020, 1, 1, 12, 0, 0), 4)
        val attendeeOtherMeetingBooking = Attendee("Fred Bloggs", "fred.bloggs@johnlewis.co.uk", true)
        val attendee1 = Attendee("David Bevan", "david.bevan@johnlewis.co.uk", true)
        largeMeetingSpace!!.bookSlot(slotFirstMeeting, listOf(attendeeOtherMeetingBooking))
        //when
        val result = largeMeetingSpace.bookSlot(slotSecondMeeting, listOf(attendee1))
        //then
        Assertions.assertThat(result.isLeft)
        Assertions.assertThat(result.left).isEqualTo(BookingError("Slot not available"))

    }

   @Test
    fun `should reject large meeting space booking when slot not available when start time is earlier but duration overlaps`() {
       //given
        val location = Location(listOf(LargeMeetingSpace("12345",4)))
        val largeMeetingSpace = location.getBookable("12345")
        val slotFirstMeeting = Slot(LocalDateTime.of(2020, 1, 1, 12, 0, 0), 4)
        val slotSecondMeeting = Slot(LocalDateTime.of(2020, 1, 1, 10, 0, 0), 4)
        val attendeeOtherMeetingBooking = Attendee("Fred Bloggs", "fred.bloggs@johnlewis.co.uk", true)
        val attendee1 = Attendee("David Bevan", "david.bevan@johnlewis.co.uk", true)
        largeMeetingSpace!!.bookSlot(slotFirstMeeting, listOf(attendeeOtherMeetingBooking))
        //when
        val result = largeMeetingSpace.bookSlot(slotSecondMeeting, listOf(attendee1))
        //then
        Assertions.assertThat(result.isLeft)
        Assertions.assertThat(result.left).isEqualTo(BookingError("Slot not available"))
    }

   @Test
    fun `should reject large meeting space booking when slot not available when start time is later but before other booking has finished`() {
      //given
        val location = Location(listOf(LargeMeetingSpace("12345",4)))
        val largeMeetingSpace = location.getBookable("12345")
        val slotFirstMeeting = Slot(LocalDateTime.of(2020, 1, 1, 12, 0, 0), 4)
        val slotSecondMeeting = Slot(LocalDateTime.of(2020, 1, 1, 14, 0, 0), 4)
        val attendeeOtherMeetingBooking = Attendee("Fred Bloggs", "fred.bloggs@johnlewis.co.uk", true)
        val attendee1 = Attendee("David Bevan", "david.bevan@johnlewis.co.uk", true)
        largeMeetingSpace!!.bookSlot(slotFirstMeeting, listOf(attendeeOtherMeetingBooking))
        //when
        val result = largeMeetingSpace.bookSlot(slotSecondMeeting, listOf(attendee1))
        //then
        Assertions.assertThat(result.isLeft)
        Assertions.assertThat(result.left).isEqualTo(BookingError("Slot not available"))
    }

    @Test
    fun `should accept large meeting space booking when slot is available when start time is four hours after other booking started`() {
      //given
        val location = Location(listOf(LargeMeetingSpace("12345",4)))
        val largeMeetingSpace = location.getBookable("12345")
        val slotFirstMeeting = Slot(LocalDateTime.of(2020, 1, 1, 12, 0, 0), 4)
        val slotSecondMeeting = Slot(LocalDateTime.of(2020, 1, 1, 16, 0, 0), 4)
        val attendeeOtherMeetingBooking = Attendee("Fred Bloggs", "fred.bloggs@johnlewis.co.uk", true)
        val attendee1 = Attendee("David Bevan", "david.bevan@johnlewis.co.uk", true)
        largeMeetingSpace!!.bookSlot(slotFirstMeeting, listOf(attendeeOtherMeetingBooking))
        //when
        val result = largeMeetingSpace.bookSlot(slotSecondMeeting, listOf(attendee1))
        //then
        Assertions.assertThat(result.isLeft)
        Assertions.assertThat(result.left).isEqualTo(BookingError("Slot not available"))
    }

    @Test
    fun `should accept large meeting space booking when slot is available when start time is four hours before other booking started`() {
      //given
        val location = Location(listOf(LargeMeetingSpace("12345",4)))
        val largeMeetingSpace = location.getBookable("12345")
        val slotFirstMeeting = Slot(LocalDateTime.of(2020, 1, 1, 12, 0, 0), 4)
        val slotSecondMeeting = Slot(LocalDateTime.of(2020, 1, 1, 8, 0, 0), 4)
        val attendeeOtherMeetingBooking = Attendee("Fred Bloggs", "fred.bloggs@johnlewis.co.uk", true)
        val attendee1 = Attendee("David Bevan", "david.bevan@johnlewis.co.uk", true)
        largeMeetingSpace!!.bookSlot(slotFirstMeeting, listOf(attendeeOtherMeetingBooking))
        //when
        val result = largeMeetingSpace.bookSlot(slotSecondMeeting, listOf(attendee1))
        //then
        Assertions.assertThat(result.isLeft)
        Assertions.assertThat(result.left).isEqualTo(BookingError("Slot not available"))
    }

    @Test
    fun `should reject large meeting space booking when more than max capacity is specified`() {
        //given
        val location = Location(listOf(LargeMeetingSpace("12345",4)))
        val largeMeetingSpace = location.getBookable("12345")
        val slot = Slot(LocalDateTime.of(2020, 1, 1, 12, 0, 0), 4)
        val attendee1 = Attendee("David Bevan1", "david.bevan1@johnlewis.co.uk", true)
        val attendee2 = Attendee("David Bevan2", "david.bevan2@johnlewis.co.uk", true)
        val attendee3 = Attendee("David Bevan3", "david.bevan3@johnlewis.co.uk", true)
        val attendee4 = Attendee("David Bevan4", "david.bevan4@johnlewis.co.uk", true)
        val attendee5 = Attendee("David Bevan5", "david.bevan5@johnlewis.co.uk", true)
        //when
        val result = largeMeetingSpace!!.bookSlot(slot, listOf(attendee1, attendee2, attendee3, attendee4, attendee5))
        //then
        Assertions.assertThat(result.isLeft)
        Assertions.assertThat(result.left).isEqualTo(BookingError("This large meeting space has a maximum capacity of 4 people."))

    }


}