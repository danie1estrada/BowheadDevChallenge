package com.daniel.estrada.mobilewellnessdapp.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.daniel.estrada.mobilewellnessdapp.repositories.Repository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnswersLogsViewModel(application: Application): AndroidViewModel(application) {
    val answers = MutableLiveData<List<ByteArray>>()
    val isLoading = MutableLiveData<Boolean>(true)
    private val repository = Repository.getInstance(application)

    init {
        loadData()
    }

    private fun loadData() {
        isLoading.value = true

        viewModelScope.launch(Dispatchers.Main) {
            try {
                val data = withContext(Dispatchers.IO) {
                    repository.getHealthData()
                }
                answers.value = data?.map { it as ByteArray }
            } catch (ex: CancellationException) {
                Log.d("ERROR", "$ex")
            } catch(ex: Exception) {
                Toast.makeText(getApplication(), ex.message ?: "An error has occurred", Toast.LENGTH_LONG).show()
            } finally {
                isLoading.value = false
            }
        }
    }
}