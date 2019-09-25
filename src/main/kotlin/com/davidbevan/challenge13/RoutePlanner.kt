package com.davidbevan.challenge13

import java.time.LocalDateTime
import kotlin.math.sqrt

class RoutePlanner {

    val MILES_PER_DEGREE = 69
    var startingPoint = Location("John Lewis Head Office", (-0.141499).toFloat(), (51.496466).toFloat())
    var unsortedListOfLocations = mutableListOf<Location>()
    var suggestedRoute:List<RouteItem> = mutableListOf()
    var suggestedItinary:List<ItineraryItem> = emptyList()

    var maxDistance = 0.toFloat()

    fun planRoute(rawStringOfLocations: String): List<ItineraryItem> {
        unsortedListOfLocations = loadLocations(rawStringOfLocations)
        unsortedListOfLocations.add(0,startingPoint)
        suggestedRoute = buildRouteFromLocations(unsortedListOfLocations)
        suggestedItinary = buildItinaryFromRoute(suggestedRoute)
        var totalDistance = 0.toFloat()
        suggestedRoute.map { routeItem ->
                println(routeItem)
                totalDistance += routeItem.distanceFromPreviousLocation
        }

        println("Total Distance = $totalDistance")
        return emptyList()
    }

    fun loadLocations(rawLocations: String): MutableList<Location> {
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

    fun buildRouteFromLocations(locations: List<Location>): List<RouteItem> {
        var currentLocation = locations.first()
        var remainingLocations = locations.subList(1,locations.size)
        var route = mutableListOf<RouteItem>()
        route.add(RouteItem(locations.first().name, 0.toFloat()))
        while(remainingLocations.isNotEmpty()) {
            var nextLocationAndDistance = findNextNearestLocationAndDistance(currentLocation, remainingLocations)
            remainingLocations = remainingLocations.filter { location -> location != nextLocationAndDistance.first }
            route.add(RouteItem(nextLocationAndDistance.first.name, nextLocationAndDistance.second))
            currentLocation = nextLocationAndDistance.first
        }
        return route
    }

    fun findNextNearestLocationAndDistance(thisLocation: Location, remainingLocations: List<Location>): Pair<Location, Float> {
        var minDistance = 14000.toFloat() //furthest possible distance on globe
        var nextLocation = remainingLocations.first()
        remainingLocations.map { location ->
            var distance = calculateDistanceBetweenLocations(startingPoint, location)
            if(distance < minDistance) {
                minDistance = distance
                nextLocation = location
            }
        }
        return Pair(nextLocation, minDistance)
    }

    fun calculateDistanceBetweenLocations(firstLocation:Location, secondLocation:Location): Float {
        val latDistance = firstLocation.lat - secondLocation.lat
        val longDistance = firstLocation.long - secondLocation.long
        var degrees = calcuateHypotenuseUsingPythagorasTheorem(latDistance, longDistance)
        //println("${firstLocation.name} to ${secondLocation.name} is ${MILES_PER_DEGREE * degrees}")
        if(MILES_PER_DEGREE * degrees > maxDistance) {
            //println("maxDistance is $maxDistance but ${secondLocation.name} is ${MILES_PER_DEGREE * degrees} away")
            maxDistance = MILES_PER_DEGREE * degrees
        }
        return MILES_PER_DEGREE * degrees
    }

    fun calcuateHypotenuseUsingPythagorasTheorem(x:Float, y:Float):Float {
        return sqrt(x*x + y*y)
    }

    fun buildItinaryFromRoute(route: List<RouteItem>) : List<ItineraryItem> {
        var startTime = LocalDateTime.of(2019,9,25,8,0,0)
        var itineraryTime = visitLocation(startTime)
        var itinerary = mutableListOf<ItineraryItem>()
        var previousRouteItem:RouteItem? = null
        var notFirstLocation:Boolean = false

        itinerary.add(ItineraryItem(route.first().name, startTime, itineraryTime))

        route.map{routeItem ->
            if (notFirstLocation) {
                if(canIGetToThisLocationInTime(routeItem, itineraryTime)) {
                    itineraryTime = itineraryTime.plusMinutes(calcTimeInMinutesToLocation(routeItem))
                } else {
                    itineraryTime = tommorrowsStartDateTime(itineraryTime)
                }
                itinerary.add(ItineraryItem(previousRouteItem!!.name,,itineraryTime))
            }
            previousRouteItem = routeItem
            notFirstLocation = true
        }
        return emptyList()
    }

    fun visitLocation(startTime: LocalDateTime):LocalDateTime {
        return startTime.plusMinutes(20)
    }

    fun tommorrowsStartDateTime(itineraryTime: LocalDateTime) : LocalDateTime {
        return LocalDateTime.of(itineraryTime.year,itineraryTime.month,itineraryTime.dayOfMonth + 1,8,0,0) //TODO Smarter logic for if arrive after midnight
    }

    fun canIGetToThisLocationInTime(routeItem: RouteItem, itineraryTime: LocalDateTime): Boolean {
        var arrivalTime = itineraryTime.plusMinutes(calcTimeInMinutesToLocation(routeItem))
        if (arrivalTime.hour > 18 || arrivalTime.hour < 8) return false //TODO smarter logic for really long journeys
        return true
    }

    fun calcTimeInMinutesToLocation(routeItem: RouteItem): Long {
        return (routeItem.distanceFromPreviousLocation.toLong() / 30) * 60
    }
}


