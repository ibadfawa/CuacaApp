package com.example.cuacaapp

import android.annotation.SuppressLint
import com.example.cuacaapp.mapper.WeatherMapper
import com.example.cuacaapp.model.WeatherModel
import com.example.cuacaapp.remote.CurrentWeatherResponse
import com.example.cuacaapp.remote.FiveDaysWeatherResponse
import com.example.cuacaapp.remote.OpenWeatherService
import org.jetbrains.annotations.NotNull
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MainRepository {
    private val retrofitBuilder: OpenWeatherService by lazy {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Config.URL_API_WEATHER_CUACA_APP)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return@lazy retrofit.create<OpenWeatherService>(OpenWeatherService::class.java)
    }

    suspend fun getCurrentWeather(query: String): WeatherModel? {
        return retrofitBuilder.listCurrentWeatherSuspend(query, Config.KEY_API_WEATHER_CUACA_APP)
            .let {
                WeatherMapper.fromResponseToModel(it)
            }
    }

    fun getCurrentWeather(query: String, onSuccess: (WeatherModel?) -> Unit, onError: () -> Unit) {
        val request: Call<CurrentWeatherResponse> =
            retrofitBuilder.listCurrentWeather(query, Config.KEY_API_WEATHER_CUACA_APP)
        request.enqueue(object : Callback<CurrentWeatherResponse?> {
            override fun onResponse(
                @NotNull call: Call<CurrentWeatherResponse?>,
                @NotNull response: Response<CurrentWeatherResponse?>
            ) {
                if (response.body()?.cod == 200) {
                    val data = response.body() as CurrentWeatherResponse
                    onSuccess(WeatherMapper.fromResponseToModel(data))
                } else {
                    val x = response
                    println("ERX: " + x.toString())
                    onError()
                }
            }

            @SuppressLint("SetTextI18n")
            override fun onFailure(
                @NotNull call: Call<CurrentWeatherResponse?>,
                @NotNull t: Throwable?
            ) {
                val x = t
                println("ERX: " + x.toString())
                onError()
            }
        })
    }

    fun get5DaysForecastWeather(
        query: String,
        onSuccess: (List<WeatherModel>?) -> Unit,
        onError: () -> Unit
    ) {
        val request: Call<FiveDaysWeatherResponse> =
            retrofitBuilder.listForecastWeather(query, Config.KEY_API_WEATHER_CUACA_APP)
        request.enqueue(object : Callback<FiveDaysWeatherResponse?> {
            override fun onResponse(
                @NotNull call: Call<FiveDaysWeatherResponse?>,
                @NotNull response: Response<FiveDaysWeatherResponse?>
            ) {
                if (response.body()?.cod == 200) {
                    val data = response.body() as FiveDaysWeatherResponse
                    onSuccess(WeatherMapper.fromObjectToModel(data))
                } else {
                    val x = response
                    println("ERX: " + x.toString())
                    onError()
                }
            }

            @SuppressLint("SetTextI18n")
            override fun onFailure(
                @NotNull call: Call<FiveDaysWeatherResponse?>,
                @NotNull t: Throwable?
            ) {
                val x = t
                println("ERX: " + x.toString())
                onError()
            }
        })
    }
}