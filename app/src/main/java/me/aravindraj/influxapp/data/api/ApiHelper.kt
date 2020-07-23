package me.aravindraj.influxapp.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getFoodData() = apiService.getFoodData()
}
