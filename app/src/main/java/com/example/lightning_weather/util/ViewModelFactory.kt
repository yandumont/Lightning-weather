package com.example.lightning_weather.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.example.lightning_weather.features.intro.splash.SplashViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == SplashViewModel::class.java) {
            return providerSplashViewModel() as T
        } else {
            throw Exception("View Model não definido")
        }
    }

    private fun providerSplashViewModel(): SplashViewModel {
        return SplashViewModel(SharedPref.getInstance(context))
    }

}