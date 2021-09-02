package com.example.lightning_weather.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import com.example.lightning_weather.features.home.api.model.HomeResponse
import com.example.lightning_weather.util.SharedPref

class HomeViewModel(instance: SharedPref) : ViewModel() {
    private val _temperature = MutableLiveData<HomeResponse.Temperature>()
    val temperature: LiveData<HomeResponse.Temperature> = _temperature
    val goToHomeFragment = MutableLiveData<Unit>()

    fun onViewCreated() {
        goToHomeFragment.postValue(Unit)
    }
}
