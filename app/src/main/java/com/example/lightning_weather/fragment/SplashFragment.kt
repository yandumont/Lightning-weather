package com.example.lightning_weather.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SplashFragment {
    private lateinit var binding : SplahsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SplashFragmentlBinding.inflate(inflater)

        return binding.root
    }
}