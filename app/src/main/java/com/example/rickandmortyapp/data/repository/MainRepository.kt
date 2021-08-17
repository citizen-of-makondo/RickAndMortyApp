package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.model.GetCharactersResponse
import com.example.rickandmortyapp.model.CharacterService
import com.example.rickandmortyapp.ui.character.CharacterViewModel
import com.example.rickandmortyapp.ui.character.Filter
import java.util.*

class MainRepository(private val apiService: CharacterService) {
    // Запросы всего листа персонажей и фильтрованный
    suspend fun getCharacters(page: Int): GetCharactersResponse = apiService.getCharacterList(page)
    suspend fun getFilteredCharacters(filterMap: MutableMap<String, String>): GetCharactersResponse =
        apiService.getFilteredList(filterMap)

    // Получение страниц
    suspend fun getPages(page: Int): GetCharactersResponse = apiService.getCharactersPages(page)
    suspend fun getPages(filterMap: MutableMap<String, String>): GetCharactersResponse =
        apiService.getFilteredCharactersPages(filterMap)


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
            pageNumberFilteredCharacterList = 1
            getCharacterList()
        }
    }
}