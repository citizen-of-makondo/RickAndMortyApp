package com.example.rickandmortyapp.data.api

import com.example.rickandmortyapp.model.CharacterService

class CharacterApiHelper(private val apiService: CharacterService) {
    suspend fun getCharacters() = apiService.getCharacterList()
}