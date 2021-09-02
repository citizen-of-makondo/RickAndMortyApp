package com.example.rickandmortyapp.ui.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.data.repository.MainRepository
import com.example.rickandmortyapp.model.LoadingStatus
import kotlinx.coroutines.launch
import java.util.*

class CharacterViewModel(private val mainRepository: MainRepository) : ViewModel() {
    var pageNumberCharacterList: Int = 1
    val charactersLiveData = MutableLiveData<LoadingStatus<List<Character>>>()
    var filterList: ArrayList<Filter> = arrayListOf()
    val loadingLiveData = MutableLiveData<Boolean>()
    private var countAllPages = 1
    private var isNeedSafeOldList = false
    private val timer = Timer()

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
        if (isNeedSafeOldList) {
            oldList = charactersLiveData.value?.data as MutableList<Character>
        }
        charactersLiveData.value =
            LoadingStatus.success(data = oldList + data.results)
        isNeedSafeOldList = true
        pageNumberCharacterList++
    }

    var timerTask: TimerTask? = null

    fun searchCharactersByName(newText: String) {
        if (newText.length > 2) {
            filterList.add(0, element = Filter.Name(newText))
            timerTask?.cancel()
            timerTask = object : TimerTask() {
                override fun run() {
                    setPageAndGetData()
                }
            }
            timer.schedule(timerTask, 1000)
            timer.purge()
        }
    }

    fun setPageAndGetData() {
        pageNumberCharacterList = 1
        isNeedSafeOldList = false
        getCharacterList()
    }
}