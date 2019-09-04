package com.davidbevan.challenge11

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.*

val mapper: ObjectMapper = ObjectMapper().registerKotlinModule().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

class BeerFinder {
    fun obtainListOfBeers(pubsInArea:String) : List<Beer>{
        var pubsInArea:PubsInArea = mapper.readValue(pubsInArea)
        pubsInArea = removeDuplicatePubs(pubsInArea)
        return listBeersFromPubsInArea(pubsInArea.pubs).sortedBy { it.name }
    }
}


fun removeDuplicatePubs(pubsInArea: PubsInArea): PubsInArea {
    return PubsInArea(pubsInArea.pubs.toTypedArray().distinctBy { it.Name }.toList())
}

fun listBeersFromPubsInArea(pubs:List<Pub>):List<Beer> {
    return pubs.map{pub -> listBeersFromPub(pub)}.flatten()
}

fun listBeersFromPub(pub:Pub): List<Beer> {
    return pub.RegularBeers.map { name -> Beer(name, pub.Name, pub.PubService, true) }.plus(
           pub.GuestBeers.map { name -> Beer(name, pub.Name, pub.PubService, false) })
}


data class Beer(
    val name:String,
    val pubName:String,
    val pubService:String,
    val regularBeer:Boolean
)

data class PubsInArea(
    val pubs: List<Pub>
)

data class Pub(
    val Branch: String,
    val CreateTS: String,
    val GuestBeers: List<String> = emptyList(),
    val Id: String,
    val Name: String,
    val PostCode: String,
    val PubService: String,
    val RegularBeers: List<String> = emptyList()
)
