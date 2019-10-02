package com.davidbevan.challenge13

import com.davidbevan.challenge13.RoutePlanner
import io.kotlintest.specs.StringSpec
import org.assertj.core.api.Assertions

class RouteBuilderTest: StringSpec()	{
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
    val routeItem6to1 = RouteItem(location6.name, 487.903679019F)
    val routeItem6 = RouteItem(location6.name, 0F)
    val routeItem1 = RouteItem(location1.name, 0F)

    init {

        "Test simple route" {
            val locations = listOf(location1, location2, location3, location4, location5, location6)
            val expectedRoute =
                listOf(routeItem1to2, routeItem2to3, routeItem3to4, routeItem4to5, routeItem5to6, routeItem6)
            val route = RouteBuilder.buildRouteFromLocations(locations)
            Assertions.assertThat(route).containsExactlyElementsOf(expectedRoute)
        }

        "Test route where next nearest algorithm produces suboptimal result" {
            val locations = listOf(location3, location1, location4, location5, location6)
            val expectedRoute =
                listOf(routeItem3to4, routeItem4to5, routeItem5to6, routeItem6to1, routeItem1)
            val route = RouteBuilder.buildRouteFromLocations(locations)
            Assertions.assertThat(route).containsExactlyElementsOf(expectedRoute)
        }
    }
}
