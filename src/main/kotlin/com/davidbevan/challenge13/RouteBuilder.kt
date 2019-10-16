package com.davidbevan.challenge13

import kotlin.math.sqrt

object RouteBuilder {

    fun buildRouteFromLocations(locations: List<Location>): List<RouteItem> {
        var currentLocation = locations.first()
        var remainingLocations = locations.subList(1,locations.size)
        var route = mutableListOf<RouteItem>()
        var nextLocationAndDistance: Pair<Location, Float> = Pair(currentLocation, 0.toFloat())
        while(remainingLocations.isNotEmpty()) {
            nextLocationAndDistance = findNextNearestLocationAndDistance(currentLocation, remainingLocations)
            route.add(RouteItem(currentLocation.name, nextLocationAndDistance.second))
            remainingLocations = remainingLocations.filter { location -> location != nextLocationAndDistance.first }
            currentLocation = nextLocationAndDistance.first

        }
        route.add(RouteItem(nextLocationAndDistance.first.name, 0.toFloat()))
        //route.map {routeItem -> println("${routeItem.name}, ${routeItem.distanceToNextLocation}")}
        return route
    }

    fun findNextNearestLocationAndDistance(thisLocation: Location, remainingLocations: List<Location>): Pair<Location, Float> {
        var minDistance = 14000.toFloat() //furthest possible distance on globe
        var nextLocation = thisLocation
        remainingLocations.map { location ->
            var distance = DistanceCalculator.calculateDistanceBetweenLocations(thisLocation, location)
            if(distance < minDistance) {
                minDistance = distance
                nextLocation = location
            }
        }
        return Pair(nextLocation, minDistance)
    }


}