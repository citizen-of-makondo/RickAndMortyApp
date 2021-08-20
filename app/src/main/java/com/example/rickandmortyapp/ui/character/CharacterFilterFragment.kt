package com.example.rickandmortyapp.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.rickandmortyapp.databinding.FragmentCharacterFilterBinding

class CharacterFilterFragment : Fragment() {
    private val bundleFromCharacterFragmentKey = "bundleFromViewToFilterKey"
    private val bundleToChatacterFragmentKey = "bundleFromFilterToViewKey"
    private val requestKey = "fromFilterToViewKey"
    private var _binding: FragmentCharacterFilterBinding? = null
    private val binding get() = _binding!!

    private var filter: ArrayList<Filter> = arrayListOf()

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
        filter = arguments?.getSerializable(bundleFromCharacterFragmentKey) as ArrayList<Filter>
        initView(view, filter)
    }

    private fun initView(view: View, filter: ArrayList<Filter>) = with(binding) {
        useFilter.setOnClickListener {
            putFilterInBundle(view)
            findNavController().popBackStack()
        }
        clearFilter.setOnClickListener {
            filter.clear()
            putFilterInBundle(view)
            findNavController().popBackStack()
        }
        if (!filter.isNullOrEmpty()) {
            for (item in filter) {
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

    fun putFilterInBundle(view: View) {
        filter =
            CharacterFilterChipChecked().checkAllChipGroupAndFillFilter(view)
        val bundle = Bundle().apply {
            putSerializable(bundleToChatacterFragmentKey, filter)
        }
        setFragmentResult(requestKey, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}