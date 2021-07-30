package com.example.rickandmortyapp.ui.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapp.model.Character

class CharacterViewModel : ViewModel() {
    var listCharacter = MutableLiveData<List<Character>>()

    init {
        listCharacter.value =
            List(10) { Character(it, "Персонаж $it", "Unknown", "Unknown", "PathImage") }
    }

    fun getMoreData() {
        val list = listCharacter.value.orEmpty().toMutableList()
        val size = list.size
        repeat(10) {
            val character = Character(it + size,
                "Персонаж ${it + size}",
                "Unknown",
                "Unknown",
                "PathImage")
            list.add(character)
        }
        listCharacter.value = list
    }
}