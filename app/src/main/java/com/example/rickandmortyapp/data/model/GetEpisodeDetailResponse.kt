package com.example.rickandmortyapp.data.model

import com.google.gson.annotations.SerializedName

data class GetEpisodeDetailResponse(
    @SerializedName("results")
    val results: List<EpisodeDTO>,
    @SerializedName("info")
    val info: EpisodeInfo
)

data class EpisodeInfo(
    @SerializedName("pages")
    val pages: Int
)