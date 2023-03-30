package com.azrosk.checktheweather.models


import com.google.gson.annotations.SerializedName

data class Sys(
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
)