package com.example.lightning_weather.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lightning_weather.BASE_URL
import com.example.lightning_weather.HomeActivity
import com.example.lightning_weather.KELVIN_TO_CELSIUS
import com.example.lightning_weather.METER_PER_SECOND_TO_KILOMETER_PER_HOUR
import com.example.lightning_weather.adapters.DetailAdapter
import com.example.lightning_weather.databinding.FragmentNext7DaysBinding
import com.example.lightning_weather.viewModel.DetailViewModel
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.util.*
import kotlin.math.roundToInt

class NextSevenDaysFragment : Fragment() {
    private lateinit var detailAdapter: DetailAdapter
    private lateinit var detailRecyclerView: RecyclerView

    private lateinit var binding: FragmentNext7DaysBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNext7DaysBinding.inflate(inflater)
        val detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        binding.backBtn.setOnClickListener { view: View ->
            (activity as HomeActivity).removeFragment(this)
        }

        detailRecyclerView = binding.listCard
        detailViewModel.listDailyWeather.observe(viewLifecycleOwner, Observer { cardDetail ->
            detailAdapter = DetailAdapter(cardDetail)
            detailRecyclerView.adapter = detailAdapter
        })
        detailRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
        }

        detailViewModel.weather.observe(viewLifecycleOwner, Observer { weather ->
            binding.cloudness.text = weather.clouds?.all?.roundToInt()?.toString() + "%"
            binding.temp.text =
                weather.main?.temp?.minus(KELVIN_TO_CELSIUS)?.roundToInt().toString() + "°C"

            binding.date.text = DateFormat.getDateInstance(DateFormat.FULL, Locale.US)
                .format(Calendar.getInstance().time).toString()

            binding.humidity.text = weather.main?.humidity?.roundToInt()?.toString() + "%"

            binding.realFeel.text =
                weather.main?.feels_like?.minus(KELVIN_TO_CELSIUS)?.roundToInt().toString() + "°C"

            binding.wind.text =
                weather.wind?.speed?.times(METER_PER_SECOND_TO_KILOMETER_PER_HOUR)?.roundToInt()
                    ?.toString() + " km/h"

            binding.main.text = weather.weather[0].main
            val iconUrl = BASE_URL + "/img/w/" + weather.weather[0].icon + ".png"
            Picasso.with(activity).load(iconUrl).resize(200, 200).into(binding.icon)
        })

        return binding.root
    }
}
