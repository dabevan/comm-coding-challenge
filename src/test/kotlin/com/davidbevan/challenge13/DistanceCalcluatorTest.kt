package com.davidbevan.challenge13

import com.davidbevan.challenge13.RoutePlanner
import io.kotlintest.specs.StringSpec
import org.assertj.core.api.Assertions

class DistanceCalcluatorTest: StringSpec()	{
    val locationN = Location("North", 0F,45F)
    val locationS = Location("South", 0F,-45F)
    val locationE = Location("East", 45F,0F)
    val locationW = Location("West", -45F,0F)

    val helensburghPetrolStation = Location("Helensburgh Petrol Station", -4.704267F,55.994132F)
    val menaiBridge = Location("Menai Bridge",-4.166358F,53.223915F)
    val johnLewisAberdeen = Location("John Lewis Aberdeen", -2.100636F,57.150079F)

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

        "Test disance between some locations that were showing odd results" {
            val distanceBetweenHelensburghPetrolStationAndMenaiBridge = DistanceCalculator.calculateDistanceBetweenLocations(helensburghPetrolStation,menaiBridge)
            val distanceBetweenHelensburghPetrolStationAndJohnLewisAberdeen = DistanceCalculator.calculateDistanceBetweenLocations(helensburghPetrolStation,johnLewisAberdeen)
            println("Distance b etween Helensburgh Petrol Station and Menai Bridge is $distanceBetweenHelensburghPetrolStationAndMenaiBridge")
            println("Distance between Helensburgh Petrol Station and JohnLewis Aberdeen is $distanceBetweenHelensburghPetrolStationAndJohnLewisAberdeen")
            Assertions.assertThat(distanceBetweenHelensburghPetrolStationAndMenaiBridge).isLessThan(distanceBetweenHelensburghPetrolStationAndJohnLewisAberdeen)
        }
    }

}