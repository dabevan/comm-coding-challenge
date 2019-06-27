package com.davidbevan.challenge6

class Filters {

    val numberIsLessThan5 :(Int) -> Boolean = { aNumber:Int -> aNumber < 5 }
    val numberIsEven :(Int) -> Boolean = { aNumber:Int -> aNumber % 2 == 0}
    val numberIsOdd :(Int) -> Boolean = { aNumber:Int -> aNumber % 2 != 0}


    fun myFilter(unFilteredNumbers :Array<Int>, rule :(Int) -> Boolean):Array<Int> {
        return(recursiveFilter(emptyArray(), unFilteredNumbers.toList(), rule))
    }


    fun recursiveFilter(result :Array<Int>, remainingNumbers :List<Int>, rule :(Int) -> Boolean):Array<Int> {
        if(remainingNumbers.isEmpty()) return result
        if(rule(remainingNumbers[0])) {
            return recursiveFilter(result.plus(remainingNumbers[0]), remainingNumbers.tail(), rule)
        }
        return recursiveFilter(result, remainingNumbers.tail(), rule)
    }

    fun <Int> List<Int>.tail() = this.takeLast(this.size -1)



    //Below was my first simpler mutable, non-recursive version

    //fun myFilter(unFilteredNumbers :Array<Int>, rule :(Int) -> Boolean):Array<Int> {
    //    var filteredNumbers = emptyArray<Int>()
    //    for(number in unFilteredNumbers) if(rule(number)) filteredNumbers += number
    //    return filteredNumbers
   // }

}