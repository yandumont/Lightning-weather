package com.example.lightning_weather.features.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lightning_weather.R
import com.example.lightning_weather.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}