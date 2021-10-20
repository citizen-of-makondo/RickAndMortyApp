package com.alexilinskiy.rickandmortyapp.model

import com.alexilinskiy.rickandmortyapp.data.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface CharacterService {
    @GET("character/")
    suspend fun getCharacterList(
        @Query("page") page: Int,
        @QueryMap filterMap: MutableMap<String, String>,
    ): GetCharactersResponse

    @GET("character/{id}")
    suspend fun getMultipleCharacters(@Path("id") id: String): List<CharacterDTO>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterDTO

    @GET("episode/")
    suspend fun getEpisodeList(
        @Query("page") page: Int,
        //    @QueryMap filterMap: MutableMap<String, String>
    ): GetEpisodeDetailResponse

    @GET("episode/{id}")
    suspend fun getMultipleEpisodes(@Path("id") id: String): List<EpisodeDTO>

    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") id: String): EpisodeDTO

    @GET("location/")
    suspend fun getLocationList(
        @Query("page") page: Int,
    ): GetLocationResponse

    @GET("location/{id}")
    suspend fun getLocation(@Path("id") id: Int): LocationDTO

    @GET("location/{id}")
    suspend fun getMultipleLocation(@Path("id") id: Int): List<LocationDTO>
}

