package com.davidbevan.challenge13

import kotlin.math.sqrt

class RoutePlanner {

    val MILES_PER_DEGREE = 69
    var startingPoint = Location("John Lewis Head Office", (-0.141499).toFloat(), (51.496466).toFloat())
    var locations:List<Location> = emptyList()
    var route:List<RouteItem> = emptyList()
    var itinary:List<ItineraryItem> = emptyList()

    fun planRoute(rawLocations: String): List<ItineraryItem> {
        locations = loadLocations(rawLocations)
        locations.map { location ->
            calculateDistanceBetweenLocations(startingPoint,location)
        }
        return emptyList()
    }

    fun calculateDistanceBetweenLocations(firstLocation:Location, secondLocation:Location): Float {
        val latDistance = firstLocation.lat - secondLocation.lat
        val longDistance = firstLocation.long - secondLocation.long
        var degrees = calcuateHypotenuseUsingPythagorasTheorem(latDistance, longDistance)
        println("${firstLocation.name} to ${secondLocation.name} is ${MILES_PER_DEGREE * degrees}")
        return MILES_PER_DEGREE * degrees
    }

    fun calcuateHypotenuseUsingPythagorasTheorem(x:Float, y:Float):Float {
        return sqrt(x*x + y*y)
    }

    fun loadLocations(rawLocations: String): List<Location> {
        var locations = mutableListOf<Location>()
        val locationElementArray = rawLocations.split(",")
        var name: String = ""
        var long: Float = 0.toFloat()
        var lat: Float
        locationElementArray.mapIndexed() { index, element ->
            var elementPointer = index%4
            if(elementPointer == 0) name = element
            if(elementPointer == 2) long = element.toFloat()
            if(elementPointer == 3) {
                lat = element.toFloat()
                locations.add(Location(name, long, lat))
            }
        }
        return locations
    }
}


