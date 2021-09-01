package com.example.rickandmortyapp.ui.character

import android.view.View
import com.example.rickandmortyapp.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.*

class CharacterFilterChipChecked {
    fun checkAllChipGroupAndFillFilter(view: View): ArrayList<Filter> {
        val filterList = arrayListOf<Filter>()
        val status = getStatusChipGroupChecked(view)
        val gender = getGenderChipGroupChecked(view)
        val specie = getSpecieChipGroupChecked(view)
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

    private fun getStatusChipGroupChecked(view: View): String? {
        val chipGroupStatus = view.findViewById<ChipGroup>(R.id.status_group)
        return when (chipGroupStatus.checkedChipId) {
            R.id.status_alive -> view.context.getString(R.string.status_alive)
            R.id.status_dead -> view.context.getString(R.string.status_dead)
            R.id.status_unknown -> view.context.getString(R.string.unknown)
            else -> null
        }
    }

    private fun getGenderChipGroupChecked(view: View): String? {
        val chipGroupGender = view.findViewById<ChipGroup>(R.id.gender_group)
        return when (chipGroupGender.checkedChipId) {
            R.id.gender_male -> view.context.getString(R.string.gender_male)
            R.id.gender_female -> view.context.getString(R.string.gender_female)
            R.id.gender_genderless -> view.context.getString(R.string.gender_genderless)
            R.id.gender_unknown -> view.context.getString(R.string.unknown)
            else -> null
        }
    }

    private fun getSpecieChipGroupChecked(view: View): String? {
        val chipGroupSpecie = view.findViewById<ChipGroup>(R.id.specie_group)
        return when (chipGroupSpecie.checkedChipId) {
            R.id.specie_human -> view.context.getString(R.string.specie_human)
            R.id.specie_alien -> view.context.getString(R.string.specie_alien)
            R.id.specie_humanoid -> view.context.getString(R.string.specie_humanoid)
            R.id.specie_poopybutthole -> view.context.getString(R.string.specie_poopybutthole)
            R.id.specie_mythological -> view.context.getString(R.string.specie_mythological)
            R.id.specie_animal -> view.context.getString(R.string.specie_animal)
            R.id.specie_robot -> view.context.getString(R.string.specie_robot)
            R.id.specie_cronenberg -> view.context.getString(R.string.specie_cronenberg)
            R.id.specie_disease -> view.context.getString(R.string.specie_disease)
            R.id.specie_planet -> view.context.getString(R.string.specie_planet)
            R.id.specie_unknown -> view.context.getString(R.string.unknown)
            else -> null
        }
    }

    fun setColorSpeciesChipGroup(view: View, item: Filter.Species) {
        val arrayPotentialView: ArrayList<View> = arrayListOf()
        view.findViewsWithText(arrayPotentialView,
            item.value,
            View.FIND_VIEWS_WITH_TEXT)
        val checkedChip = arrayPotentialView.first() as Chip
        checkedChip.setChipBackgroundColorResource(R.color.dark_green)
    }

    fun setColorGenderChipGroup(view: View, item: Filter.Gender) {
        val arrayPotentialView: ArrayList<View> = arrayListOf()
        view.findViewsWithText(arrayPotentialView,
            item.value,
            View.FIND_VIEWS_WITH_TEXT)
        val checkedChip = arrayPotentialView.first() as Chip
        checkedChip.setChipBackgroundColorResource(R.color.dark_green)
    }

    fun setColorStatusChipGroup(view: View, item: Filter.Status) {
        val arrayPotentialView: ArrayList<View> = arrayListOf()
        view.findViewsWithText(arrayPotentialView,
            item.value,
            View.FIND_VIEWS_WITH_TEXT)
        val checkedChip = arrayPotentialView.first() as Chip
        checkedChip.setChipBackgroundColorResource(R.color.dark_green)
    }
}