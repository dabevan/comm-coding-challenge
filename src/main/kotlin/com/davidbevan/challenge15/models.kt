package com.davidbevan.challenge15

data class DiscountApplied(val product:String,
                           val ean: String,
                           val amount: Float)

data class DeliveryToShop(val product: String,
                          val ean: String,
                          val item: String,
                          val caseSize: Int,
                          val depot:String,
                          val quantityDelivered: Int)

data class DeliveryToDepot(val product:String,
                           val item:String,
                           val caseSize:Int,
                           val depot:String,
                           val supplier:String,
                           val quantityDelivered: Int)

data class DepotRebate(val depot: String,
                           val stockDelivered: Int,
                           val percentage: Float,
                           val shareOfRebate: Float,
                           val items: List<String>)

data class SupplierRebatePerDepot(val supplier: String,
                           val depot: String,
                           val stockDelivered: Int,
                           val percentage: Float,
                           val shareOfRebate: Float)


data class DepotStockDelivered(val depot: String,
                               val stockDelivered: Int,
                               val items:Array<String>)