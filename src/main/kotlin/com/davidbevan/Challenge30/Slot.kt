package com.davidbevan.Challenge30

import java.time.LocalDateTime

class Slot(val bookingStartDateTime: LocalDateTime, val duration: Int) {
    init {
        if (!listOf(8,10,12,14,16).contains(bookingStartDateTime.hour)) throw SlotException("${bookingStartDateTime.hour} is not a valid start time.")
    }
    fun isSlotOverlapping(slot: Slot): Boolean {
        if (bookingStartDateTime == slot.bookingStartDateTime) return true
        if (bookingStartDateTime.plusHours(duration.toLong()) > slot.bookingStartDateTime) return true
        if (bookingStartDateTime.minusHours(slot.duration.toLong()) < slot.bookingStartDateTime) return true
        return false
    }
}
