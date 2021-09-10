package com.example.lightning_weather.fragment

import com.example.lightning_weather.viewModel.HomeViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lightning_weather.*
import com.example.lightning_weather.adapter.CardAdapter
import com.example.lightning_weather.databinding.HomeFragmentBinding
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt


class HomeFragment : Fragment() {
    private lateinit var dayAdapter: CardAdapter
    private lateinit var dayRecyclerView: RecyclerView
    private var listDayWeather : ArrayList<DayWeather> = ArrayList()

    private lateinit var binding: HomeFragmentBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("HomeFragment", "createView")
        binding = HomeFragmentBinding.inflate(inflater)

        binding.seeMore.setOnClickListener { view: View ->
            val detailFragment = DetailFragment()
            (activity as HomeActivity).loadFragment(detailFragment)
        }

        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel.weather.observe(viewLifecycleOwner, Observer { weather ->
            binding.temperature.text = weather.main?.temp?.let { convertKelvinToCelsius(it) }

            binding.mainWeather.text = weather.weather[0].main
            binding.description.text = weather.weather[0].description
            binding.dateTime.text = DateFormat.getDateInstance(DateFormat.FULL, Locale.US)
                .format(Calendar.getInstance().time).toString()
            binding.address.text = weather.name

            binding.humidity.text = weather.main?.humidity?.roundToInt()?.toString() + "%"
            binding.progressHumidity.progress = weather.main?.humidity?.roundToInt()!!

            binding.realFeel.text = weather.main?.feels_like?.let { convertKelvinToCelsius(it) }
            binding.progressRealFeel.progress =
                weather.main?.feels_like?.minus(KELVIN_TO_CELSIUS)?.roundToInt()!!

            binding.wind.text =
                weather.wind?.speed?.times(METER_PER_SECOND_TO_KILOMETER_PER_HOUR)?.roundToInt()
                    ?.toString() + " km/h"
            binding.progressWind.progress = weather.wind?.speed?.times(
                METER_PER_SECOND_TO_KILOMETER_PER_HOUR
            )?.roundToInt()!!

            binding.cloudness.text = weather.clouds?.all?.roundToInt()?.toString() + "%"
            binding.progressCloudness.progress = weather.clouds?.all?.roundToInt()!!

        })

        dayRecyclerView = binding.bottom
        homeViewModel.listDayWeather.observe(viewLifecycleOwner, Observer {
            dayAdapter = CardAdapter(it)
            dayRecyclerView.adapter = dayAdapter
        })

        dayRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        return binding.root
    }

    private fun convertKelvinToCelsius(temp: Float): String {
        return (temp.minus(KELVIN_TO_CELSIUS)).roundToInt().toString() + "°C"
    }
}
