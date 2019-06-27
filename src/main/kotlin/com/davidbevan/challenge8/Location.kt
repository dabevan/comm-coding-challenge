package com.davidbevan.challenge8

abstract class Location {
    var name = ""
    open var collect = 0
    var cost = 0
    open var rent = 0
    abstract var maxNumber: Int

}


class FreeParking: Location() {
    override var maxNumber = 1
}


class Go: Location() {
    override var maxNumber = 1
    override var collect = 100
}


class Factory: Location() {
    override var maxNumber = 4
    override var collect = 100
    override var rent = 20

}