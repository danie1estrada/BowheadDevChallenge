package com.daniel.estrada.mobilewellnessdapp.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.daniel.estrada.mobilewellnessdapp.R
import com.daniel.estrada.mobilewellnessdapp.databinding.FragmentWellnessTrackingBinding
import com.daniel.estrada.mobilewellnessdapp.viewmodels.HealthDataViewModel


class WellnessTrackingFragment : Fragment() {

    private val viewModel: HealthDataViewModel by viewModels()
    private lateinit var binding: FragmentWellnessTrackingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWellnessTrackingBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}