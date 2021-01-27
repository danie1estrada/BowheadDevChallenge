package com.daniel.estrada.mobilewellnessdapp.screens.welcome

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.daniel.estrada.mobilewellnessdapp.R
import com.daniel.estrada.mobilewellnessdapp.data.Repository
import com.daniel.estrada.mobilewellnessdapp.databinding.FragmentCreatePasswordBinding
import com.daniel.estrada.mobilewellnessdapp.viewmodels.PasswordViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import org.web3j.crypto.WalletUtils


class CreatePasswordFragment : Fragment() {

    private val viewModel: PasswordViewModel by viewModels()
    private lateinit var binding: FragmentCreatePasswordBinding
    private lateinit var filePath: String
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreatePasswordBinding.inflate(inflater, container, false)
        setup()
        return binding.root
    }

    private fun setup() {
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        binding.buttonLetsGo.setOnClickListener {
            createWallet()
        }

        repository = Repository.getInstance(requireActivity().application)
    }

    private fun createWallet() {
        viewModel.isLoading.value = true

        lifecycleScope.launch(Dispatchers.Main) {
            try {
                withContext(Dispatchers.IO) {
                    repository.createWallet(viewModel.password.value!!)
                }

                sendFundsAndRegister()
                findNavController().navigate(R.id.action_createPasswordFragment_to_homeFragment)
            } catch (ex: CancellationException) {
                Log.d("ERROR", ex.message!!)
            } catch (ex: Exception) {
                Toast.makeText(context, "$ex", Toast.LENGTH_LONG).show()
                Log.d("ERROR", ex.message!!)
            } finally {
                viewModel.isLoading.value = false
            }
        }
    }

    private suspend fun sendFundsAndRegister() {
        val foundsReceipt = withContext(Dispatchers.IO) {
            repository.sendFunds()
        }
        Log.d("TRANSACTION COMPLETED", foundsReceipt?.transactionHash ?: "FAILED")

        val receipt = withContext(Dispatchers.IO) {
            repository.registerUser()
        }
        Log.d("USER REGISTERED", receipt?.transactionHash ?: "FAILED")
    }
}