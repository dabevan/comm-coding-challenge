package com.davidbevan.challenge13

import java.time.LocalDateTime
import kotlin.math.sqrt

class RoutePlanner {

    var startingPoint = Location("John Lewis Head Office", (-0.141499).toFloat(), (51.496466).toFloat())

    var unsortedListOfLocations = mutableListOf<Location>()
    var suggestedRoute:List<RouteItem> = mutableListOf()
    var suggestedItinary:List<ItineraryItem> = emptyList()

    fun planRoute(rawStringOfLocations: String): List<ItineraryItem> {
        unsortedListOfLocations = LocationLoader.loadLocations(rawStringOfLocations)
        unsortedListOfLocations.add(0,startingPoint)
        suggestedRoute = RouteBuilder.buildRouteFromLocations(unsortedListOfLocations)
        suggestedItinary = ItineraryBuilder.buildItinaryFromRoute(suggestedRoute)

        return emptyList()
    }






}


