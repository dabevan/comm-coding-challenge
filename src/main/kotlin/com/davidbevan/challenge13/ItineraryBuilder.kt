package com.davidbevan.challenge13

import com.sun.org.apache.xpath.internal.operations.Bool
import java.time.LocalDateTime
import java.util.*

object ItineraryBuilder {

    val SPEED = 30F

    fun buildItinaryFromRoute(route: List<RouteItem>, startTime: LocalDateTime) : List<ItineraryItem> {
        var itinerary = mutableListOf<ItineraryItem>()
        var currentTime = startTime
        route.map { routeItem ->
            var arriveTime = currentTime
            currentTime = spendTimeAtLocation(currentTime)
            var departTime: LocalDateTime
            val arrivalTimeAtNextDestination = calculateArrivalTimeAtNextDestination(currentTime, routeItem.distanceToNextLocation)
            if (isArrivalTimeWithinTimeLimits(currentTime, arrivalTimeAtNextDestination)) {
                departTime = currentTime
            } else {
                departTime = currentTime.plusDays(1).withHour(8).withMinute(0)
            }
            itinerary.add(ItineraryItem(routeItem.name, arriveTime, departTime))
            currentTime = calculateArrivalTimeAtNextDestination(departTime, routeItem.distanceToNextLocation)
        }
        return itinerary
    }

    fun spendTimeAtLocation(timeArrive:LocalDateTime): LocalDateTime {
        return timeArrive.plusMinutes(20)
    }

    fun isArrivalTimeWithinTimeLimits(departureTime: LocalDateTime, arrivalTime: LocalDateTime): Boolean {
        val departureDay = departureTime.dayOfYear
        val arrivalDay = arrivalTime.dayOfYear
        val arrivalHour = arrivalTime.hour
        return (departureDay == arrivalDay && arrivalHour < 18)
    }

    fun calculateArrivalTimeAtNextDestination(departureTime: LocalDateTime, distance: Float): LocalDateTime {
        var timeInHours = distance/SPEED
        return departureTime.plusMinutes((timeInHours * 60).toLong())
    }


}