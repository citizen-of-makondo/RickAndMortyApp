package com.alexilinskiy.rickandmortyapp.ui.location.locationDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexilinskiy.rickandmortyapp.data.model.CharacterDTO
import com.alexilinskiy.rickandmortyapp.data.model.LocationDTO
import com.alexilinskiy.rickandmortyapp.data.repository.MainRepository
import kotlinx.coroutines.launch

class LocationDetailViewModel constructor(
    private val mainRepository: MainRepository,
    locationID: Int,
) : ViewModel() {
    var locationDetailLiveData = MutableLiveData<LocationDTO>()
    var characterListLiveData = MutableLiveData<List<CharacterDTO>>()

    init {
        getLocationDetail(locationID)
    }

    private fun getLocationDetail(locationID: Int) {
        viewModelScope.launch {
            try {
                val data = mainRepository.getLocationDetail(locationID)
                locationDetailLiveData.value = data
                val dataA = mainRepository.getMultipleCharacters(split(data.characters))
                characterListLiveData.value = dataA
            } catch (e: Exception) {
                Log.e("LocationDetail", "getLocationDetail: ${e.message}")
            }
        }
    }

    private fun split(value: List<String>): String {
        val stringBuilder = StringBuilder()
        value.forEach { s: String ->
            stringBuilder.append(s.split("/").last())
            stringBuilder.append(",")
        }
        return stringBuilder.toString()
    }
}