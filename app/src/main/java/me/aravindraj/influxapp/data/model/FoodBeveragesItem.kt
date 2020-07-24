package me.aravindraj.influxapp.data.model


data class FoodBeveragesItem(
    val cinemaId: String,
    val tabName: String,
    val imageURL: String,
    var itemPrice: String,
    val itemName: String,
    val vegClass: String,
    val vistaFoodItemId: String,
    val subitems: List<Subitem>,
    var selectedSubItemId: String,
    var selectedSubItemName: String,
    var selectedSubItemPrice: String,
    var count: Int = 0
)