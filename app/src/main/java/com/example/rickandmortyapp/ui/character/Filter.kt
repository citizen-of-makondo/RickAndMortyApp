package com.example.rickandmortyapp.ui.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Filter(val value: String) {
    class Status(val status: String) : Filter(status)

    class Gender(val gender: String) : Filter(gender)

    class Species(val species: String) : Filter(species)

    class Name(val name: String) : Filter(name)

    @Parcelize
    class FilterList: ArrayList<Filter>(), Parcelable
}
