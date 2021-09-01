package com.example.rickandmortyapp.ui.character

object Mapping {
    fun mapFilterListToQueryMap(result: ArrayList<Filter>): MutableMap<String, String> {
        val filterMap: MutableMap<String, String> = mutableMapOf()
        for (item in result) {
            when (item) {
                is Filter.Status -> filterMap["status"] = item.value
                is Filter.Gender -> filterMap["gender"] = item.value
                is Filter.Species -> filterMap["species"] = item.value
                is Filter.Name -> filterMap["name"] = item.value
            }
        }
        return filterMap
    }
}