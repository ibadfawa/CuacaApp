package com.example.cuacaapp.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("data/2.5/weather")
    fun listCurrentWeather(
        @Query("q") query: String?,
        @Query("appid") appid: String
    ): Call<CurrentWeatherResponse>

    @GET("data/2.5/weather")
    suspend fun listCurrentWeatherSuspend(
        @Query("q") query: String?,
        @Query("appid") appid: String
    ): CurrentWeatherResponse?

    @GET("data/2.5/forecast")
    fun listForecastWeather(
        @Query("q") query: String?,
        @Query("appid") appid: String
    ): Call<FiveDaysWeatherResponse>
}