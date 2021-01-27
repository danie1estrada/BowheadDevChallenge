package com.daniel.estrada.mobilewellnessdapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniel.estrada.mobilewellnessdapp.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnswersLogsViewModel: ViewModel() {
    val answers = MutableLiveData<List<ByteArray>>()
    val isLoading = MutableLiveData<Boolean>(true)
}