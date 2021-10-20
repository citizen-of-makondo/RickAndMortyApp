package com.alexilinskiy.rickandmortyapp.data.model

import com.google.gson.annotations.SerializedName

data class GetLocationResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<LocationDTO>
)

data class Info(
    @SerializedName("pages")
    val pages: Int,
)