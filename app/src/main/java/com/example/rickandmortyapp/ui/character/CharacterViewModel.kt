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
    var filterMap: MutableMap<String, String> = mutableMapOf()
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
        val hasNextPage: String? = mainRepository.getCharacters(filterMap).info.hasNext
        hasNextPage?.let {
            filterMap["page"] = pageNumberCharacterList.toString()
            var oldList: List<Character> = listOf()
            if (pageNumberCharacterList > 1) {
                oldList = charactersLiveData.value?.data.orEmpty()
            }
            charactersLiveData.value =
                LoadingStatus.success(data = oldList + mainRepository.getCharacters(
                    filterMap).results)
            with(filterMap) {
                remove("page")
                pageNumberCharacterList++
                put("page", "$pageNumberCharacterList")
            }
        }
    }

    fun searchCharactersByName(newText: String) {
        if (newText.length > 2) {
            val queryList: ArrayList<Filter> = arrayListOf()
            queryList.add(Filter.Name(newText))
            SendFilterFromArrayListToMap.sendFilterFromArrayListToMap(queryList, this)
        } else {
            with(this) {
                filterMap.clear()
                pageNumberCharacterList = 1
                getCharacterList()
            }
        }
    }
}