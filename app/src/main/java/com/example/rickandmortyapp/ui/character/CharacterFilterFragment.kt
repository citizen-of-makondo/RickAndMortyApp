package com.example.rickandmortyapp.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        initViews(view)
        return binding.root
    }

    private fun initViews(view: View?) {
        val useFilterButton = binding.useFilter
        useFilterButton.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}