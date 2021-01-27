package com.daniel.estrada.mobilewellnessdapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PasswordViewModel(): ViewModel() {
    val isLoading = MutableLiveData<Boolean>(false)
    val password = MutableLiveData<String>("")
}