package com.davidbevan.challenge13

import java.util.*

data class Location(
    val name:String,
    val long:Float,
    val lat:Float
)

data class RouteItem(
    val name:String,
    val distanceFromPreviousLocation:Float
)

data class ItineraryItem(
    val name:String,
    val arrivalTime:Date,
    val departureTime:Date
)