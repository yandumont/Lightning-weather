package com.example.lightning_weather.features.intro.splash

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lightning_weather.R
import com.example.lightning_weather.databinding.SplashFragmentBinding
import com.example.lightning_weather.util.ViewModelFactory

class SplashFragment : Fragment() {

    private var _binding : SplashFragmentBinding? = null
    private val binding: SplashFragmentBinding get() = _binding!!
    private lateinit var viewModel: SplashViewModel

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

        viewModel = ViewModelProvider(this, ViewModelFactory(requireContext())).get(SplashViewModel::class.java)
        subscribeObservers()
        Handler().postDelayed(Runnable {
            viewModel.onViewCreated()
        }, 2000)

    }

    private fun subscribeObservers(){
        viewModel.goToHome.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_splashFragment_to_homeActivity)
        })
    }
}