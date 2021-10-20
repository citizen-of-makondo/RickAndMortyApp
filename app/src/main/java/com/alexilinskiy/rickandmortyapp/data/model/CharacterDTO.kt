package com.alexilinskiy.rickandmortyapp.data.model

import com.google.gson.annotations.SerializedName

data class CharacterDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("location")
    val location: LocationDTO,
    @SerializedName("origin")
    val origin: Origin,
    @SerializedName("episode")
    val episode: List<String>,
)

data class Origin(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
)
