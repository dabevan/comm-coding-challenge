package com.davidbevan.Challenge30

import io.vavr.control.Either

data class BookingError(val message: String)

data class BookingConfirmation(val message: String)

data class Booking(val slot: Slot, val attendees: List<Attendee>)

open class Bookable(open val identifier: String) {

    var bookings: List<Booking> = emptyList()

    open fun bookSlot(slot: Slot, attendees: List<Attendee>): Either<BookingError, BookingConfirmation> {
        return if (bookings.firstOrNull { it.slot.isSlotOverlapping(slot)} == null) {
            bookings = bookings.plus(Booking(slot, attendees))
            Either.right(BookingConfirmation("Confirmed"))
        } else {
            Either.left(BookingError("Slot not available"))
        }
    }

}

class SlotException(message: String): Exception(message)