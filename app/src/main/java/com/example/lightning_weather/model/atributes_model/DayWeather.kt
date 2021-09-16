package com.example.lightning_weather.model.atributes_model

data class DayWeather(
    val day: String,
    val hour: String,
    val tempo: Int,
    val icon: String,
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Float,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)
