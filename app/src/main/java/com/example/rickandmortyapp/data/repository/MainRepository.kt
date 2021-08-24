package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.model.GetCharactersResponse
import com.example.rickandmortyapp.model.CharacterService
import com.example.rickandmortyapp.ui.character.Filter
import com.example.rickandmortyapp.ui.character.Mapping

class MainRepository(private val apiService: CharacterService) {
    suspend fun getCharacters(filter: ArrayList<Filter>): GetCharactersResponse {
        val filterMap = Mapping.mappingArrayListToMap(filter)
        return apiService.getCharacterList(filterMap)
    }
}