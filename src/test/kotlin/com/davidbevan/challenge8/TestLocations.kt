package com.davidbevan.challenge8

import io.kotlintest.matchers.*
import io.kotlintest.specs.StringSpec


class	TestLocations() :	StringSpec()	{
    init {

        "test Free Parking" {
            var freeParking = Location.FreeParking()
            freeParking.getMaxNum() shouldBe (1)
            freeParking.getRent() shouldBe (0)
            freeParking.getPassingPayment() shouldBe (0)
        }

        "test Go" {
            var go = Location.Go()
            go.getMaxNum() shouldBe (1)
            go.getRent() shouldBe (0)
            go.getPassingPayment() shouldBe (100)
        }

        "test Factory or Warehouse without owner" {
            var factoryOrWarehouse = Location.FactoryOrWarehouse("A factory name", 1000)
            factoryOrWarehouse.getMaxNum() shouldBe (4)
            factoryOrWarehouse.getRent() shouldBe (0)
            factoryOrWarehouse.getPassingPayment() shouldBe (0)
        }

        "test Factory or Warehouse with owner" {
            var factoryOrWarehouse = Location.FactoryOrWarehouse("A factory name", 1000)
            factoryOrWarehouse.purchase("Dave")
            factoryOrWarehouse.getMaxNum() shouldBe (4)
            factoryOrWarehouse.getRent() shouldBe (20)
            factoryOrWarehouse.getPassingPayment() shouldBe (0)
        }

        "test Retail Site without owner" {
            var retailSite = Location.RetailSite("A retail site name", 1000, 30, 100,150,200)
            retailSite.getMaxNum() shouldBe (20)
            retailSite.getRent() shouldBe (0)
            retailSite.getPassingPayment() shouldBe (0)
        }


        "test undeveloped Retail Site with owner" {
            var retailSite = Location.RetailSite("A retail site name", 1000, 30, 100,150,200)
            retailSite.purchase("Dave")
            retailSite.getMaxNum() shouldBe (20)
            retailSite.getRent() shouldBe (30)
            retailSite.getPassingPayment() shouldBe (0)
        }


        "test developed Retail Site with owner and a Mini Store" {
            var retailSite = Location.RetailSite("A retail site name", 1000, 30, 100,150,200)
            retailSite.purchase("Dave")
            retailSite.build(MiniStore(30))
            retailSite.getMaxNum() shouldBe (20)
            retailSite.getRent() shouldBe (60)
            retailSite.getPassingPayment() shouldBe (0)
        }

        "test developed Retail Site with owner and a Supermarket" {
            var retailSite = Location.RetailSite("A retail site name", 1000, 30, 100,150,200)
            retailSite.purchase("Dave")
            retailSite.build(Supermarket(40))
            retailSite.getMaxNum() shouldBe (20)
            retailSite.getRent() shouldBe (70)
            retailSite.getPassingPayment() shouldBe (0)
        }

        "test developed Retail Site with owner and a Mega Store" {
            var retailSite = Location.RetailSite("A retail site name", 1000, 30, 100,150,200)
            retailSite.purchase("Dave")
            retailSite.build(Supermarket(50))
            retailSite.getMaxNum() shouldBe (20)
            retailSite.getRent() shouldBe (80)
            retailSite.getPassingPayment() shouldBe (0)
        }
    }
}


