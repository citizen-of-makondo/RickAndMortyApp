package com.example.rickandmortyapp.data.api

import com.example.rickandmortyapp.model.CharacterService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CharacterRetrofitBuilder {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    private fun getRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    val API_SERVICE: CharacterService = getRetrofit().create(CharacterService::class.java)
}