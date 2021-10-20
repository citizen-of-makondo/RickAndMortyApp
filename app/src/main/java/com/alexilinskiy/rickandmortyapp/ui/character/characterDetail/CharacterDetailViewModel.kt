package com.alexilinskiy.rickandmortyapp.ui.character.characterDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexilinskiy.rickandmortyapp.data.model.CharacterDTO
import com.alexilinskiy.rickandmortyapp.data.model.EpisodeDTO
import com.alexilinskiy.rickandmortyapp.data.repository.MainRepository
import kotlinx.coroutines.launch

class CharacterDetailViewModel constructor(
    private val mainRepository: MainRepository,
    characterID: Int,
) :
    ViewModel() {
    var characterDetailLiveData = MutableLiveData<CharacterDTO>()
    val imageURL = MutableLiveData<String>()
    var episodeListLiveData = MutableLiveData<List<EpisodeDTO>>()

    init {
        getCharacterDetail(characterID)
    }

    private fun getCharacterDetail(characterID: Int) {
        viewModelScope.launch {
            try {
                val data = mainRepository.getCharacterDetail(characterID)
                characterDetailLiveData.value = data
                imageURL.value = data.image
                episodeListLiveData.value = mainRepository.getMultipleEpisodes(split(data.episode))
            } catch (e: Exception) {
                Log.e("CharacterDetail", "getCharacterDetail: ${e.message}")
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
