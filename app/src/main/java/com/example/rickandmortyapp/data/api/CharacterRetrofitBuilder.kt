package com.example.rickandmortyapp.data.api

import com.example.rickandmortyapp.model.CharacterService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CharacterRetrofitBuilder {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val API_SERVICE: CharacterService = getRetrofit().create(CharacterService::class.java)
}