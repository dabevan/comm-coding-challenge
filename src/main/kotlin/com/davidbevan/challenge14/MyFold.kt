package com.davidbevan.challenge14

fun <AccType,ListType>List<ListType>.myFold(initialValue:AccType, funct:(AccType,ListType)->AccType): AccType {
    var acc = initialValue
    this.map { it ->
        acc = funct(acc,it)
    }
    return acc
}

