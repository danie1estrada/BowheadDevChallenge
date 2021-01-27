package com.daniel.estrada.mobilewellnessdapp.viewmodels

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.daniel.estrada.mobilewellnessdapp.repositories.Repository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HealthDataViewModel(application: Application) : AndroidViewModel(application) {

    val feeling = MutableLiveData<String>()
    val didSleepWell = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>(false)
    private val repository = Repository.getInstance(application)

    fun setFeeling(value: String) {
        feeling.value = value
    }

    fun setDidSleepWell(value: String) {
        didSleepWell.value = value
    }

    private fun getDataAsBytes(): ByteArray {
        val plainTextData = "${feeling.value} ${didSleepWell.value}"
        val spaces = Array(32 - plainTextData.length) { " " }.joinToString(separator = "")
        return "$plainTextData$spaces".toByteArray()
    }

    fun addHealthData(view: View) {
        isLoading.value = true

        viewModelScope.launch(Dispatchers.Main) {
            try {
                withContext(Dispatchers.IO) {
                    repository.addHealthData(getDataAsBytes())
                }
                clear()
                Snackbar.make(view, "Data registered!", Snackbar.LENGTH_LONG).show()
            } catch (ex: CancellationException) {
                Log.d("ERROR", ex.message!!)
            } catch (ex: Exception) {
                Log.d("ADD HEALTH DATA", ex.message!!)
                Snackbar.make(view, "$ex", Snackbar.LENGTH_INDEFINITE).show()
            } finally {
                isLoading.value = false
            }
        }
    }

    private fun clear() {
        feeling.value = ""
        didSleepWell.value = ""
    }
}