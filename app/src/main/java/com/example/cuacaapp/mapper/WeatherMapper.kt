package com.example.cuacaapp.mapper

import android.annotation.SuppressLint
import com.example.cuacaapp.model.WeatherModel
import com.example.cuacaapp.remote.CurrentWeatherResponse
import com.example.cuacaapp.remote.FiveDaysWeatherResponse

object WeatherMapper {
    fun fromResponseToModel(from: CurrentWeatherResponse?): WeatherModel? {
        return WeatherModel(
            name = from?.name,
            weather1 = from?.weather?.get(0)?.main,
            weather2 = from?.weather?.get(0)?.description?.capitalizeFirstLetter(),
            temp = from?.main?.temp,
            humidity = from?.main?.humidity,
            visibility = from?.visibility,
            speed = from?.wind?.speed
        )
    }

    fun fromObjectToModel(from: FiveDaysWeatherResponse?): List<WeatherModel>? {
        return from?.list?.map {
            WeatherModel(
                weather1 = it.weather?.get(0)?.main,
                weather2 = it.weather?.get(0)?.description?.capitalizeFirstLetter(),
                temp = it.main?.temp,
                humidity = it.main?.humidity,
                visibility = it.visibility,
                speed = it.wind?.speed,
                date = it.dt_txt
            )
        } ?: emptyList()
    }

    @SuppressLint("DefaultLocale")
    fun String.capitalizeFirstLetter() =
        this.split(" ").joinToString(" ") { it.capitalize() }.trimEnd()
}