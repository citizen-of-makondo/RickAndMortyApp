package com.example.rickandmortyapp.ui.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.model.CharacterDTO
import com.example.rickandmortyapp.data.repository.MainRepository
import com.example.rickandmortyapp.model.Resource
import kotlinx.coroutines.launch

class CharacterViewModel(val mainRepository: MainRepository) :
    ViewModel() {
    var countPages: Int = 1
    val charactersLiveData = MutableLiveData<Resource<Array<CharacterDTO>>>()

    var loadingLiveData = MutableLiveData<Boolean>()

    init {
        loadingLiveData.value = true
        getUsers(countPages)
    }

    fun getUsers(countPages: Int) {
        viewModelScope.launch {
            try {
                this@CharacterViewModel.countPages++
                loadingLiveData.value = true
                val oldList = charactersLiveData.value?.data ?: arrayOf()
                charactersLiveData.value =
                    Resource.success(data = oldList + mainRepository.getCharacters(countPages).results)
                loadingLiveData.value = false
            } catch (exception: Exception) {
                Resource.error(data = null, message = exception.message ?: "Error Occurred!")
            }
        }
    }
}