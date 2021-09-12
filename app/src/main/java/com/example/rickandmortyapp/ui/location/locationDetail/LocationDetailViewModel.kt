package com.example.rickandmortyapp.ui.location.locationDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.model.GetLocationDetailRespone
import com.example.rickandmortyapp.data.repository.MainRepository
import com.example.rickandmortyapp.model.LoadingStatus
import kotlinx.coroutines.launch

class LocationDetailViewModel constructor(
    private val mainRepository: MainRepository,
    locationID: Int,
) : ViewModel() {
    var locationDetailLiveData = MutableLiveData<LoadingStatus<GetLocationDetailRespone>>()

    init {
        getLocationDetail(locationID)
    }

    private fun getLocationDetail(locationID: Int) {
        viewModelScope.launch {
            try {
                val data = mainRepository.getLocationDetail(locationID)
                locationDetailLiveData.value = LoadingStatus.success(data = data)
            } catch (e: Exception) {
                Log.e("LocationDetail", "getLocationDetail: ${e.message}")
            }
        }
    }
}