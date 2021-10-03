package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.model.*
import com.example.rickandmortyapp.model.CharacterService
import com.example.rickandmortyapp.ui.character.CharacterFilter
import com.example.rickandmortyapp.ui.character.Mapping

class MainRepository(private val apiService: CharacterService) {
    suspend fun getCharacters(pageNumberCharacterList: Int, characterFilter: ArrayList<CharacterFilter>): GetCharactersResponse {
        val filterMap = Mapping.mapFilterListToQueryMap(characterFilter)
        return apiService.getCharacterList(pageNumberCharacterList, filterMap)
    }

    suspend fun getEpisodes(pageNumberCharacterList: Int/*, filter: ArrayList<EpisodeFilter>*/): GetEpisodeDetailResponse {
      //  val filterMap = Mapping.mapFilterListToQueryMap(filter)
        return apiService.getEpisodeList(pageNumberCharacterList, /*filterMap*/)
    }

    suspend fun getCharacterDetail(id: Int): GetCharacterDetailResponse =
        apiService.getCharacter(id)

    suspend fun getLocationDetail(id: Int): GetLocationDetailRespone = apiService.getLocation(id)

    suspend fun getEpisodeDetail(id: String): Episode = apiService.getEpisode(id)
}