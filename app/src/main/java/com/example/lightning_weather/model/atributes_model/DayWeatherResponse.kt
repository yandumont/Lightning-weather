package com.example.lightning_weather.model.atributes_model

data class DayWeatherResponse(
    val cnt: Int,
    val cod: String,
    val list: List<DayWeather>,
    val message: Int
)
