package com.daniel.estrada.mobilewellnessdapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HealthDataViewModel : ViewModel() {

    val feeling = MutableLiveData<String>()
    val didSleepWell = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>(false)

    fun setFeeling(value: String) {
        feeling.value = value
    }

    fun setDidSleepWell(value: String) {
        didSleepWell.value = value
    }

    fun getDataAsBytes(): ByteArray {
        val plainTextData = "${feeling.value} ${didSleepWell.value}"
        val spaces = Array(32 - plainTextData.length) { " " }.joinToString(separator = "")
        return "$plainTextData$spaces".toByteArray()
    }

    fun clear() {
        feeling.value = ""
        didSleepWell.value = ""
    }
}