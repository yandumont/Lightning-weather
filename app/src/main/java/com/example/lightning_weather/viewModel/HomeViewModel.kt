package com.example.lightning_weather.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lightning_weather.*
import com.example.lightning_weather.interfaceApi.WeatherApi
import com.example.lightning_weather.interfaceApi.WeatherForecastApi
import com.example.lightning_weather.model.Weather
import com.example.lightning_weather.model.day_weather.DayWeatherResponse
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class HomeViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather>
        get() = _weather

    private var _listDayWeather = MutableLiveData<DayWeatherResponse?>()
        .apply { postValue(null) }
    val listDayWeather: LiveData<DayWeatherResponse?>
        get() = _listDayWeather

    init {
        getWeather()
        getWeatherForecast()
    }

    private fun getWeather() {
        viewModelScope.launch {
            try {
                val result =
                    WeatherApi.retrofitService.getCurrentWeatherData(LATITUDE, LONGITUDE, APPID)
                _weather.value = result

                _response.value = "Success!!!"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                Log.i("VM", e.toString())
            }
        }
    }

    private fun getWeatherForecast() {
        viewModelScope.launch {
            try {
                Log.i("Debug", "Long: ${LONGITUDE}, Lat: ${LATITUDE}")
                val result = WeatherForecastApi.weatherForecastService.getWeatherForecast(
                    LATITUDE,
                    LONGITUDE,
                    APPID
                )
                 _listDayWeather.value = result
                _response.value = "Success!!!"
                Log.i("Home VM", "listDayWeather size: ${_listDayWeather.value?.list?.size}")
                Log.i("VM", _response.value!!)
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                Log.i("VM", e.toString())
            }
        }
    }

    private fun String.toDate(
        dateFormat: String = "yyyy-MM-dd HH:mm:ss",
        timeZone: TimeZone = TimeZone.getTimeZone("UTC")
    ): Date {
        val parser = SimpleDateFormat(dateFormat, Locale.TAIWAN)
        parser.timeZone = timeZone
        return parser.parse(this)
    }

    private fun Date.formatTo(
        dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()
    ): String {
        val formatter = SimpleDateFormat(dateFormat, Locale.TAIWAN)
        formatter.timeZone = timeZone
        return formatter.format(this)
    }
}
