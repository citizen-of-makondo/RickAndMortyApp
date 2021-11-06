package com.alexilinskiy.rickandmortyapp.ui.episode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexilinskiy.rickandmortyapp.data.model.EpisodeDTO
import com.alexilinskiy.rickandmortyapp.data.repository.MainRepository
import com.alexilinskiy.rickandmortyapp.model.LoadingStatus
import kotlinx.coroutines.launch
import java.util.*

class EpisodeViewModel(
    private val mainRepository: MainRepository,
) : ViewModel() {

    private val timer = Timer()
    private var countAllPages = 1
    private var pageNumberEpisodeList: Int = 1
    private var timerTask: TimerTask? = null
    val episodeLiveData = MutableLiveData<LoadingStatus<List<EpisodeDTO>>>()
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
        var oldList: List<EpisodeDTO> = listOf()
        if (pageNumberEpisodeList > 1) {
            oldList = episodeLiveData.value?.data as MutableList<EpisodeDTO>
        }
        episodeLiveData.value =
            LoadingStatus.success(data = oldList + data.results)
        pageNumberEpisodeList++
    }
}