package com.example.rickandmortyapp.ui.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.data.repository.MainRepository
import com.example.rickandmortyapp.model.LoadingStatus
import kotlinx.coroutines.launch
import java.util.*

const val timeOfDelay = 1000L
const val countLetterForSearching = 2

class CharacterViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private val timer = Timer()
    private var countAllPages = 1
    private var pageNumberCharacterList: Int = 1
    private var timerTask: TimerTask? = null
    val charactersLiveData = MutableLiveData<LoadingStatus<List<Character>>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    var filterList: ArrayList<Filter> = arrayListOf()

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
        if (pageNumberCharacterList > countAllPages) {
            return
        }
        val data = mainRepository.getCharacters(pageNumberCharacterList, filterList)
        countAllPages = data.info.pages
        var oldList: List<Character> = listOf()
        if (pageNumberCharacterList > 1) {
            oldList = charactersLiveData.value?.data as MutableList<Character>
        }
        charactersLiveData.value =
            LoadingStatus.success(data = oldList + data.results)
        pageNumberCharacterList++
    }

    fun searchCharactersByName(newText: String) {
        if (newText.length > countLetterForSearching) {
            timerTask?.cancel()
            timerTask = object : TimerTask() {
                override fun run() {
                    filterList.add(0, element = Filter.Name(newText))
                    setPageAndGetData()
                }
            }
            timer.schedule(timerTask, timeOfDelay)
            timer.purge()
        }
    }

    fun setPageAndGetData() {
        pageNumberCharacterList = 1
        getCharacterList()
    }
}