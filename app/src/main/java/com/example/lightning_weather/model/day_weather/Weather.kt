package com.example.lightning_weather.model.day_weather

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)