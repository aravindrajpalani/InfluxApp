package me.aravindraj.influxapp.data.model

data class FoodList(
    val Currency: String,
    val FoodList: List<Food>,
    val status: Status
)