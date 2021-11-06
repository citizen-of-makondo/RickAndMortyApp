package com.alexilinskiy.rickandmortyapp.data.model

import com.google.gson.annotations.SerializedName

data class GetCharactersResponse(
    @SerializedName("info")
    val episodeCharacterInfo: CharacterInfo,
    @SerializedName("results")
    val results: List<CharacterDTO>
)

data class CharacterInfo(
    @SerializedName("pages")
    val pages: Int
)