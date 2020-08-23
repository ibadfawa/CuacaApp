package com.example.cuacaapp.model

data class WeatherModel(
    var name: String? = null,
    var weather1: String? = null,
    var weather2: String? = null,
    var temp: Double? = null,
    var humidity: Int? = null,
    var visibility: Int? = null,
    var speed: Double? = null,
    var date: String? = null
)