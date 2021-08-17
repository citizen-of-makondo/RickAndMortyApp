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

    private fun initView(view: View?) {
        binding.useFilter.setOnClickListener {
            var filter: ArrayList<Filter> = arrayListOf()
            filter = view?.let { CharacterFilterChipChecked().filterCharacter(view) }!!
            val bundle = Bundle().apply {
                putSerializable("bundleKey", filter)
            }
            setFragmentResult("requestKey", bundle)
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}