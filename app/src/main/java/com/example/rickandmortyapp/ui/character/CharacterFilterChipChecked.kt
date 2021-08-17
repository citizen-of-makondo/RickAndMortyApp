package com.example.rickandmortyapp.ui.character

import android.view.View
import com.example.rickandmortyapp.R
import com.google.android.material.chip.ChipGroup

class CharacterFilterChipChecked {
    fun filterCharacter(view: View): ArrayList<Filter> {
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
            R.id.status_alive -> "alive"
            R.id.status_dead -> "dead"
            R.id.status_unknown -> "unknown"
            else -> null
        }
    }

    private fun checkGender(view: View): String? {
        val chipGroupGender = view.findViewById<ChipGroup>(R.id.gender_group)
        return when (chipGroupGender.checkedChipId) {
            R.id.gender_male -> "male"
            R.id.gender_female -> "female"
            R.id.gender_genderless -> "genderless"
            R.id.gender_unknown -> "unknown"
            else -> null
        }
    }

    private fun checkSpecie(view: View): String? {
        val chipGroupSpecie = view.findViewById<ChipGroup>(R.id.specie_group)
        return when (chipGroupSpecie.checkedChipId) {
            R.id.specie_human -> "human"
            R.id.specie_alien -> "alien"
            R.id.specie_humanoid -> "humanoid"
            R.id.specie_poopybutthole -> "poopybutthole"
            R.id.specie_mythological -> "mythological"
            R.id.specie_animal -> "animal"
            R.id.specie_robot -> "robot"
            R.id.specie_cronenberg -> "cronenberg"
            R.id.specie_disease -> "disease"
            R.id.specie_planet -> "planet"
            R.id.specie_unknown -> "unknown"
            else -> null
        }
    }
}