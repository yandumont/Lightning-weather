package com.example.lightning_weather.features.home.api.model

import com.google.gson.annotations.SerializedName

class HomeResponse(
    val temperature: Temperature
) {
    data class Temperature(
        @SerializedName("current_value")
        val currentValue: Int,
        val receivables: Int
    )

//    data class Benefit(
//        @SerializedName("indicator_color")
//        val indicatorColor: String,
//        val image: String,
//        val title: String,
//        val message: String,
//        @SerializedName("text_link")
//        val textLink: String
//    )
}