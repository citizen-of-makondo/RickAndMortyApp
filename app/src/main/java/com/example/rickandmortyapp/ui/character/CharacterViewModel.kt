package com.example.rickandmortyapp.ui.character

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.model.Character

class CharacterViewModel : ViewModel() {
    var listCharacter: MutableLiveData<List<Character>> = MutableLiveData()

    init {
        listCharacter.value =
            List(10) { Character(it, "Персонаж $it", "Unknown", "Unknown", "PathImage") }
    }

    fun getMoreData() {
        val list = listCharacter.value.orEmpty().toMutableList()
        repeat(10) {
            val character = Character(it + listCharacter.value!!.size,
                "Персонаж ${it + listCharacter.value!!.size}",
                "Unknown",
                "Unknown",
                "PathImage")
            list.add(character)
        }
        listCharacter.value = list
    }

    fun filterCharacterNavigation(requireActivity: FragmentActivity) {
        Navigation.findNavController(requireActivity, R.id.nav_host_fragment_activity_main)
            .navigate(CharacterFragmentDirections.actionNavigationCharacterToCharacterFilterFragment())
    }
}