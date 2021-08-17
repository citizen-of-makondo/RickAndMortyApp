package com.example.rickandmortyapp.model

import com.example.rickandmortyapp.data.model.GetCharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface CharacterService {
    @GET("character/")
    suspend fun getCharacterList(@Query("page") page: Int): GetCharactersResponse

    @GET("character/")
    suspend fun getFilteredList(@QueryMap filter: MutableMap<String, String>): GetCharactersResponse

    @GET("character/")
    suspend fun getCharactersPages(@Query("page") page: Int): GetCharactersResponse

    @GET("character/")
    suspend fun getFilteredCharactersPages(@QueryMap filter: MutableMap<String, String>): GetCharactersResponse
}

