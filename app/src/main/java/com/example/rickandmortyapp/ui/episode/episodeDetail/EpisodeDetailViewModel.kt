package com.example.rickandmortyapp.ui.episode.episodeDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.model.CharacterDTO
import com.example.rickandmortyapp.data.model.EpisodeDTO
import com.example.rickandmortyapp.data.repository.MainRepository
import kotlinx.coroutines.launch

class EpisodeDetailViewModel(
    private val mainRepository: MainRepository,
    episodeID: Int,
) : ViewModel() {
    var episodeDetailLiveData = MutableLiveData<EpisodeDTO>()
    var characterListLiveData = MutableLiveData<List<CharacterDTO>>()
    init {
        getEpisodeDetail(episodeID)
    }

    private fun getEpisodeDetail(episodeID: Int) {
        viewModelScope.launch {
            try {
                val data = mainRepository.getEpisode(episodeID.toString())
                episodeDetailLiveData.value = data
                characterListLiveData.value = mainRepository.getMultipleCharacters(split(data.characters))
            } catch (e: Exception) {
                Log.e("EpisodeDetail", "getEpisodeDetail: ${e.message}")
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