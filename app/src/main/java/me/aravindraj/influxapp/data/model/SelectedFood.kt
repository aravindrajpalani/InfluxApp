package me.aravindraj.influxapp.data.model


data class SelectedFood(
    val vistaFoodItemId: String,
    val itemName: String,
    var itemPrice: String,
    var selectedSubItemName: String,
    var quantity: Int = 0
)