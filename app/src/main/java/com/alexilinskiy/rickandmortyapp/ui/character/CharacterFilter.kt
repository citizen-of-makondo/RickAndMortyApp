package com.alexilinskiy.rickandmortyapp.ui.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class CharacterFilter(val value: String) {
    class Status(val status: String) : CharacterFilter(status)

    class Gender(val gender: String) : CharacterFilter(gender)

    class Species(val species: String) : CharacterFilter(species)

    class Name(val name: String) : CharacterFilter(name)

    @Parcelize
    class FilterList: ArrayList<CharacterFilter>(), Parcelable
}
