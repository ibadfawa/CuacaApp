package com.example.cuacaapp

import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuacaapp.model.WeatherModel
import kotlinx.coroutines.*

//TODO dont forget use viewModelScope, uiScope
class MainViewModel : ViewModel() {

    val example = MutableLiveData<Int>()
    val currentWeather = MutableLiveData<WeatherModel>()
    val fiveDaysWeather = MutableLiveData<List<WeatherModel>>()

    fun searchWeather(query: String) {
//        MainRepository.getCurrentWeather(query, {
//            currentWeather.value = it
//        }, {
//            currentWeather.value = null
//        })
        viewModelScope.launch {
            currentWeather.value = MainRepository.getCurrentWeather(query)
        }

        MainRepository.get5DaysForecastWeather(query, {
            fiveDaysWeather.value = it
        }, {
            fiveDaysWeather.value = emptyList()
        })
    }

    fun asyncAwait(view: TextView?) = CoroutineScope(Dispatchers.IO).launch {
        CoroutineScope(Dispatchers.Main).launch {
            view?.text = "START"
        }

        println("QQQ = START")
        val yyy = getNumeric()
        val zzz = getString()
        println("QQQ = XXX: " + yyy + " YYY: " + zzz)

        println("QQQ = RESTART")
        val aaa = async { getNumeric() }
        val bbb = async { getString() }
        println("QQQ 0 = AAA: " + aaa + " BBB: " + bbb)
        println("QQQ 1 = AAA: " + aaa.await() + " BBB: " + bbb.await())
//        val qwerty = aaa.await()
//        qwerty.plus(0)
        println("QQQ 2 = AAA: " + aaa + " BBB: " + bbb)

        CoroutineScope(Dispatchers.Main).launch {
            view?.text = "SUKSES"
        }
    }

    fun cobaCoba() = viewModelScope.launch {
    }

    suspend fun getNumeric(): Int {
        delay(5000)
        return 9
    }

    suspend fun getString(): String {
        delay(10000)
        return "9"
    }
}