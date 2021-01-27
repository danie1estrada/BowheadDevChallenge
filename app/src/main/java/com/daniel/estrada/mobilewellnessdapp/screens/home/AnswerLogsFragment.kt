package com.daniel.estrada.mobilewellnessdapp.screens.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.daniel.estrada.mobilewellnessdapp.adapters.AnswerLogsRecyclerViewAdapter
import com.daniel.estrada.mobilewellnessdapp.data.Repository
import com.daniel.estrada.mobilewellnessdapp.databinding.FragmentAnswerLogsBinding
import com.daniel.estrada.mobilewellnessdapp.viewmodels.AnswersLogsViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnswerLogsFragment : Fragment() {

    private lateinit var binding: FragmentAnswerLogsBinding
    private val viewModel: AnswersLogsViewModel by viewModels()
    private val logsAdapter = AnswerLogsRecyclerViewAdapter()
    private lateinit var repository: Repository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnswerLogsBinding.inflate(inflater, container, false)
        setup()
        loadData()
        return binding.root
    }

    private fun setup() {
        repository = Repository.getInstance(requireActivity().application)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        binding.buttonBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.recyclerViewLogs.apply {
            adapter = logsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.answers.observe(viewLifecycleOwner, Observer {
            logsAdapter.healthData = it
            logsAdapter.notifyDataSetChanged()
        })
    }

    private fun loadData() {
        viewModel.isLoading.value = true

        lifecycleScope.launch(Dispatchers.Main) {
            try {
                val data = withContext(Dispatchers.IO) {
                    repository.getHealthData()
                }
                viewModel.answers.value = data?.map { it as ByteArray }
            } catch (ex: CancellationException) {
                Log.d("ERROR", "$ex")
            } catch(ex: Exception) {
                Toast.makeText(context, ex.message ?: "An error has occurred", Toast.LENGTH_LONG).show()
            } finally {
                viewModel.isLoading.value = false
            }
        }
    }

}