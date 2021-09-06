package com.example.lightning_weather.features.home.model

import com.google.gson.annotations.SerializedName

class HomeResponse(
    val temperature: Temperature
) {
    data class Temperature(
        @SerializedName("current_value")
        val cityName: String,
        val temperature: Temperature
    )
}