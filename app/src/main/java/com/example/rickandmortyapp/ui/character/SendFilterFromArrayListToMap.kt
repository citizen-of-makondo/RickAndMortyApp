package com.example.rickandmortyapp.ui.character

import java.util.*

class SendFilterFromArrayListToMap {
    companion object {
        @JvmStatic
        fun sendFilterFromArrayListToMap(
            result: ArrayList<Filter>,
            characterViewModel: CharacterViewModel,
        ) = with(characterViewModel) {
            filterMap.clear()
            for (item in result) {
                when (item) {
                    is Filter.Status -> filterMap["status"] = item.value
                    is Filter.Gender -> filterMap["gender"] = item.value
                    is Filter.Species -> filterMap["species"] = item.value
                    is Filter.Name -> filterMap["name"] = item.value
                }
            }
            pageNumberCharacterList = 1
            getCharacterList()
        }
    }
}