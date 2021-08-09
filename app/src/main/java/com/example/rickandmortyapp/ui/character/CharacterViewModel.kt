package com.example.rickandmortyapp.ui.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.data.repository.MainRepository
import com.example.rickandmortyapp.model.LoadingStatus
import kotlinx.coroutines.launch

class CharacterViewModel(val mainRepository: MainRepository) :
    ViewModel() {
    private var pageNumber: Int = 1
    val charactersLiveData = MutableLiveData<LoadingStatus<List<Character>>>()

    val loadingLiveData = MutableLiveData<Boolean>()

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            try {
                loadingLiveData.value = true
                val oldList = charactersLiveData.value?.data.orEmpty()
                charactersLiveData.value =
                    LoadingStatus.success(data = oldList + mainRepository.getCharacters(pageNumber).results)
                pageNumber++
                loadingLiveData.value = false
            } catch (exception: Exception) {
                LoadingStatus.error(data = null, message = exception.message ?: "Error Occurred!")
            }
        }
    }
}