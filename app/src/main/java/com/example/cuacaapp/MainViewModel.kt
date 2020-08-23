package com.example.cuacaapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cuacaapp.model.WeatherModel

//TODO dont forget use viewModelScope, uiScope
class MainViewModel : ViewModel() {
    val currentWeather = MutableLiveData<WeatherModel>()
    val fiveDaysWeather = MutableLiveData<List<WeatherModel>>()
    fun searchWeather(query: String) {
        MainRepository.getCurrentWeather(query, {
            currentWeather.value = it
        }, {
            currentWeather.value = null
        })

        MainRepository.get5DaysForecastWeather(query, {
            fiveDaysWeather.value = it
        }, {
            fiveDaysWeather.value = emptyList()
        })
    }
}