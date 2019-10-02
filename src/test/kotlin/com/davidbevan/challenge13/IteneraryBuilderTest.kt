package com.davidbevan.challenge13

import io.kotlintest.specs.StringSpec
import org.assertj.core.api.Assertions
import java.time.LocalDateTime

class IteneraryBuilderTest: StringSpec()	{

    var startTime = LocalDateTime.of(2019,9,25,8,0,0)

    val location1 = Location("One", 1F,1F)
    val location2 = Location("Two", 2F,2F)
    val location3 = Location("Three", 3F,3F)
    val location4 = Location("Four", 4F,4F)
    val location5 = Location("Five", 5F,5F)
    val location6 = Location("Six", 6F,6F)

    val routeItem1to2 = RouteItem(location1.name, 97.580735804F)
    val routeItem2to3 = RouteItem(location2.name, 97.580735804F)
    val routeItem3to4 = RouteItem(location3.name, 97.580735804F)
    val routeItem4to5 = RouteItem(location4.name, 97.580735804F)
    val routeItem5to6 = RouteItem(location5.name, 97.580735804F)
    val routeItem6 = RouteItem(location6.name, 0F)


    init {

        "Test single day itinerary" {
            val route = listOf(routeItem4to5, routeItem5to6, routeItem6)
            val expectedItinerary = listOf(
                ItineraryItem(location4.name, startTime, startTime.plusMinutes(20)),
                ItineraryItem(location5.name, startTime.plusMinutes(215), startTime.plusMinutes(235)),
                ItineraryItem(location6.name, startTime.plusMinutes(430), startTime.plusMinutes(450))
            )

            val itinerary = ItineraryBuilder.buildItinaryFromRoute(route, startTime)
            Assertions.assertThat(itinerary).containsExactlyElementsOf(expectedItinerary)
        }

    "Test multi day itinerary" {
            val route = listOf(routeItem1to2,routeItem2to3,routeItem3to4,routeItem4to5, routeItem5to6, routeItem6)
            val expectedItinerary = listOf(
                ItineraryItem(location1.name, startTime, startTime.plusMinutes(20)),
                ItineraryItem(location2.name, startTime.plusMinutes(215), startTime.plusMinutes(235)),
                ItineraryItem(location3.name, startTime.plusMinutes(430), startTime.plusDays(1)),
                ItineraryItem(location4.name, startTime.plusDays(1).plusMinutes(195), startTime.plusDays(1).plusMinutes(215)),
                ItineraryItem(location5.name, startTime.plusDays(1).plusMinutes(410), startTime.plusDays(2)),
                ItineraryItem(location6.name, startTime.plusDays(2).plusMinutes(195), startTime.plusDays(2).plusMinutes(215))
            )

            val itinerary = ItineraryBuilder.buildItinaryFromRoute(route, startTime)
            Assertions.assertThat(itinerary).containsExactlyElementsOf(expectedItinerary)
        }

    }
}
