package com.example.rickandmortyapp.ui.episode.episodeDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.model.GetEpisodeDetailResponse
import com.example.rickandmortyapp.data.repository.MainRepository
import com.example.rickandmortyapp.model.LoadingStatus
import kotlinx.coroutines.launch

class EpisodeDetailViewModel(
    private val mainRepository: MainRepository,
    episodeID: Int,
) : ViewModel() {
    var episodeDetailLiveData = MutableLiveData<LoadingStatus<GetEpisodeDetailResponse>>()

    init {
        getEpisodeDetail(episodeID)
    }

    private fun getEpisodeDetail(episodeID: Int) {
        viewModelScope.launch {
            try {
                val data = mainRepository.getEposideDetail(episodeID)
                episodeDetailLiveData.value = LoadingStatus.success(data = data)
            } catch (e: Exception) {
                Log.e("EpisodeDetail", "getEpisodeDetail: ${e.message}")
            }
        }
    }
}