package com.alexilinskiy.rickandmortyapp.ui.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexilinskiy.rickandmortyapp.data.model.CharacterDTO
import com.alexilinskiy.rickandmortyapp.data.repository.MainRepository
import com.alexilinskiy.rickandmortyapp.model.LoadingStatus
import kotlinx.coroutines.launch
import java.util.*

const val timeOfDelay = 1000L
const val countLetterForSearching = 2

class CharacterViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private val timer = Timer()
    private var countAllPages = 1
    private var pageNumberCharacterList: Int = 1
    private var timerTask: TimerTask? = null
    val charactersLiveData = MutableLiveData<LoadingStatus<List<CharacterDTO>>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    var characterFilterList: ArrayList<CharacterFilter> = arrayListOf()

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
        val data = mainRepository.getCharacters(pageNumberCharacterList, characterFilterList)
        countAllPages = data.episodeCharacterInfo.pages
        var oldList: List<CharacterDTO> = listOf()
        if (pageNumberCharacterList > 1) {
            oldList = charactersLiveData.value?.data as MutableList<CharacterDTO>
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
                    characterFilterList.add(0, element = CharacterFilter.Name(newText))
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