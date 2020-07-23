package me.aravindraj.influxapp.data.api

import me.aravindraj.influxapp.data.model.FoodList
import retrofit2.http.GET

interface ApiService {

    @GET("5b700cff2e00005c009365cf")
    suspend  fun getFoodData(): FoodList
}