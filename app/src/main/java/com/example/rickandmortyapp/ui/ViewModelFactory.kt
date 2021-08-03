package com.example.rickandmortyapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyapp.data.api.CharacterApiHelper
import com.example.rickandmortyapp.data.repository.MainRepository
import com.example.rickandmortyapp.ui.character.CharacterViewModel

class ViewModelFactory(private val apiHelper: CharacterApiHelper) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            return CharacterViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}