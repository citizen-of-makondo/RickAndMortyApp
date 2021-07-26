package com.example.rickandmortyapp.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.FragmentCharacterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterFragment : Fragment() {
    private val characterViewModel: CharacterViewModel by viewModel()
    private var _binding: FragmentCharacterBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)

        initViews(view)

        return binding.root
    }

    private fun initViews(view: View?) = with(binding) {
        var characterList = view?.findViewById<RecyclerView>(R.id.character_list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}