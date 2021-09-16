package com.example.lightning_weather.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lightning_weather.BASE_URL
import com.example.lightning_weather.KELVIN_TO_CELSIUS
import com.example.lightning_weather.R
import com.example.lightning_weather.model.atributes_model.DayWeather
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class CardAdapter(private val listDay: ArrayList<DayWeather>) :
    RecyclerView.Adapter<CardAdapter.CardHolder>() {
    class CardHolder(view: View) : RecyclerView.ViewHolder(view) {
        val day: TextView = view.findViewById(R.id.day)
        val hour: TextView = view.findViewById(R.id.hour)
        val tempo: TextView = view.findViewById(R.id.temperature)
        val icon: ImageView = view.findViewById(R.id.iconInCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        return CardHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listDay.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val currentItem = listDay[position]
        val dateFormat: DateFormat = SimpleDateFormat("dd/MM")
        val hourFormat: DateFormat = SimpleDateFormat("HH:mm")
        val listDays: MutableList<Date> = mutableListOf()
        var date = Calendar.getInstance().time
        val instance = Calendar.getInstance()
        for (dayWeather in listDay) {
            instance.time = date
            instance.add(Calendar.HOUR_OF_DAY, 3)
            listDays.add(instance.time)
            date = instance.time
        }
        holder.day.text = dateFormat.format(listDays[position])
        holder.hour.text = hourFormat.format(listDays[position])
        holder.tempo.text = currentItem.main.temp.let { convertKelvinToCelsius(it) }
        val iconUrl = BASE_URL + "/img/w/" + currentItem.weather[0].icon + ".png"
        Picasso.with(holder.day.context).load(iconUrl).resize(100, 100).into(holder.icon)
    }

    private fun convertKelvinToCelsius(temp: Double): String {
        return (temp.minus(KELVIN_TO_CELSIUS)).roundToInt().toString() + "°C"
    }
}
