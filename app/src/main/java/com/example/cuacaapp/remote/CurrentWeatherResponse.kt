package com.example.cuacaapp.remote

import com.example.cuacaapp.data.weather.Main
import com.example.cuacaapp.data.weather.Weather
import com.example.cuacaapp.data.weather.Wind

data class CurrentWeatherResponse(
    var cod: Int?,
    var name: String?,
    var weather: List<Weather>?,
    var visibility: Int?,
    var wind: Wind?,
    var main: Main?
)