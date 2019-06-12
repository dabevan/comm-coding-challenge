package com.davidbevan.challenge6

class Filters {

    val numberIsLessThan5 :(Int) -> Boolean = { aNumber:Int -> aNumber < 5 }
    val numberIsEven :(Int) -> Boolean = { aNumber:Int -> aNumber % 2 == 0}
    val numberIsOdd :(Int) -> Boolean = { aNumber:Int -> aNumber % 2 != 0}


    fun myFilter(unFilteredNumbers :Array<Int>, rule :(Int) -> Boolean):Array<Int?> {
        var filteredNumbers = arrayOfNulls<Int>(0)
        for(number in unFilteredNumbers) if(rule(number)) filteredNumbers += number
        return filteredNumbers
    }


}