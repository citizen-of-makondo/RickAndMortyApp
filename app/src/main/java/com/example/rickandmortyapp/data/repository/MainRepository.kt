package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.model.*
import com.example.rickandmortyapp.model.CharacterService
import com.example.rickandmortyapp.ui.character.CharacterFilter
import com.example.rickandmortyapp.ui.character.Mapping

class MainRepository(private val apiService: CharacterService) {
    suspend fun getCharacters(
        pageNumberCharacterList: Int,
        characterFilter: ArrayList<CharacterFilter>,
    ): GetCharactersResponse {
        val filterMap = Mapping.mapFilterListToQueryMap(characterFilter)
        return apiService.getCharacterList(pageNumberCharacterList, filterMap)
    }

    suspend fun getMultipleCharacters(id: String): List<CharacterDTO> =
        apiService.getMultipleCharacters(id)

    suspend fun getCharacterDetail(id: Int): CharacterDTO =
        apiService.getCharacter(id)

    suspend fun getEpisodes(pageNumberCharacterList: Int/*, filter: ArrayList<EpisodeFilter>*/): GetEpisodeDetailResponse {
        //  val filterMap = Mapping.mapFilterListToQueryMap(filter)
        return apiService.getEpisodeList(pageNumberCharacterList /*filterMap*/)
    }

    suspend fun getMultipleEpisodes(id: String): List<EpisodeDTO> =
        apiService.getMultipleEpisodes(id)

    suspend fun getLocations(
        pageNumberList: Int,
       /* locationFilter: ArrayList<CharacterFilter>,*/
    ): GetLocationResponse {
      //  val filterMap = Mapping.mapFilterListToQueryMap(characterFilter)
        return apiService.getLocationList(pageNumberList /*filterMap*/)
    }

    suspend fun getLocationDetail(id: Int): LocationDTO = apiService.getLocation(id)

    suspend fun getEpisode(id: String): EpisodeDTO = apiService.getEpisode(id)

}