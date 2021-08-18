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

class CharacterFilterFragment : Fragment() {
    private var _binding: FragmentCharacterFilterBinding? = null

    private val binding get() = _binding!!
    var filter: ArrayList<Filter> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("fromViewToFilterKey") { requestKey, bundle ->
            filter = bundle.getSerializable("bundleFromViewToFilterKey") as ArrayList<Filter>
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
        CharacterFilterChipChecked().filterCharacter(view, filter)
    }

    private fun initView(view: View?) {
        binding.useFilter.setOnClickListener {
            filter = view?.let { CharacterFilterChipChecked().filterCharacter(view, filter) }!!
            val bundle = Bundle().apply {
                putSerializable("bundlefromFilterToViewKey", filter)
            }
            setFragmentResult("fromFilterToViewKey", bundle)
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}