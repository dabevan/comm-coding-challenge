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


//fun List<Any>.myFold(initialValue:Any, funct :(Any, Any) -> Any):Any {
//    var acc = initialValue
//    this.map { it ->
//        acc = funct(acc,it)
//    }
//    return acc
//}


//// fun List<Person>.myFold(initialValue:Int, funct :(Int, Person) -> Int):Any {
////    var acc = initialValue
////    this.map { it ->
////        acc = funct(acc,it)
////    }
////    return acc
////}
//
//
//public data class Person(val firstName:String,
//                  val surname:String,
//                  val age:Int)