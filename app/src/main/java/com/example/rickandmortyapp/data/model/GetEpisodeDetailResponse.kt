package com.example.rickandmortyapp.data.model

import com.google.gson.annotations.SerializedName

data class GetEpisodeDetailResponse(
    @SerializedName("results")
    val results: List<Episode>,
    @SerializedName("info")
    val info: EpisodeInfo
)

data class EpisodeInfo(
    @SerializedName("pages")
    val pages: Int
)

data class Episode(
    @SerializedName("air_date")
    val airDate: String,
    @SerializedName("characters")
    val characters: List<String>,
    @SerializedName("episode")
    val episode: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
)