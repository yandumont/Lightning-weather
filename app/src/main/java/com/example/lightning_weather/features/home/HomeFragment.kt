package com.example.lightning_weather.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lightning_weather.R
import com.example.lightning_weather.databinding.HomeFragmentBinding
import com.example.lightning_weather.util.ViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: HomeFragmentBinding? = null
    private val binding: HomeFragmentBinding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        instantiateVariables()
        subscribeObservers()
        viewModel.onViewCreated()
    }

    private fun instantiateVariables() {
        viewModel = ViewModelProvider(
            this, ViewModelFactory(requireContext())
        ).get(HomeViewModel::class.java)
    }

    private fun subscribeObservers() {
        viewModel.goToHomeFragment.observe(viewLifecycleOwner, {
//            findNavController().navigate(R.id.action_homeActivity_to_searchFragment)
        })
    }
}
