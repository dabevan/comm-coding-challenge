package com.davidbevan.Challenge30

class Location(val bookables: List<Bookable>) {

    fun getBookable(identifier: String) = bookables.firstOrNull { desk -> desk.identifier == identifier }

}