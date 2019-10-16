package com.davidbevan.challenge11

import io.kotlintest.specs.StringSpec
import org.assertj.core.api.Assertions

class TestBeers : StringSpec()	{
        init {

            "Test that 13 beers are returned from the simple sample data" {
                var beers = BeerFinder().obtainListOfBeers(PubsTestData().simplePubsData)
                Assertions.assertThat(beers).hasSize(13)
            }

            "Test that the beers returned from the simple sample data are in alphabetical order" {
                var beers = BeerFinder().obtainListOfBeers(PubsTestData().simplePubsData)
                Assertions.assertThat(beers).containsExactlyElementsOf(beers.sortedBy { it.name })
            }

            "Test that the correct 13 beers are returned from the simple sample data" {
                var beers = BeerFinder().obtainListOfBeers(PubsTestData().simplePubsData)
                Assertions.assertThat(beers).containsExactlyElementsOf(PubsTestData().simplePubDataResult)
            }

            "Test that the complex sample data returns 14 beers" {
                var beers = BeerFinder().obtainListOfBeers(PubsTestData().longPubsData)
                Assertions.assertThat(beers).hasSize(24)
            }


        }
}