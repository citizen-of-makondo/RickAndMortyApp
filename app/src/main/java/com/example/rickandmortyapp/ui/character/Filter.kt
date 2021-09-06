package com.example.rickandmortyapp.ui.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Filter(val value: String) {
    @Parcelize
    class Status(val status: String) : Filter(status), Parcelable

    @Parcelize
    class Gender(val gender: String) : Filter(gender), Parcelable

    @Parcelize
    class Species(val species: String) : Filter(species), Parcelable

    @Parcelize
    class Name(val name: String) : Filter(name), Parcelable

    @Parcelize
    class FilterList: ArrayList<Filter>(), Parcelable
}
