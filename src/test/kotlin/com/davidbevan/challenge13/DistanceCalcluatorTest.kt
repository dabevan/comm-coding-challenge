package com.davidbevan.challenge13

import com.davidbevan.challenge13.RoutePlanner
import io.kotlintest.specs.StringSpec
import org.assertj.core.api.Assertions

class DistanceCalcluatorTest: StringSpec()	{
    val locationN = Location("North", 0F,45F)
    val locationS = Location("South", 0F,-45F)
    val locationE = Location("East", 45F,0F)
    val locationW = Location("West", -45F,0F)

    init {

        "Test disance between things on same latitude" {
            val distance = DistanceCalculator.calculateDistanceBetweenLocations(locationE,locationW)
            Assertions.assertThat(distance).isEqualTo(6210F)
        }

        "Test disance between things on same longitude" {
            val distance = DistanceCalculator.calculateDistanceBetweenLocations(locationN,locationS)
            Assertions.assertThat(distance).isEqualTo(6210F)
        }

        "Test disance between things on same different longitude and latitude" {
            val distance = DistanceCalculator.calculateDistanceBetweenLocations(locationN,locationW)
            Assertions.assertThat(distance).isEqualTo(4391.133111168F)
        }
    }

}