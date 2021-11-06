package com.example.rickandmortyapp.ui.character

object Mapping {
    fun mapFilterListToQueryMap(result: ArrayList<CharacterFilter>): MutableMap<String, String> {
        val filterMap: MutableMap<String, String> = mutableMapOf()
        for (item in result) {
            when (item) {
                is CharacterFilter.Status -> filterMap["status"] = item.value
                is CharacterFilter.Gender -> filterMap["gender"] = item.value
                is CharacterFilter.Species -> filterMap["species"] = item.value
                is CharacterFilter.Name -> filterMap["name"] = item.value
            }
        }
        return filterMap
    }
}