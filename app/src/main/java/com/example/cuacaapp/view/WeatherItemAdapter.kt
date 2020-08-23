package com.example.cuacaapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cuacaapp.R
import com.example.cuacaapp.databinding.ViewItemWeatherBinding
import com.example.cuacaapp.model.WeatherModel

class WeatherItemAdapter : RecyclerView.Adapter<WeatherItemAdapter.ItemViewHolder>() {

    private var items: List<WeatherModel> = emptyList()
    fun update(items: List<WeatherModel>?) {
        this.items = items ?: emptyList()
        notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("items")
        fun RecyclerView.bindItems(items: List<WeatherModel>?) {
            val adapter = adapter as WeatherItemAdapter
            adapter.update(items)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(parent)

    override fun getItemCount() = items.size
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ItemViewHolder(
        private val parent: ViewGroup,
        private val binding: ViewItemWeatherBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.view_item_weather,
            parent,
            false
        )
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WeatherModel) {
            binding.weather = item
        }
    }

}