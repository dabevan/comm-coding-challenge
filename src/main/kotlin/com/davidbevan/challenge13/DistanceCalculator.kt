package com.davidbevan.challenge13

import kotlin.math.sqrt

object DistanceCalculator {
    val MILES_PER_DEGREE = 69

    fun calculateDistanceBetweenLocations(firstLocation:Location, secondLocation:Location): Float {
        val latDistance = firstLocation.lat - secondLocation.lat
        val longDistance = firstLocation.long - secondLocation.long
        var degrees = calcuateHypotenuseUsingPythagorasTheorem(latDistance, longDistance)
        //println("${firstLocation.name} to ${secondLocation.name} is ${MILES_PER_DEGREE * degrees}")
        return MILES_PER_DEGREE * degrees
    }

    fun calcuateHypotenuseUsingPythagorasTheorem(x:Float, y:Float):Float {
        return sqrt(x*x + y*y)
    }
}