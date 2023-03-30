package com.azrosk.checktheweather.api.entity


import com.azrosk.checktheweather.models.Main
import com.azrosk.checktheweather.models.Sys
import com.azrosk.checktheweather.models.Weather
import com.azrosk.checktheweather.models.Wind
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind
)