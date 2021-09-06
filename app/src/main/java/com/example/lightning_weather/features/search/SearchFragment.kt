package com.example.lightning_weather.features.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.lightning_weather.databinding.SearchFragmentBinding
import android.widget.TextView

import android.widget.EditText

class SearchFragment : Fragment(){

    private var _binding: SearchFragmentBinding? = null
    private val binding: SearchFragmentBinding get() = _binding!!

    private val tvCidade: TextView? = null
    private val etCidade: EditText? = null
    private val tvTemp: TextView? = null
    private val btnParserGSON: Button? = null
    private val btnParserJObject: Button? = null

    private val restAdapter: RestAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root

        restAdapter = Builder()
            .setEndpoint(
                "http://api.openweathermap.org" +
                        "/data/2.5/"
            ).build()
    }
}
