package com.example.cuacaapp.presenter

import android.widget.TextView
import androidx.databinding.BindingAdapter

object UtilsPresenter {
    const val nothing = "-"

    @JvmStatic
    @BindingAdapter("bindText")
    fun loadText(textView: TextView, text: Any?) {
        textView.text = text?.toString() ?: "-"
    }
}