package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.model.DataDTO
import com.example.rickandmortyapp.model.CharacterService

class MainRepository(private val apiService: CharacterService) {
    suspend fun getCharacters(page: Int): DataDTO = apiService.getCharacterList(page)
}