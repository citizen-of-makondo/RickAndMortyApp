package com.example.rickandmortyapp.ui.character

import java.io.Serializable

sealed class Filter(val value: String) : Serializable {
    class Status(status: String) : Filter(status)

    class Gender(gender: String) : Filter(gender)

    class Species(species: String) : Filter(species)

    class Name(name: String): Filter(name)

    class Page(page: Int): Filter(page.toString())
}
