package com.davidbevan.challenge15


fun parseDiscountsAppliedString(discountsAppliedString: String): List<DiscountApplied> {
    var discountsApplied = mutableListOf<DiscountApplied>()
    val discountElementArray = discountsAppliedString.split(",")
    var product = ""
    var ean = ""
    var discount: Float
    discountElementArray.mapIndexed() { index, element ->
        var elementPointer = index%3
        if(elementPointer == 0) product = element
        if(elementPointer == 1) ean = element
        if(elementPointer == 2) {
            discount = element.substring(1).toFloat()
            discountsApplied.add(DiscountApplied(product,ean,discount))
        }
    }
    return discountsApplied
}

fun parseDeliveriesToShop(deliveriesToShopString: String): List<DeliveryToShop> {
    var delivieriesToShop = mutableListOf<DeliveryToShop>()
    val deliveryElementArray = deliveriesToShopString.split(",")
    var product = ""
    var ean = ""
    var item = ""
    var caseSize: Int = 0
    var depot = ""
    var quantityDelivered: Int
    deliveryElementArray.mapIndexed() { index, element ->
        var elementPointer = index%6
        if(elementPointer == 0) product = element
        if(elementPointer == 1) ean = element
        if(elementPointer == 2) item = element
        if(elementPointer == 3) caseSize = element.toInt()
        if(elementPointer == 4) depot = element
        if(elementPointer == 5) {
            quantityDelivered = element.toInt()
            delivieriesToShop.add(DeliveryToShop(product,ean,item,caseSize ,depot, quantityDelivered))
        }
    }
    return delivieriesToShop
}

fun parseDeliveriesToDepots(deliveriesToDepotsString: String): List<DeliveryToDepot> {
  var delivieriesToDepot = mutableListOf<DeliveryToDepot>()
    val deliveryElementArray = deliveriesToDepotsString.split(",")
    var product = ""
    var item = ""
    var caseSize: Int = 0
    var depot = ""
    var supplier = ""
    var quantityDelivered: Int
    deliveryElementArray.mapIndexed() { index, element ->
        var elementPointer = index%6
        if(elementPointer == 0) product = element
        if(elementPointer == 1) item = element
        if(elementPointer == 2) caseSize = element.toInt()
        if(elementPointer == 3) depot = element
        if(elementPointer == 4) supplier = element
        if(elementPointer == 5) {
            quantityDelivered = element.toInt()
            delivieriesToDepot.add(DeliveryToDepot(product,item,caseSize ,depot, supplier,quantityDelivered))
        }
    }
    return delivieriesToDepot

}