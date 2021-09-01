package com.example.lightning_weather.features.intro.splash

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.lightning_weather.util.SharedPref

class SplashViewModel(private val sharedPref: SharedPref) : ViewModel(){

    val goToHome = MutableLiveData<Unit>()

    fun onViewCreated() {
        goToHome.postValue(Unit)
    }
}