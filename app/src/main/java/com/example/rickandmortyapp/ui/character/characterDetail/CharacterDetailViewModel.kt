package com.example.rickandmortyapp.ui.character.characterDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.model.GetCharacterDetailResponse
import com.example.rickandmortyapp.data.repository.MainRepository
import com.example.rickandmortyapp.model.LoadingStatus
import kotlinx.coroutines.launch

class CharacterDetailViewModel constructor(private val mainRepository: MainRepository, val characterID: Int) :
    ViewModel() {
    var characterDetailLiveData = MutableLiveData<LoadingStatus<GetCharacterDetailResponse>>()

    init {
        getCharacterDetail(characterID)
    }

    fun getCharacterDetail(characterID: Int) {
        viewModelScope.launch {
            try {
                val data = mainRepository.getCharacterDetail(characterID)
                characterDetailLiveData.value = LoadingStatus.success(data = data)
            } catch (e: Exception) {
                Log.e("Detail", "getCharacterDetail: ${e.message}")
            }
        }
    }
}
