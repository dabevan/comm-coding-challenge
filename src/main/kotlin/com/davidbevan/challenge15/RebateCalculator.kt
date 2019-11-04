package com.davidbevan.challenge15

fun calculateRebateFromSuppliers(deliveriesToDepotsString: String, deliveriesToShopString: String, discountsAppliedString:String) : List<Any> {
    val discountsApplied = parseDiscountsAppliedString(discountsAppliedString)
    val deliveriesToShop = parseDeliveriesToShop(deliveriesToShopString)
    val deliveriesToDepots = parseDeliveriesToDepots(deliveriesToDepotsString)

    var returnList = mutableListOf<Any>()
    for (discount in discountsApplied) {
        val shopDeliveries = findShopDeliveriesForEAN(discount.ean, deliveriesToShop)
        val stockDeliveredPerDepot = calculateStockDeliveredPerDepot(shopDeliveries)
        val rebatePerDepot = calculateRebatePerDepot(discount.amount, stockDeliveredPerDepot)
        //val rebatePerSupplierPerDepot = calculateRebatePerSupplierPerDepot(discount.amount,rebatePerDepot)

        returnList = returnList.plus(rebatePerDepot).toMutableList()
        println("shopDeliveries:$shopDeliveries")
        println("stockDeliveredPerDepot:$stockDeliveredPerDepot")
        println("rebatePerDepot:$rebatePerDepot")
    }
    return returnList
}


private fun calculateStockDeliveredPerDepot(shopDeliveries: List<DeliveryToShop>) : List<DepotStockDelivered>{
    var stockDeliveredPerDepot = mutableListOf<DepotStockDelivered>()
    val deliveriesGroupedByDepot = shopDeliveries.groupBy { shopDelivery -> shopDelivery.depot }
    deliveriesGroupedByDepot.keys.map {depot ->
        var deliveriesForDepot = deliveriesGroupedByDepot[depot]
        var totalStockDelivered = 0
        var items = mutableListOf<String>()
        deliveriesForDepot?.map { delivery ->
            totalStockDelivered =+  delivery.caseSize * delivery.quantityDelivered
            items.add(delivery.item)
        }
        stockDeliveredPerDepot.add(DepotStockDelivered(depot,totalStockDelivered, items.distinct().toTypedArray()))
    }
    return stockDeliveredPerDepot
}


fun calculateRebatePerDepot(discountAmount: Float, stockDeliveredPerDepot: List<DepotStockDelivered>): List<DepotRebate> {
    var rebatePerDepot = mutableListOf<DepotRebate>()
    var totalStockDeliveredAcrosssAllDepots = 0
    for(delivery in stockDeliveredPerDepot) {
        totalStockDeliveredAcrosssAllDepots += delivery.stockDelivered
    }
    for(delivery in stockDeliveredPerDepot) {
        val portionOfRebate = delivery.stockDelivered.toFloat()/totalStockDeliveredAcrosssAllDepots.toFloat()
        rebatePerDepot.add(DepotRebate(delivery.depot,delivery.stockDelivered,portionOfRebate*100, portionOfRebate*discountAmount*0.5F, delivery.items.toList()))
    }
    return rebatePerDepot
}


fun findShopDeliveriesForEAN(ean: String, deliveriesToShop:List<DeliveryToShop>) : List<DeliveryToShop> {
    return deliveriesToShop.filter { delivery -> delivery.ean == ean }
}


//fun calculateRebatePerSupplierPerDepot(amount: Float, rebatePerDepot: List<DepotRebate>): List<SupplierRebatePerDepot> {
//    var rebatePerSupplierPerDepot = mutableListOf<SupplierRebatePerDepot>()
//    var totalStockDeliveredAcrosssAllSuppliers = 0
//    for(delivery in stockDeliveredPerDepot) {
//        totalStockDeliveredAcrosssAllDepots += delivery.stockDelivered
//    }
//    for(delivery in stockDeliveredPerDepot) {
//        val portionOfRebate = delivery.stockDelivered.toFloat()/totalStockDeliveredAcrosssAllDepots.toFloat()
//        rebatePerDepot.add(DepotRebate(delivery.depot,delivery.stockDelivered,portionOfRebate*100, portionOfRebate*discountAmount*0.5F, delivery.items.toList()))
//    }
//    return rebatePerDepot
//}




