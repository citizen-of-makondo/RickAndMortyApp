package com.example.rickandmortyapp.ui.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.data.repository.MainRepository
import com.example.rickandmortyapp.model.LoadingStatus
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule

class CharacterViewModel(val mainRepository: MainRepository) :
    ViewModel() {
    var pageNumberCharacterList: Int = 1
    val charactersLiveData = MutableLiveData<LoadingStatus<List<Character>>>()
    var filterList: ArrayList<Filter> = arrayListOf()
    val loadingLiveData = MutableLiveData<Boolean>()
    private var countAllPages = 1

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
            }
        }
    }

    private suspend fun getData() {
        if (pageNumberCharacterList <= countAllPages) {
            val data = mainRepository.getCharacters(filterList)
            countAllPages = data.info.pages
            var oldList: List<Character> = listOf()
            if (pageNumberCharacterList > 1) {
                oldList = charactersLiveData.value?.data.orEmpty()
            }
            charactersLiveData.value =
                LoadingStatus.success(data = oldList + data.results)
            with(filterList) {
                remove(Filter.Page(pageNumberCharacterList))
                filterList.add(Filter.Page(++pageNumberCharacterList))
            }
        }
    }

    fun searchCharactersByName(newText: String) {
        if (newText.length > 2) {
            filterList.remove(Filter.Name(newText))
            Timer("SearchingByName", false).schedule(1000) {
                pageNumberCharacterList = 1
                with(filterList) {
                    remove(Filter.Page(pageNumberCharacterList))
                    add(Filter.Name(newText))
                    add(Filter.Page(1))
                }
                getCharacterList()
            }
        } else {
            filterList.clear()
            pageNumberCharacterList = 1
            getCharacterList()
        }
    }

    fun setPageAndGetData() {
        pageNumberCharacterList = 1
        getCharacterList()
    }
}