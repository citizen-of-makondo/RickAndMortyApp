package com.example.rickandmortyapp.ui.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.rickandmortyapp.data.repository.MainRepository
import com.example.rickandmortyapp.model.Resource
import kotlinx.coroutines.Dispatchers

class CharacterViewModel(private val mainRepository: MainRepository) :
    ViewModel() {
    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getCharacters()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

/*init {
    listCharacter.value =
        List(10) {
            Character(it,
                "Персонаж $it",
                "Unknown",
                "Unknown",
                "Unknown",
                "image",
                ArrayList(),
                "url")
        }
}

fun getMoreData() {
    val list = listCharacter.value.orEmpty().toMutableList()
    val size = list.size
    repeat(10) {
        val character = Character(it + size,
            "Персонаж ${it + size}",
            "Unknown",
            "Unknown",
            "Unknown",
            "image",
            ArrayList(),
            "url")
        list.add(character)
    }
    listCharacter.value = list
}*/
}