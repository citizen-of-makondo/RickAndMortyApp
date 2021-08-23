package com.example.rickandmortyapp.data.model

import com.google.gson.annotations.SerializedName

data class GetCharactersResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Character>
)

data class Info(
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String?
)

data class Character(
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
    val url: String
)