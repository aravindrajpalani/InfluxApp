package me.aravindraj.influxapp.data.model

data class Fnblist(
    val Cinemaid: String,
    val ImageUrl: String,
    val ImgUrl: String,
    var ItemPrice: String,
    val Name: String,
    val OtherExperience: String,
    val PriceInCents: String,
    val SevenStarExperience: String,
    val SubItemCount: Int,
    val TabName: String,
    val VegClass: String,
    val VistaFoodItemId: String,
    val subitems: List<Subitem>,
    var quantity: Int = 0,
    var selectedSubFoodItemId: String
)