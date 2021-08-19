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
    private var _binding: FragmentCharacterFilterBinding? = null

    private val binding get() = _binding!!
    private var filter: ArrayList<Filter> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setFragmentResultListener("fromViewToFilterKey") { requestKey, bundle ->
            filter = arguments?.getSerializable("bundleFromViewToFilterKey") as ArrayList<Filter>
            //filter = bundle.getSerializable("bundleFromViewToFilterKey") as ArrayList<Filter>
        }*/
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
        filter = arguments?.getSerializable("bundleFromViewToFilterKey") as ArrayList<Filter>
        initView(view, filter)
    }

    private fun initView(view: View?, filter: ArrayList<Filter>) {
        binding.useFilter.setOnClickListener {
            this.filter = view?.let {
                CharacterFilterChipChecked().filterCharacter(view,
                    this.filter)
            }!!
            val bundle = Bundle().apply {
                putSerializable("bundleFromFilterToViewKey", this@CharacterFilterFragment.filter)
            }
            setFragmentResult("fromFilterToViewKey", bundle)
            findNavController().popBackStack()
        }
        if (!filter.isNullOrEmpty()) {
            view?.let {
                for (item in filter) {
                    if (item is Filter.Status) CharacterFilterChipChecked().setColorStatusChipGroup(binding.statusGroup, item)
                    if (item is Filter.Gender) CharacterFilterChipChecked().setColorGenderChipGroup(binding.genderGroup, item)
                    if (item is Filter.Species) CharacterFilterChipChecked().setColorSpeciesChipGroup(binding.specieGroup, item)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}