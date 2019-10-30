package com.davidbevan.challenge14

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MyFoldTest {

    var listOfIntsOneToFive = listOf(1,2,3,4,5)
    var listOfIntsFourToOne = listOf(4,3,2,1)
    var listOfStringsOneToFive = listOf("1","2","3","4","5")
    var listOfPersons = listOf(Person("Fred","Bloggs", 32),
                                         Person("Billy","Nomates", 21),
                                         Person("Joe","Soap", 28))


    @Test
    fun `Add some ints`() {
        var expected = listOfIntsOneToFive.fold(10)  {acc, value ->  acc + value}
        var actual = listOfIntsOneToFive.myFold(10) { acc, value -> acc + value}
        Assertions.assertThat(actual).isEqualTo(expected)
        println(actual)
    }

   @Test
    fun `Subtract some ints`() {
        var expected = listOfIntsOneToFive.fold(10) { acc, value ->  acc - value}
        var actual = listOfIntsOneToFive.myFold(10) { acc, value -> acc - value}
        Assertions.assertThat(actual).isEqualTo(expected)
        println(actual)
    }

    @Test
    fun `Add some strings`() {
        var expected = listOfStringsOneToFive.fold("10") { acc, value ->  acc + value}
        var actual = listOfStringsOneToFive.myFold("10") { acc, value -> acc + value}
        Assertions.assertThat(actual).isEqualTo(expected)
        println(actual)
    }

    @Test
    fun `Example from challenge site`() {
        var expected = listOfIntsFourToOne.fold(1) { acc, value ->  acc * value}
        var actual = listOfIntsFourToOne.myFold(1) { acc, value -> acc * value}
        Assertions.assertThat(actual).isEqualTo(expected)
        println(actual)
    }

    @Test
    fun `Example from challenge site with generic fold`() {
        var expected = listOfIntsFourToOne.fold(1) { acc, value ->  acc * value}
        //var expected = fold(listOfIntsFourToOne, 1) { acc, value ->  acc * value}
        //var actual = myFold(listOfIntsFourToOne,1) { acc, value -> acc * value}
        var actual = listOfIntsFourToOne.myFold(1) { acc, value -> acc * value}
        Assertions.assertThat(actual).isEqualTo(expected)
        println(actual)
    }

    @Test
    fun `Add up the ages of a list of Persons`() {
        var expected = listOfPersons.fold(0) { acc, value ->  acc + value.age}
        var actual = listOfPersons.myFold(0) { acc, value -> acc + value.age}
        Assertions.assertThat(actual).isEqualTo(expected)
        println(actual)
    }


}

public data class Person(val firstName:String,
                  val surname:String,
                  val age:Int)
