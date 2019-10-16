package com.davidbevan.challenge14

fun List<Int>.myFold(initialValue:Int, funct :(Int, Int) -> Int):Any {
    var acc = initialValue
    this.map { it ->
        acc = funct(acc,it)
    }
    return acc
}


fun List<String>.myFold(initialValue:String, funct :(String, String) -> String):Any {
    var acc = initialValue
    this.map { it ->
        acc = funct(acc,it)
    }
    return acc
}


fun <ListType, AccType>myFold(list: List<ListType>, initialValue:AccType, funct:(AccType,ListType)->AccType): AccType {
    var acc = initialValue
    list.map { it ->
        acc = funct(acc,it)
    }
    return acc
}

