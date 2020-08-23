package com.example.cuacaapp.presenter

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kotlin.math.roundToInt

object WeatherPresenter {

    fun weatherToImage(string: String?): String {
        return when (string) {
            "Clouds" -> "https://www.treehugger.com/thmb/Kc3Gx2Y6SmBJNVZgf0OCoaEZpPQ=/735x0/__opt__aboutcom__coeus__resources__content_migration__mnn__images__2018__08__CollectionOfCloudsAgainstABlueSky-8cae9f3109d14dcf98d9facc5775222f.jpg"
            "Rain" -> "https://s7d2.scene7.com/is/image/TWCNews/heavy_rain-1_jpg"
            "Clear" -> "https://images.assetsdelivery.com/compings_v2/oottoo008/oottoo0081309/oottoo008130900004.jpg"
            else -> "https://i.ytimg.com/vi/6MGRkUlFZws/maxresdefault.jpg"
        }
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("textWeather")
    fun TextView.textWeather(text: String?) {
        this.text = "in ${text}"
    }

    @JvmStatic
    @BindingAdapter("srcWeather")
    fun ImageView.srcWeather(imageName: String?) {
        val imageUrl = weatherToImage(imageName)
        Glide.with(context)
            .load(imageUrl)
//            .apply(RequestOptions().circleCrop())
            .into(this)
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("textTemperature")
    fun TextView.textTemperature(temp: Double?) {
        val celcius: Double = temp?.minus(273.15) ?: 0.0
        text = "${celcius.roundToInt().toString().plus("C")} (temp)"
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("textHumidity")
    fun TextView.textHumidity(humidity: Int?) {
        text =
            if (humidity != null) "${humidity.toString().plus("%")} (humidity)" else "0% (humidity)"
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("textVisibility")
    fun TextView.textVisibility(visibility: Int?) {
        text = if (visibility != null) "${visibility.div(1000).toString()
            .plus(" km")} (visibility)" else "0 km (visibility)"
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("textWind")
    fun TextView.textWind(wind: Double?) {
        text = if (wind != null) "${wind.toString().plus(" m/s")} (wind)" else "0.0 m/s (wind)"
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("textTemperatureItem")
    fun TextView.textTemperatureItem(temp: Double?) {
        val celcius: Double = temp?.minus(273.15) ?: 0.0
        text = celcius.roundToInt().toString().plus("C")
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("textHumidityItem")
    fun TextView.textHumidityItem(humidity: Int?) {
        text = if (humidity != null) humidity.toString().plus("%") else "0%"
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("textVisibilityItem")
    fun TextView.textVisibilityItem(visibility: Int?) {
        text = if (visibility != null) visibility.div(1000).toString().plus(" km") else "0 km"
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("textWindItem")
    fun TextView.textWindItem(wind: Double?) {
        text = if (wind != null) wind.toString().plus(" m/s") else "0.0 m/s"
    }
}