package me.aravindraj.influxapp.data.repository

import me.aravindraj.influxapp.data.api.ApiHelper


class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getFoodData() = apiHelper.getFoodData()
}
