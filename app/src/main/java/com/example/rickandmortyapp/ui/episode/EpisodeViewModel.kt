package com.example.rickandmortyapp.ui.episode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.model.Episode
import com.example.rickandmortyapp.data.repository.MainRepository
import com.example.rickandmortyapp.model.LoadingStatus
import kotlinx.coroutines.launch
import java.util.*

class EpisodeViewModel(
    private val mainRepository: MainRepository,
) : ViewModel() {

    private val timer = Timer()
    private var countAllPages = 1
    private var pageNumberEpisodeList: Int = 1
    private var timerTask: TimerTask? = null
    val episodeLiveData = MutableLiveData<LoadingStatus<List<Episode>>>()
    val loadingLiveData = MutableLiveData<Boolean>()
  //  var characterFilterList: ArrayList<CharacterFilter> = arrayListOf()

    init {
        getEpisodeList()
    }

    fun getEpisodeList() {
        viewModelScope.launch {
            try {
                loadingLiveData.value = true
                getData()
                loadingLiveData.value = false
            } catch (exception: Exception) {
                LoadingStatus.error(data = null,
                    message = exception.message ?: "Error Occurred!")
            }
        }
    }

    private suspend fun getData() {
        if (pageNumberEpisodeList > countAllPages) {
            return
        }
        val data = mainRepository.getEpisodes(pageNumberEpisodeList)
        countAllPages = data.info.pages
        var oldList: List<Episode> = listOf()
        if (pageNumberEpisodeList > 1) {
            oldList = episodeLiveData.value?.data as MutableList<Episode>
        }
        episodeLiveData.value =
            LoadingStatus.success(data = oldList + data.results)
        pageNumberEpisodeList++
    }
}