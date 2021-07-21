package com.example.rickandmortyapp.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyapp.databinding.FragmentCharacterBinding

class CharacterFragment : Fragment() {

    private lateinit var characterViewModel: CharacterViewModel
    private var _binding: FragmentCharacterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        characterViewModel =
            ViewModelProvider(this).get(CharacterViewModel::class.java)

        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCharacter
        characterViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}