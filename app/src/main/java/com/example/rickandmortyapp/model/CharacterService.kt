package com.example.rickandmortyapp.model

import com.example.rickandmortyapp.data.model.CharacterDTO
import com.example.rickandmortyapp.data.model.DataDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {
    @GET("character/")
    suspend fun getCharacterList(@Query("page") page: Int): DataDTO<CharacterDTO>
}