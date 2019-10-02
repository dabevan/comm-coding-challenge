package com.davidbevan.challenge13

import java.time.LocalDateTime

object ItineraryBuilder {

      fun buildItinaryFromRoute(route: List<RouteItem>) : List<ItineraryItem> {
          return emptyList()
      }
//    fun buildItinaryFromRoute(route: List<RouteItem>) : List<ItineraryItem> {
//        var startTime = LocalDateTime.of(2019,9,25,8,0,0)
//        var itineraryTime = visitLocation(startTime)
//        var itinerary = mutableListOf<ItineraryItem>()
//
//        itinerary.add(ItineraryItem(route.first().name, startTime, itineraryTime))
//
//        route.map{routeItem ->
//            if (notFirstLocation) {
//                if(canIGetToThisLocationInTime(routeItem, itineraryTime)) {
//                    itineraryTime = itineraryTime.plusMinutes(calcTimeInMinutesToLocation(routeItem))
//                } else {
//                    itineraryTime = tommorrowsStartDateTime(itineraryTime)
//                }
//                itinerary.add(ItineraryItem(previousRouteItem!!.name,,itineraryTime))
//            }
//            previousRouteItem = routeItem
//            notFirstLocation = true
//        }
//        return emptyList()
//    }

    fun visitLocation(startTime: LocalDateTime): LocalDateTime {
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
        return (routeItem.distanceToNextLocation.toLong() / 30) * 60
    }
}