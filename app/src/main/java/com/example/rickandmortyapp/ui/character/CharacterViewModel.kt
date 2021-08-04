package com.example.rickandmortyapp.ui.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.model.CharacterDTO
import com.example.rickandmortyapp.data.repository.MainRepository
import com.example.rickandmortyapp.model.Resource
import kotlinx.coroutines.launch

class CharacterViewModel(private val mainRepository: MainRepository) :
    ViewModel() {
    var countPages = 1
    val liveData = MutableLiveData<Resource<Array<CharacterDTO>>>()

    init {
        getUsers(countPages)
    }

    fun getUsers(countPages: Int) {
        viewModelScope.launch {
            if (countPages <= 34) {
                val oldList = liveData.value?.data ?: arrayOf()
                liveData.value =
                    Resource.success(data = oldList + mainRepository.getCharacters(countPages).results)
                this@CharacterViewModel.countPages++
            }
        }
        try {
            //Resource.success(data = mainRepository.getCharacters(page))
        } catch (exception: Exception) {
            // emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}