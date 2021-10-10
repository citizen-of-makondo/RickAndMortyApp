package com.example.rickandmortyapp.ui.episode.episodeDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.model.Episode
import com.example.rickandmortyapp.data.repository.MainRepository
import kotlinx.coroutines.launch

class EpisodeDetailViewModel(
    private val mainRepository: MainRepository,
    episodeID: Int,
) : ViewModel() {
    var episodeDetailLiveData = MutableLiveData<Episode>()

    init {
        getEpisodeDetail(episodeID)
    }

    private fun getEpisodeDetail(episodeID: Int) {
        viewModelScope.launch {
            try {
                val data = mainRepository.getEpisodesDetail(episodeID.toString())
             //   episodeDetailLiveData.value = data
            } catch (e: Exception) {
                Log.e("EpisodeDetail", "getEpisodeDetail: ${e.message}")
            }
        }
    }
}