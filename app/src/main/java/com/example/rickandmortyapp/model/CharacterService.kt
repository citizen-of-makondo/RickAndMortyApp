package com.example.rickandmortyapp.model

import com.example.rickandmortyapp.data.model.GetCharacterDetailResponse
import com.example.rickandmortyapp.data.model.GetCharactersResponse
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
    suspend fun getCharacter(@Path("id") id: Int): GetCharacterDetailResponse
}

