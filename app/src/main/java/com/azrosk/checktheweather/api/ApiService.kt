package com.azrosk.checktheweather.api

import com.azrosk.checktheweather.models.WeatherResponse
import com.azrosk.checktheweather.utilities.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/weather?q=Minsk&units=metric&appid=61004004e9c98755ef6392d8bf49cc15")
    suspend fun getWeather() : Response<WeatherResponse>
}