package com.example.rickandmortyapp.data.model

import com.google.gson.annotations.SerializedName

data class GetCharacterDetailResponse(
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
    val location: Location,
    @SerializedName("origin")
    val origin: Origin,
    @SerializedName("episode")
    val episode: Array<String>
)

data class Origin(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class Location(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
