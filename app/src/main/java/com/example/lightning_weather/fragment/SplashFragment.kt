package com.example.lightning_weather.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lightning_weather.R
import com.example.lightning_weather.databinding.SplashFragmentBinding
import com.google.android.ads.mediationtestsuite.viewmodels.ViewModelFactory

class SplashFragment : Fragment() {

    private var _binding : SplashFragmentBinding? = null
    private val binding: SplashFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SplashFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed(Runnable {
            findNavController().navigate(R.id.action_splashFragment_to_homeActivity)
        }, 2000)
    }
}
