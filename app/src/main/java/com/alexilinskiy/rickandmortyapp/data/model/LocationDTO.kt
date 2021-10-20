package com.alexilinskiy.rickandmortyapp.data.model

import com.google.gson.annotations.SerializedName

data class LocationDTO(
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("residents")
    val characters: List<String>,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)
