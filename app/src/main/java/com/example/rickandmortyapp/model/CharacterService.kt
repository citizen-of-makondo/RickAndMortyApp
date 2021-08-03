package com.example.rickandmortyapp.model

import com.example.rickandmortyapp.data.model.CharacterDTO
import com.example.rickandmortyapp.data.model.ResultDTO
import retrofit2.http.GET

interface CharacterService {
    @GET("character")
    suspend fun getCharacterList(): ResultDTO<CharacterDTO>
}