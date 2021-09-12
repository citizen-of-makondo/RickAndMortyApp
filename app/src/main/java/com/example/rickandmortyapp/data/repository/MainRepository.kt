package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.model.GetCharacterDetailResponse
import com.example.rickandmortyapp.data.model.GetCharactersResponse
import com.example.rickandmortyapp.data.model.GetEpisodeDetailResponse
import com.example.rickandmortyapp.data.model.GetLocationDetailRespone
import com.example.rickandmortyapp.model.CharacterService
import com.example.rickandmortyapp.ui.character.Filter
import com.example.rickandmortyapp.ui.character.Mapping

class MainRepository(private val apiService: CharacterService) {
    suspend fun getCharacters(pageNumberCharacterList: Int, filter: ArrayList<Filter>): GetCharactersResponse {
        val filterMap = Mapping.mapFilterListToQueryMap(filter)
        return apiService.getCharacterList(pageNumberCharacterList, filterMap)
    }

    suspend fun getCharacterDetail(id: Int): GetCharacterDetailResponse =
        apiService.getCharacter(id)

    suspend fun getLocationDetail(id: Int): GetLocationDetailRespone = apiService.getLocation(id)

    suspend fun getEposideDetail(id: Int): GetEpisodeDetailResponse = apiService.getEpisode(id)
}