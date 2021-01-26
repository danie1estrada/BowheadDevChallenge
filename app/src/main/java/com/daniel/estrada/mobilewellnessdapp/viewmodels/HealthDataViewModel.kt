package com.daniel.estrada.mobilewellnessdapp.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar

class HealthDataViewModel : ViewModel() {

    val feeling = MutableLiveData<String>()
    val didSleepWell = MutableLiveData<String>()

    fun isCompleted(): LiveData<Boolean>? {
        return Transformations.map(feeling) { it.isNotEmpty() }
    }

    fun setFeeling(value: String) {
        feeling.value = value
    }

    fun setDidSleepWell(value: String) {
        didSleepWell.value = value
    }

    fun submit(view: View) {
        Snackbar.make(
            view,
            "FEELING: ${feeling.value}, SLEEP: ${didSleepWell.value}",
            Snackbar.LENGTH_LONG
        ).show()
    }
}