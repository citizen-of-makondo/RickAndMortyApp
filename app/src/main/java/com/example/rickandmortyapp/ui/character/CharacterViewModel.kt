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
    var pageNumberCharacterList: Int = 1
    val charactersLiveData = MutableLiveData<LoadingStatus<List<Character>>>()
    var filterMap: ArrayList<Filter> = arrayListOf()
    val loadingLiveData = MutableLiveData<Boolean>()

    init {
        getCharacterList()
    }

    fun getCharacterList() {
        viewModelScope.launch {
            try {
                loadingLiveData.value = true
                getData()
                loadingLiveData.value = false
            } catch (exception: Exception) {
                LoadingStatus.error(data = null, message = exception.message ?: "Error Occurred!")
                getCharacterList()
            }
        }
    }

    private suspend fun getData() {
        val hasNextPage: String? = mainRepository.getCharacters(filterMap).info.nextPageLink
        hasNextPage?.let {
            filterMap.add(Filter.Page(pageNumberCharacterList))
            var oldList: List<Character> = charactersLiveData.value?.data.orEmpty()
            charactersLiveData.value =
                LoadingStatus.success(data = oldList + mainRepository.getCharacters(
                    filterMap).results)
            with(filterMap) {
                remove(Filter.Page(pageNumberCharacterList))
                pageNumberCharacterList++
                filterMap.add(Filter.Page(pageNumberCharacterList))
            }
        }
    }

    fun searchCharactersByName(newText: String) {
        if (newText.length > 2) {
            val queryList: ArrayList<Filter> = arrayListOf(Filter.Name(newText))
            Mapping.sendFilterFromArrayListToMap(queryList)
        } else {
            with(this) {
                filterMap.clear()
                pageNumberCharacterList = 1
                getCharacterList()
            }
        }
    }
}