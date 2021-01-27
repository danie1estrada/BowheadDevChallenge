package com.daniel.estrada.mobilewellnessdapp.screens.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.daniel.estrada.mobilewellnessdapp.R
import com.daniel.estrada.mobilewellnessdapp.data.Repository
import com.daniel.estrada.mobilewellnessdapp.databinding.FragmentWellnessTrackingBinding
import com.daniel.estrada.mobilewellnessdapp.viewmodels.HealthDataViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class WellnessTrackingFragment : Fragment() {

    private val viewModel: HealthDataViewModel by viewModels()
    private lateinit var binding: FragmentWellnessTrackingBinding
    private lateinit var repository: Repository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWellnessTrackingBinding.inflate(inflater, container, false)
        setup()
        return binding.root
    }

    private fun setup() {
        repository = Repository.getInstance(requireActivity().application)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        binding.buttonSubmit.setOnClickListener {
            addHealthData(it)
        }
    }

    private fun addHealthData(view: View) {
        viewModel.isLoading.value = true

        lifecycleScope.launch(Dispatchers.Main) {
            try {
                withContext(Dispatchers.IO) {
                    repository.addHealthData(viewModel.getDataAsBytes())
                }
                viewModel.clear()
                Snackbar.make(view, "Data registered!", Snackbar.LENGTH_LONG).show()
            } catch (ex: CancellationException) {
                Log.d("ERROR", ex.message!!)
            } catch (ex: Exception) {
                Log.d("ADD HEALTH DATA", ex.message!!)
                Snackbar.make(view, "$ex", Snackbar.LENGTH_INDEFINITE).show()
            } finally {
                viewModel.isLoading.value = false
            }
        }
    }
}