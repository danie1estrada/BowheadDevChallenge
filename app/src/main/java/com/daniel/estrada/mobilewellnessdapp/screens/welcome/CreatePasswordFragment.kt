package com.daniel.estrada.mobilewellnessdapp.screens.welcome

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.daniel.estrada.mobilewellnessdapp.R
import com.daniel.estrada.mobilewellnessdapp.databinding.FragmentCreatePasswordBinding
import com.daniel.estrada.mobilewellnessdapp.viewmodels.PasswordViewModel
import com.google.android.material.button.MaterialButton


class CreatePasswordFragment : Fragment() {

    private val viewModel: PasswordViewModel by viewModels()
    private lateinit var binding: FragmentCreatePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreatePasswordBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<MaterialButton>(R.id.buttonLetsGo).setOnClickListener {
            findNavController().navigate(R.id.action_createPasswordFragment_to_homeFragment)
        }
    }
}