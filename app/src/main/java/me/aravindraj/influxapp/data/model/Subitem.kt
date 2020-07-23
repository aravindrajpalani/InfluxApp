package me.aravindraj.influxapp.data.model

data class Subitem(
    val Name: String,
    val PriceInCents: String,
    val SubitemPrice: String,
    val VistaParentFoodItemId: String,
    val VistaSubFoodItemId: String
)