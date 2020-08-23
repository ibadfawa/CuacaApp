package com.example.cuacaapp.remote

import com.example.cuacaapp.data.weather.ObjectWeather

data class FiveDaysWeatherResponse(
    var cod: Int?,
    var list: List<ObjectWeather>?
)