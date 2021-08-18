package com.example.rickandmortyapp.ui.character

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.View
import com.example.rickandmortyapp.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class CharacterFilterChipChecked {

    fun filterCharacter(view: View, filter: ArrayList<Filter>): ArrayList<Filter> {
        if (!filter.isNullOrEmpty()) {
            for (item in filter) {
                when(item)
                {
                    is Filter.Status -> setColorStatusChipGroup(view, item)
                    is Filter.Gender -> setColorGenderChipGroup(view)
                    is Filter.Species -> setColorSpeciesChipGroup(view)
                }
            }
        }
        val filterList: ArrayList<Filter> = arrayListOf()
        val status = checkStatus(view)
        val gender = checkGender(view)
        val specie = checkSpecie(view)
        status?.let {
            filterList.add(Filter.Status(it))
        }
        gender?.let {
            filterList.add(Filter.Gender(it))
        }
        specie?.let {
            filterList.add(Filter.Species(it))
        }
        return filterList
    }

    private fun checkStatus(view: View): String? {
        val chipGroupStatus = view.findViewById<ChipGroup>(R.id.status_group)
        return when (chipGroupStatus.checkedChipId) {
            R.id.status_alive -> "Alive"
            R.id.status_dead -> "Dead"
            R.id.status_unknown -> "Unknown"
            else -> null
        }
    }

    private fun checkGender(view: View): String? {
        val chipGroupGender = view.findViewById<ChipGroup>(R.id.gender_group)
        return when (chipGroupGender.checkedChipId) {
            R.id.gender_male -> "Male"
            R.id.gender_female -> "Female"
            R.id.gender_genderless -> "Genderless"
            R.id.gender_unknown -> "Unknown"
            else -> null
        }
    }

    private fun checkSpecie(view: View): String? {
        val chipGroupSpecie = view.findViewById<ChipGroup>(R.id.specie_group)
        return when (chipGroupSpecie.checkedChipId) {
            R.id.specie_human -> "Human"
            R.id.specie_alien -> "Alien"
            R.id.specie_humanoid -> "Humanoid"
            R.id.specie_poopybutthole -> "Poopybutthole"
            R.id.specie_mythological -> "Mythological"
            R.id.specie_animal -> "Animal"
            R.id.specie_robot -> "Robot"
            R.id.specie_cronenberg -> "Cronenberg"
            R.id.specie_disease -> "Disease"
            R.id.specie_planet -> "Planet"
            R.id.specie_unknown -> "Unknown"
            else -> null
        }
    }

    private fun setColorSpeciesChipGroup(view: View) {

    }

    private fun setColorGenderChipGroup(view: View) {

    }

    @SuppressLint("ResourceAsColor")
    private fun setColorStatusChipGroup(view: View, item: Filter.Status) {
        val arrayPotentinalView: ArrayList<View> = arrayListOf()
        val chip:Chip = view.findViewsWithText(arrayPotentinalView, item.value, View.FIND_VIEWS_WITH_TEXT) as Chip
        chip.chipBackgroundColor = ColorStateList.valueOf(R.color.black)
    }
}