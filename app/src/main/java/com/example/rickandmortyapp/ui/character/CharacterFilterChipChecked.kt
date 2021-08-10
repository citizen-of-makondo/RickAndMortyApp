package com.example.rickandmortyapp.ui.character

import android.view.View
import com.example.rickandmortyapp.R
import com.google.android.material.chip.ChipGroup

class CharacterFilterChipChecked {
    fun filterCharacter(view: View): String? {
        var requestForFilter: String? = null

        val status = checkStatus(view)
        val gender = checkGender(view)
        val specie = checkSpecie(view)
        status?.let {
            requestForFilter += "status=$it"
        }
        gender?.let {
            requestForFilter += "gender=$it"
        }
        specie?.let {
            requestForFilter += "specie=$it"
        }
        return requestForFilter
    }

    private fun checkStatus(view: View): String? {
        val chipGroupStatus = view.findViewById<ChipGroup>(R.id.status_group)
        var status: String? = null
        when (chipGroupStatus.checkedChipId) {
            R.id.status_alive -> status = "alive"
            R.id.status_dead -> status = "dead"
            R.id.status_unknown -> status = "unknown"
        }
        return status
    }

    private fun checkGender(view: View): String? {
        val chipGroupGender = view.findViewById<ChipGroup>(R.id.gender_group)
        var gender: String? = null
        when (chipGroupGender.checkedChipId) {
            R.id.gender_male -> gender = "male"
            R.id.gender_female -> gender = "female"
            R.id.gender_genderless -> gender = "genderless"
            R.id.gender_unknown -> gender = "unknown"
        }
        return gender
    }

    private fun checkSpecie(view: View): String? {
        val chipGroupSpecie = view.findViewById<ChipGroup>(R.id.specie_group)
        var specie: String? = null
        when (chipGroupSpecie.checkedChipId) {
            R.id.specie_human -> specie = "human"
            R.id.specie_alien -> specie = "alien"
            R.id.specie_humanoid -> specie = "humanoid"
            R.id.specie_poopybutthole -> specie = "poopybutthole"
            R.id.specie_mythological -> specie = "mythological"
            R.id.specie_animal -> specie = "animal"
            R.id.specie_robot -> specie = "robot"
            R.id.specie_cronenberg -> specie = "cronenberg"
            R.id.specie_disease -> specie = "disease"
            R.id.specie_planet -> specie = "planet"
            R.id.specie_unknown -> specie = "unknown"
        }
        return specie
    }
}