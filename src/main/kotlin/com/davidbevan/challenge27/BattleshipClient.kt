package com.davidbevan.challenge27

import org.http4k.client.JavaHttpClient
import org.http4k.core.*
import org.http4k.format.Jackson.auto


fun getURL(url: String): Response {
    val request = Request(Method.GET, url)
    val client: HttpHandler = JavaHttpClient()
    return client(request)
}

fun shoot(shots: String, game: Game, player: String = "DavidBevan"): ShotResults {
    val shotResults =  Body.auto<ShotResults>().toLens()(getURL("https://challenge27.appspot.com/?shots=$shots&game=${game.name}&player=$player"))
    game.recordShots(shots, shotResults)
    return shotResults
}


data class ShotResults(val results: List<String>)


/*
ShotResults(results=[M, M, M, M, M, M, M, M, M, M])
ShotResults(results=[M, M, M, M, S, S, S, S, M, M])
ShotResults(results=[M, H, M, M, M, M, M, M, M, M])
ShotResults(results=[M, H, M, H, M, M, M, M, M, M])
ShotResults(results=[M, H, M, H, M, M, M, H, M, M])
ShotResults(results=[M, H, M, H, M, M, M, H, M, M])
ShotResults(results=[M, H, M, M, S, M, M, M, M, M])
ShotResults(results=[M, M, M, M, M, M, M, M, M, M])
ShotResults(results=[M, M, M, M, M, M, M, M, H, M])
ShotResults(results=[S, M, M, M, M, M, M, M, H, M])
*/







































//fun main() {
//    var col = arrayOf(0,1,2,3,4,5,6,7,8,9)
//    col.forEach {
//        val jsonBody = getURL("https://challenge27.appspot.com/?shots=A${it}B${it}C${it}D${it}E${it}F${it}G${it}H${it}I${it}J${it}")
//        val shotResults = Body.auto<ShotResults>().toLens()(jsonBody)
//        println(shotResults)
//    }
//}






//fun main() {
//    val jsonBody = getURL("https://stubs-api.catalogue.flex.jl-digital.net/v0/product-summaries?productId=3328696&key=AIzaSyDFq24k8YseOOtrvDhY0D28QCrgRT1U9-o")
//    val prodCatProduct = Body.auto<Array<ProdCatProduct>>().toLens()(jsonBody)
//    println(prodCatProduct.first().id)
//    println(prodCatProduct.first().title)
//    println(prodCatProduct.first().brand)
//}
//

//data class ProdCatProduct(val id: String, val title: String, val brand: String)
