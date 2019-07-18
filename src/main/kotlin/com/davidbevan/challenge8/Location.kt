package com.davidbevan.challenge8


interface Purchasable {
    val purchasePrice: Int
    fun getOwner(): String?
    fun purchase(purchaser: String)
}

interface Developable {
    var shop:Shop?
    fun build(newShop: Shop)
}

sealed class Location {
    open fun getMaxNum() = 0
    open fun getRent() = 0
    open fun getPassingPayment() = 0


    class FreeParking : Location() {
        override fun getMaxNum() = 1
    }


    class Go : Location() {
        override fun getMaxNum() = 1
        override fun getPassingPayment() = 100
    }


    class FactoryOrWarehouse(val name: String, override val purchasePrice: Int) :Purchasable, Location() {
        private var owner:String? = null
        override fun getOwner() = owner
        override fun getMaxNum() = 4
        override fun getRent() = if (getOwner() != null) { 20 } else {0}
        override fun purchase(purchaser: String) { owner = purchaser }
    }


    class RetailSite(val name: String,
                     override val purchasePrice: Int,
                     val undevelopedRent: Int,
                     val costOfMiniStore: Int,
                     val costOfSupermarket: Int,
                     val costOfMegaStore: Int) :Purchasable, Developable, Location() {
        private var owner:String? = null
        override fun getOwner() = owner
        override fun getMaxNum() = 20
        override fun getRent() = if (getOwner() != null) { undevelopedRent + (shop?.getRent() ?: 0) } else {0}
        override fun purchase(purchaser: String) { owner = purchaser }
        override var shop: Shop? = null
        override fun build(newShop: Shop) { shop = newShop }

    }
}

abstract class Shop(private val additionalRent:Int) {
    fun getRent() = additionalRent
}

class MiniStore(private val additionalRent:Int): Shop(additionalRent)
class Supermarket(private val additionalRent:Int): Shop(additionalRent)
class MegaStore(private val additionalRent:Int): Shop(additionalRent)
