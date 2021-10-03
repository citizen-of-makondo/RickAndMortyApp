package com.example.rickandmortyapp.ui.character.characterDetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.adapter.CharacterDetailAdapter

import com.example.rickandmortyapp.databinding.FragmentCharacterDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CharacterDetailFragment : Fragment() {
    private val characterDetailViewModel: CharacterDetailViewModel by viewModel(parameters = {
        parametersOf(CharacterDetailFragmentArgs.fromBundle(requireArguments()).characterID)
    })

    lateinit var adapter: CharacterDetailAdapter
    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)

        binding.viewmodel = characterDetailViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                parentFragmentManager.popBackStack()
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupUI()
    }

    private fun setupUI() {
        val recyclerView = binding.episodeRecyclerView
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        adapter = CharacterDetailAdapter()
        recyclerView.adapter = adapter
        binding.locationCharacterDetail.setOnClickListener {
            detailLocationDirection()
        }
    }

    private fun setupObserver() {
        characterDetailViewModel.characterDetailLiveData.observe(viewLifecycleOwner) { resources ->
            resources ?: return@observe
            characterDetailViewModel.episodeList.value?.let { adapter.dataList }
        }
    }

    private fun detailLocationDirection() {
        val locationID =
            characterDetailViewModel.characterDetailLiveData.value?.location?.url?.split("/".toRegex())
                ?.last()?.toInt()
        locationID?.let {
            val action =
                CharacterDetailFragmentDirections.actionCharacterDetailFragmentToLocationDetailFragment(
                    locationID = locationID)
            view?.let { view -> Navigation.findNavController(view).navigate(action) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}