package com.daniel.estrada.mobilewellnessdapp.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.daniel.estrada.mobilewellnessdapp.adapters.AnswerLogsRecyclerViewAdapter
import com.daniel.estrada.mobilewellnessdapp.databinding.FragmentAnswerLogsBinding
import com.daniel.estrada.mobilewellnessdapp.viewmodels.AnswersLogsViewModel

class AnswerLogsFragment : Fragment() {

    private lateinit var binding: FragmentAnswerLogsBinding
    private val viewModel: AnswersLogsViewModel by viewModels()
    private val logsAdapter = AnswerLogsRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnswerLogsBinding.inflate(inflater, container, false)
        setup()
        return binding.root
    }

    private fun setup() {
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

}