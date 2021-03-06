package com.davidbevan.challenge15

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class CalculateRebateForSupplierTest {

    //val discountsApplied = "Tomato Soup,E10001,£126.19,Tomato Soup,E10002,£123.80,Tomato Soup,E10003,£37.74,Chicken Soup,E10004,£127.70,Chicken Soup,E10005,£153.28,Chicken Soup,E10006,£29.42,Vegatable Soup,E10007,£16.10,Vegatable Soup,E10008,£159.26,Vegatable Soup,E10009,£92.84,Vegatable Soup,E10010,£167.32,Carrot Soup,E10011,£36.00,Carrot Soup,E10012,£199.41,Carrot Soup,E10013,£195.84,Turnip Soup,E10014,£46.02,MISSING,E10016,£94.25,Oxtail Soup,E10017,£77.30,Oxtail Soup,E10018,£71.77,Minestrone Soup,E10019,£102.10,Minestrone Soup,E10020,£84.88,Minestrone Soup,E10021,£103.75,Minestrone Soup,E10022,£20.85,Spring Onion Soup,E10023,£162.71,Spring Onion Soup,E10024,£74.90,Spring Onion Soup,E10025,£167.73"
    //val discountsApplied = "Soup 1,E1,£100"
    //val deliveriesToShop = "Carrot Soup,E10011,I118,72,Depot-A,7,Carrot Soup,E10011,I118,72,Depot-B,32,Carrot Soup,E10011,I118,72,Depot-C,9,Carrot Soup,E10011,I119,96,Depot-A,10,Carrot Soup,E10011,I119,96,Depot-C,20,Carrot Soup,E10012,I120,48,Depot-B,8,Carrot Soup,E10012,I120,48,Depot-C,8,Carrot Soup,E10013,I121,48,Depot-A,24,Carrot Soup,E10013,I122,96,Depot-A,25,Carrot Soup,E10013,I122,96,Depot-B,11,Carrot Soup,E10013,I122,96,Depot-C,13,Chicken Soup,E10004,I105,96,Depot-A,10,Chicken Soup,E10004,I105,96,Depot-B,6,Chicken Soup,E10004,I105,96,Depot-C,5,Chicken Soup,E10005,I106,96,Depot-A,14,Chicken Soup,E10005,I106,96,Depot-B,7,Chicken Soup,E10005,I106,96,Depot-C,2,Chicken Soup,E10005,I107,96,Depot-B,2,Chicken Soup,E10005,I107,96,Depot-C,11,Chicken Soup,E10006,I108,48,Depot-C,5,Chicken Soup,E10006,I109,48,Depot-A,14,Chicken Soup,E10006,I109,48,Depot-B,6,Chicken Soup,E10006,I109,48,Depot-C,9,Minestrone Soup,E10019,I132,24,Depot-A,2,Minestrone Soup,E10019,I132,24,Depot-B,4,Minestrone Soup,E10019,I132,24,Depot-C,10,Minestrone Soup,E10019,I133,24,Depot-B,9,Minestrone Soup,E10019,I133,24,Depot-C,14,Minestrone Soup,E10019,I134,48,Depot-A,10,Minestrone Soup,E10019,I134,48,Depot-C,19,Minestrone Soup,E10020,I135,96,Depot-A,17,Minestrone Soup,E10020,I135,96,Depot-B,13,Minestrone Soup,E10020,I135,96,Depot-C,21,Minestrone Soup,E10020,I136,96,Depot-A,9,Minestrone Soup,E10020,I136,96,Depot-B,8,Minestrone Soup,E10020,I136,96,Depot-C,13,Minestrone Soup,E10021,I137,96,Depot-A,14,Minestrone Soup,E10021,I137,96,Depot-B,6,Minestrone Soup,E10021,I137,96,Depot-C,13,Minestrone Soup,E10022,I138,48,Depot-A,13,Minestrone Soup,E10022,I138,48,Depot-B,6,Oxtail Soup,E10016,I126,24,Depot-A,16,Oxtail Soup,E10016,I126,24,Depot-B,8,Oxtail Soup,E10016,I126,24,Depot-C,12,Oxtail Soup,E10016,I127,24,Depot-A,5,Oxtail Soup,E10016,I127,24,Depot-B,26,Oxtail Soup,E10016,I127,24,Depot-C,9,Oxtail Soup,E10017,I128,96,Depot-A,5,Oxtail Soup,E10017,I128,96,Depot-B,10,Oxtail Soup,E10017,I128,96,Depot-C,17,Oxtail Soup,E10017,I129,96,Depot-B,14,Oxtail Soup,E10017,I129,96,Depot-C,7,Oxtail Soup,E10018,I130,72,Depot-A,13,Oxtail Soup,E10018,I130,72,Depot-B,15,Oxtail Soup,E10018,I130,72,Depot-C,32,Oxtail Soup,E10018,I131,24,Depot-A,10,Oxtail Soup,E10018,I131,24,Depot-C,22,Spring Onion Soup,E10023,I139,72,Depot-A,10,Spring Onion Soup,E10023,I139,72,Depot-B,2,Spring Onion Soup,E10023,I139,72,Depot-C,16,Spring Onion Soup,E10023,I140,24,Depot-A,19,Spring Onion Soup,E10023,I140,24,Depot-B,13,Spring Onion Soup,E10023,I140,24,Depot-C,12,Spring Onion Soup,E10024,I141,96,Depot-A,3,Spring Onion Soup,E10024,I142,24,Depot-A,11,Spring Onion Soup,E10024,I142,24,Depot-C,7,Spring Onion Soup,E10025,I143,48,Depot-A,6,Spring Onion Soup,E10025,I143,48,Depot-C,12,Tomato Soup,E10001,I101,72,Depot-C,2,Tomato Soup,E10002,I102,48,Depot-A,4,Tomato Soup,E10002,I102,48,Depot-B,13,Tomato Soup,E10002,I102,48,Depot-C,14,Tomato Soup,E10003,I103,48,Depot-A,3,Tomato Soup,E10003,I103,48,Depot-B,19,Tomato Soup,E10003,I103,48,Depot-C,18,Tomato Soup,E10003,I104,48,Depot-A,2,Tomato Soup,E10003,I104,48,Depot-B,21,Tomato Soup,E10003,I104,48,Depot-C,26,Turnip Soup,E10014,I123,48,Depot-A,14,Turnip Soup,E10014,I123,48,Depot-B,7,Turnip Soup,E10014,I123,48,Depot-C,10,Turnip Soup,E10014,I124,96,Depot-A,28,Turnip Soup,E10014,I124,96,Depot-B,6,Turnip Soup,E10014,I124,96,Depot-C,5,Vegatable Soup,E10007,I110,24,Depot-A,19,Vegatable Soup,E10007,I110,24,Depot-B,11,Vegatable Soup,E10007,I110,24,Depot-C,9,Vegatable Soup,E10007,I111,72,Depot-B,16,Vegatable Soup,E10007,I111,72,Depot-C,23,Vegatable Soup,E10008,I112,96,Depot-A,4,Vegatable Soup,E10008,I112,96,Depot-B,9,Vegatable Soup,E10008,I112,96,Depot-C,27,Vegatable Soup,E10008,I113,72,Depot-A,10,Vegatable Soup,E10008,I113,72,Depot-B,18,Vegatable Soup,E10009,I114,48,Depot-A,24,Vegatable Soup,E10009,I114,48,Depot-B,18,Vegatable Soup,E10009,I114,48,Depot-C,11,Vegatable Soup,E10009,I115,48,Depot-A,11,Vegatable Soup,E10009,I115,48,Depot-B,25,Vegatable Soup,E10009,I115,48,Depot-C,11,Vegatable Soup,E10009,I116,96,Depot-A,9,Vegatable Soup,E10009,I116,96,Depot-B,10,Vegatable Soup,E10010,I117,96,Depot-A,5,Vegatable Soup,E10010,I117,96,Depot-B,11,Vegatable Soup,E10010,I117,96,Depot-C,20"
    //val deliveriesToShop = "Soup 1 ,E1,I1,10,Depot-A,2,Soup 1,E1,I1,5,Depot-B,5,Soup 1,E1,I1,3,Depot-C,5,Soup 1,E1,I2,2,Depot-C,10"
    //val deliveriesToDepot = "Soup 1,I1,20,Depot-A,Sup1,10,Soup 1,I1,30,Depot-A,Sup2,10,Soup 1,I1,20,Depot-B,Sup1,20,Soup 1,I1,20,Depot-C,Sup3,10"

    @Test
    fun testOneEANFromOneDepotFromOneSupplier() {
        val discountsApplied = "Soup 1,E1,£100"
        val deliveriesToShop = "Soup 1,E1,I1,10,Depot-A,1"
        val deliveriesToDepot = "Soup 1,I1,10,Depot-A,Sup1,10"
        val result = calculateRebateFromSuppliers(deliveriesToDepot,deliveriesToShop,discountsApplied)
        val expectedResult = listOf(DepotRebate("Depot-A", 10, 100.0F, 50.0F, listOf("I1")))
        Assertions.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun testOneEANFromOneDepotFromTwoSuppliers() {
        val discountsApplied = "Soup 1,E1,£100"
        val deliveriesToShop = "Soup 1,E1,I1,10,Depot-A,1"
        val deliveriesToDepot = "Soup 1,I1,10,Depot-A,Sup1,10,Soup 1,I1,10,Depot-A,Sup2,10"
        val result = calculateRebateFromSuppliers(deliveriesToDepot,deliveriesToShop,discountsApplied)
        val expectedResult = listOf(DepotRebate("Depot-A", 10, 100.0F, 50.0F, listOf("I1")))
        Assertions.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun testOneEANFromOneDepotWithTwoItemNumbersFromTwoSuppliers() {
        val discountsApplied = "Soup 1,E1,£100"
        val deliveriesToShop = "Soup 1,E1,I1,10,Depot-A,1,Soup 1,E1,I2,10,Depot-A,1"
        val deliveriesToDepot = "Soup 1,I1,10,Depot-A,Sup1,10,Soup 1,I2,10,Depot-A,Sup2,10"
        val result = calculateRebateFromSuppliers(deliveriesToDepot,deliveriesToShop,discountsApplied)
        val expectedResult = listOf(DepotRebate("Depot-A", 10, 100.0f, 50.0f, listOf("I1", "I2")))
        Assertions.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun testOneEANFromTwoDepotsFromFourSuppliers() {
        val discountsApplied = "Soup 1,E1,£100"
        val deliveriesToShop = "Soup 1,E1,I1,10,Depot-A,1,Soup 1,E1,I1,10,Depot-B,1"
        val deliveriesToDepot = "Soup 1,I1,10,Depot-A,Sup1,10,Soup 1,I1,10,Depot-A,Sup2,10,Soup 1,I1,10,Depot-B,Sup1,10,Soup 1,I1,10,Depot-B,Sup2,10"
        val result = calculateRebateFromSuppliers(deliveriesToDepot,deliveriesToShop,discountsApplied)
        val expectedResult = listOf(DepotRebate("Depot-A", 10, 50.0F, 25.0F, listOf("I1")), DepotRebate("Depot-B", 10, 50.0F, 25.0F, listOf("I1")))
        Assertions.assertThat(result).isEqualTo(expectedResult)
    }
}