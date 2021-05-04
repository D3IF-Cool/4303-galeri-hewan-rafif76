package org.d3if4080.galerihewan.ui.main

import ApiStatus
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.d3if4080.galerihewan.R
import org.d3if4080.galerihewan.model.Hewan

class MainViewModel : ViewModel() {
    private val data = MutableLiveData<List<Hewan>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch {
            status.value = ApiStatus.LOADING
            try {
                data.value = HewanApi.service.getHewan()
                status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value = ApiStatus.FAILED


            }
        }
    }
    fun getStatus(): LiveData<ApiStatus> = status
    // Data ini akan kita ambil dari server di langkah selanjutnya

    fun getData(): LiveData<List<Hewan>> = data
}