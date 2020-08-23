package com.example.cuacaapp.data.weather

data class ObjectWeather(
    var weather: List<Weather>?,
    var visibility: Int?,
    var wind: Wind?,
    var main: Main?,
    var dt_txt: String?
)