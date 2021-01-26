package com.daniel.estrada.mobilewellnessdapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PasswordViewModel: ViewModel() {
    val password = MutableLiveData<String>()
}