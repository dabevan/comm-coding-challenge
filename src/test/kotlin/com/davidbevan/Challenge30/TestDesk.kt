package com.davidbevan.Challenge30

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TestDesk {
    @Test
    fun `should accept desk booking when slot is available`() {
        //given
        val location = Location(listOf(Desk("12345")))
        val desk = location.getBookable("12345")
        val slot = Slot(LocalDateTime.of(2020, 1, 1, 12, 0, 0), 2)
        val attendee1 = Attendee("David Bevan", "david.bevan@johnlewis.co.uk", true)
        //when
        val result = desk!!.bookSlot(slot, listOf(attendee1))
        //then
        Assertions.assertThat(result.isRight)
        Assertions.assertThat(result.get()).isEqualTo(BookingConfirmation("Confirmed"))
    }

    @Test
    fun `should reject desk booking when slot not available due to start time match`() {
        //given
        val location = Location(listOf(Desk("12345")))
        val desk = location.getBookable("12345")
        val slotFirstMeeting = Slot(LocalDateTime.of(2020, 1, 1, 12, 0, 0), 2)
        val slotSecondMeeting = Slot(LocalDateTime.of(2020, 1, 1, 12, 0, 0), 2)
        val attendeeOtherMeetingBooking = Attendee("Fred Bloggs", "fred.bloggs@johnlewis.co.uk", true)
        val attendee1 = Attendee("David Bevan", "david.bevan@johnlewis.co.uk", true)
        desk!!.bookSlot(slotFirstMeeting, listOf(attendeeOtherMeetingBooking))
        //when
        val result = desk.bookSlot(slotSecondMeeting, listOf(attendee1))
        //then
        Assertions.assertThat(result.isLeft)
        Assertions.assertThat(result.left).isEqualTo(BookingError("Slot not available"))
    }

    @Test
    fun `should accept desk booking when slot not available due to start time match`() {
        //given
        val location = Location(listOf(Desk("12345")))
        val desk = location.getBookable("12345")
        val slotFirstMeeting = Slot(LocalDateTime.of(2020, 1, 1, 12, 0, 0), 2)
        val slotSecondMeeting = Slot(LocalDateTime.of(2020, 1, 1, 12, 0, 0), 2)
        val attendeeOtherMeetingBooking = Attendee("Fred Bloggs", "fred.bloggs@johnlewis.co.uk", true)
        val attendee1 = Attendee("David Bevan", "david.bevan@johnlewis.co.uk", true)
        desk!!.bookSlot(slotFirstMeeting, listOf(attendeeOtherMeetingBooking))
        //when
        val result = desk.bookSlot(slotSecondMeeting, listOf(attendee1))
        //then
        Assertions.assertThat(result.isLeft)
        Assertions.assertThat(result.left).isEqualTo(BookingError("Slot not available"))
    }

    @Test
    fun `should reject a desk booking for more than one person`() {
        //given
        val location = Location(listOf(Desk("12345")))
        val desk = location.getBookable("12345")
        val slot = Slot(LocalDateTime.of(2020, 1, 1, 12, 0, 0), 2)
        val attendee1 = Attendee("David Bevan", "david.bevan@johnlewis.co.uk", true)
        val attendee2 = Attendee("Fred Bloggs", "fred.bloggs@johnlewis.co.uk", true)
        //when
        val result = desk!!.bookSlot(slot, listOf(attendee1, attendee2))
        //then
        Assertions.assertThat(result.isRight)
        Assertions.assertThat(result.get()).isEqualTo(BookingConfirmation("Confirmed"))
    }

}



