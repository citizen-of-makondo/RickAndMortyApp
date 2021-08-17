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
    private var pageNumberCharacterList: Int = 1
    var pageNumberFilteredCharacterList: Int = 1
    val charactersLiveData = MutableLiveData<LoadingStatus<List<Character>>>()
    var filterMap: MutableMap<String, String> = mutableMapOf()
    val loadingLiveData = MutableLiveData<Boolean>()
    private var countAllPages = 1

    init {
        getCharacterList()
    }

    fun getCharacterList() {
        viewModelScope.launch {
            try {
                if (filterMap.isNullOrEmpty() && pageNumberCharacterList <= countAllPages) {
                    with(pageNumberCharacterList) {
                        getCountPages(this)
                        getData(this)
                    }
                } else {
                    if (pageNumberFilteredCharacterList <= countAllPages) {
                        with(filterMap) {
                            getCountPages(this)
                            getData(this)
                        }
                    }
                }
            } catch (exception: Exception) {
                LoadingStatus.error(data = null, message = exception.message ?: "Error Occurred!")
                getCharacterList()
            }
        }
    }

    private suspend fun getCountPages(query: Any) {
        countAllPages = when (query) {
            is MutableMap<*, *> -> mainRepository.getPages(filterMap).info.pages
            else -> mainRepository.getPages(pageNumberCharacterList).info.pages
        }
    }

    private suspend fun getData(option: Any) {
        loadingLiveData.value = true
        when (option) {
            is Int -> {
                val oldList: List<Character> = charactersLiveData.value?.data.orEmpty()
                charactersLiveData.value =
                    LoadingStatus.success(data = oldList + mainRepository.getCharacters(
                        pageNumberCharacterList).results)
                pageNumberCharacterList++
            }
            is MutableMap<*, *> -> {
                filterMap["page"] = pageNumberFilteredCharacterList.toString()
                var oldList: List<Character> = listOf()
                if (pageNumberFilteredCharacterList > 1) {
                    oldList = charactersLiveData.value?.data.orEmpty()
                }
                charactersLiveData.value =
                    LoadingStatus.success(data = oldList + mainRepository.getFilteredCharacters(
                        filterMap).results)
                with(filterMap) {
                    remove("page")
                    pageNumberFilteredCharacterList++
                    put("page", "$pageNumberFilteredCharacterList")
                }
            }
        }
        loadingLiveData.value = false
    }
}