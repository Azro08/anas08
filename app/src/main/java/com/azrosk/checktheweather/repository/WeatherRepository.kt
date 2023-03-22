package com.azrosk.checktheweather.repository

import com.azrosk.checktheweather.api.ApiService
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getWeather() = apiService.getWeather()
}