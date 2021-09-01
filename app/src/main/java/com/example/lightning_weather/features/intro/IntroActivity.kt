package com.example.lightning_weather.features.intro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.lightning_weather.R

class IntroActivity : AppCompatActivity() {

    private lateinit var binding :IntroActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = IntroActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}