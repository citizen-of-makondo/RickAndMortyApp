package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.model.GetCharactersResponse
import com.example.rickandmortyapp.model.CharacterService

class MainRepository(private val apiService: CharacterService) {
    suspend fun getCharacters(filterMap: MutableMap<String, String>): GetCharactersResponse =
        apiService.getCharacterList(filterMap)
}