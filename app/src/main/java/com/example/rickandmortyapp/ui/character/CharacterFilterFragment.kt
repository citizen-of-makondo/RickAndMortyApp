package com.example.rickandmortyapp.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.rickandmortyapp.databinding.FragmentCharacterFilterBinding

const val REQUEST_FILTER_KEY = "fromFilterToViewKey"
const val REQUEST_CHARACTER_KEY = "fromViewToFilterKey"

class CharacterFilterFragment : Fragment() {
    private var _binding: FragmentCharacterFilterBinding? = null
    private val binding get() = _binding!!

    var filterList: ArrayList<Filter> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(REQUEST_CHARACTER_KEY) { _, bundle ->
            filterList = bundle.getSerializable(BUNDLE_CHARACTER_KEY) as ArrayList<Filter>
        }
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
            filterList.clear()
            putFilterInBundle(view)
            findNavController().popBackStack()
        }
        if (!filterList.isNullOrEmpty()) {
            for (item in filterList) {
                if (item is Filter.Status) CharacterFilterChipChecked().setColorStatusChipGroup(
                    statusGroup,
                    item)
                if (item is Filter.Gender) CharacterFilterChipChecked().setColorGenderChipGroup(
                    genderGroup,
                    item)
                if (item is Filter.Species) CharacterFilterChipChecked().setColorSpeciesChipGroup(
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