package com.example.rickandmortyapp.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyapp.databinding.FragmentCharacterBinding

class CharacterFragment : Fragment() {

    private lateinit var characterViewModel: CharacterViewModel
    private var binding: FragmentCharacterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        characterViewModel =
            ViewModelProvider(this).get(CharacterViewModel::class.java)

        binding = FragmentCharacterBinding.inflate(inflater, container, false)

        initViews()

        return binding!!.root
    }

    private fun initViews() = with(binding) {
        val textView = this?.textCharacter
        characterViewModel.text.observe(viewLifecycleOwner, Observer {
            textView?.run {
                textView.text = it
            }
        })
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}