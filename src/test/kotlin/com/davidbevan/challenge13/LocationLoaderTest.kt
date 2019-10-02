package com.davidbevan.challenge13

import com.davidbevan.challenge13.RoutePlanner
import io.kotlintest.specs.StringSpec
import org.assertj.core.api.Assertions

class LocationLoaderTest: StringSpec()	{
    init {

        "Test loading 3 locations" {
            val locations = LocationLoader.loadLocations(threeRawLocations)
            Assertions.assertThat(locations).hasSize(3)
            Assertions.assertThat(locations).containsExactlyElementsOf(threeExpectedLocations)
        }

    }
    val threeRawLocations = "AAA,AAA123,10,11,BBB,BBB123,20,21,CCC,CCC123,30,31"
    val threeExpectedLocations = listOf(
            Location("AAA", 10F,11F),
            Location("BBB",20F,21F),
            Location("CCC",30F,31F)
        )
}