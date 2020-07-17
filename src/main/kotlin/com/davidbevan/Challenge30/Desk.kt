package com.davidbevan.Challenge30

import io.vavr.control.Either

class Desk(override val identifier: String): Bookable(identifier) {


    override fun bookSlot(slot: Slot, attendees: List<Attendee>): Either<BookingError, BookingConfirmation> {
        if(attendees.size != 1) return Either.left(BookingError("Desks can only be booked by one person."))
        if(slot.duration != 2) return Either.left(BookingError("Desks can only be booked in 2 hour slots."))
        return super.bookSlot(slot, attendees)
    }
}