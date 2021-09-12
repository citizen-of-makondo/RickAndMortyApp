package com.example.rickandmortyapp.data.model

import com.google.gson.annotations.SerializedName

data class GetEpisodeDetailResponse(
    @SerializedName("airDate")
    val air_date: String,
    @SerializedName("characters")
    val characters: List<String>,
    @SerializedName("episode")
    val episode: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)