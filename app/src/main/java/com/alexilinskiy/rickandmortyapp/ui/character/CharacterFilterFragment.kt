package com.alexilinskiy.rickandmortyapp.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.alexilinskiy.rickandmortyapp.databinding.FragmentCharacterFilterBinding

const val REQUEST_FILTER_KEY = "fromFilterToViewKey"

class CharacterFilterFragment : Fragment() {
    private var _binding: FragmentCharacterFilterBinding? = null
    private val binding get() = _binding!!

    var characterFilterList: ArrayList<CharacterFilter> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        characterFilterList.addAll(CharacterFilterFragmentArgs.fromBundle(requireArguments()).filter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCharacterFilterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) = with(binding) {
        useFilter.setOnClickListener {
            putFilterInBundle(view)
            findNavController().popBackStack()
        }
        clearFilter.setOnClickListener {
            characterFilterList.clear()
            putFilterInBundle(view)
            findNavController().popBackStack()
        }
        if (!characterFilterList.isNullOrEmpty()) {
            for (item in characterFilterList) {
                if (item is CharacterFilter.Status) CharacterFilterChipChecked().setColorStatusChipGroup(
                    statusGroup,
                    item)
                if (item is CharacterFilter.Gender) CharacterFilterChipChecked().setColorGenderChipGroup(
                    genderGroup,
                    item)
                if (item is CharacterFilter.Species) CharacterFilterChipChecked().setColorSpeciesChipGroup(
                    specieGroup,
                    item)
            }
        }
    }

    private fun putFilterInBundle(view: View) {
        val filter =
            CharacterFilterChipChecked().checkAllChipGroupAndFillFilter(view)
        val bundle = Bundle()
        bundle.putSerializable(BUNDLE_FILTER_KEY, filter)
        setFragmentResult(REQUEST_FILTER_KEY, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}