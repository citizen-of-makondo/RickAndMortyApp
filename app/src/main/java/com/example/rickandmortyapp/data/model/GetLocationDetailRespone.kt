package com.example.rickandmortyapp.data.model

import com.google.gson.annotations.SerializedName

data class GetLocationDetailRespone(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("characters")
    val residents: List<String>,
    @SerializedName("type")
    val type: String,
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("url")
    val url: String
)