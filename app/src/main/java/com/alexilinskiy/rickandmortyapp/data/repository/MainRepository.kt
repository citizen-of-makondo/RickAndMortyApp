package com.alexilinskiy.rickandmortyapp.data.repository

import com.alexilinskiy.rickandmortyapp.data.model.*
import com.alexilinskiy.rickandmortyapp.model.CharacterService
import com.alexilinskiy.rickandmortyapp.ui.character.CharacterFilter
import com.alexilinskiy.rickandmortyapp.ui.character.Mapping
import retrofit2.Response

class MainRepository(private val apiService: CharacterService) {
    suspend fun getCharacters(
        pageNumberCharacterList: Int,
        characterFilter: ArrayList<CharacterFilter>,
    ): Response<GetCharactersResponse> {
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