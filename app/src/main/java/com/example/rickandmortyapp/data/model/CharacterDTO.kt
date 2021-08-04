package com.example.rickandmortyapp.data.model

import com.google.gson.annotations.SerializedName

data class DataDTO<T>(
 /*   @SerializedName("info")
    val info: Info,*/
    @SerializedName("results")
    val results: Array<CharacterDTO>
)

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
    //  @SerializedName("status")
    //   val origin: ArrayList<String>,
    // @SerializedName("location")
    //  val location: ArrayList<String>,
    @SerializedName("image")
    val image: String,
    //  @SerializedName("episode")
    //  val episode: Array<String>,
    @SerializedName("url")
    val url: String
)
/*
data class Info(
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?
) */