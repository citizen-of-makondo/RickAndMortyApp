package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.api.CharacterApiHelper

class MainRepository(private val apiHelper: CharacterApiHelper) {
    suspend fun getCharacters() = apiHelper.getCharacters()
}