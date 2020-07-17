package com.davidbevan.Challenge30

import io.vavr.control.Either

class LargeMeetingSpace(override val identifier: String, val maxCapacity: Int, facilities: Map<String, String> = emptyMap()): Bookable(identifier) {

    override fun bookSlot(slot: Slot, attendees: List<Attendee>): Either<BookingError, BookingConfirmation> {
        if(attendees.size > maxCapacity) return Either.left(BookingError("This large meeting space has a maximum capacity of $maxCapacity people."))
        if(slot.duration != 4) return Either.left(BookingError("Large meeting spaces can only be booked in 4 hour slots."))
        return super.bookSlot(slot, attendees)
    }
}