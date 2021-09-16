package com.example.lightning_weather.model.day_weather

data class DayWeatherResponse(
    val cnt: Int,
    val cod: String,
    val list: List<DayWeather>,
    val message: Int
)