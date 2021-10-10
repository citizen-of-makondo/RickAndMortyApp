package com.example.rickandmortyapp.ui.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.model.LocationDTO
import com.example.rickandmortyapp.data.repository.MainRepository
import com.example.rickandmortyapp.model.LoadingStatus
import kotlinx.coroutines.launch
import java.util.*

class LocationViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private val timer = Timer()
    private var countAllPages = 1
    private var pageNumberLocationList: Int = 1
    private var timerTask: TimerTask? = null
    val locationsLiveData = MutableLiveData<LoadingStatus<List<LocationDTO>>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    //var locationFilterList: ArrayList<CharacterFilter> = arrayListOf()

    init {
        getLocationList()
    }

    fun getLocationList() {
        viewModelScope.launch {
            try {
                loadingLiveData.value = true
                getData()
                loadingLiveData.value = false
            } catch (exception: Exception) {
                LoadingStatus.error(data = null, message = exception.message ?: "Error Occurred!")
            }
        }
    }

    private suspend fun getData() {
        if (pageNumberLocationList > countAllPages) {
            return
        }
        val data = mainRepository.getLocations(pageNumberLocationList, /*characterFilterList*/)
        countAllPages = data.info.pages
        var oldList: List<LocationDTO> = listOf()
        if (pageNumberLocationList > 1) {
            oldList = locationsLiveData.value?.data as  List<LocationDTO>
        }
        locationsLiveData.value =
            LoadingStatus.success(data = oldList + data.results)
        pageNumberLocationList++
    }
}