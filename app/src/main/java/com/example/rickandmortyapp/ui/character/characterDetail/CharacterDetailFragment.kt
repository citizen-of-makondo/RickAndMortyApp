package com.example.rickandmortyapp.ui.character.characterDetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.adapter.EpisodeAdapter
import com.example.rickandmortyapp.databinding.FragmentCharacterDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CharacterDetailFragment : Fragment() {
    private val characterDetailViewModel: CharacterDetailViewModel by viewModel(parameters = {
        parametersOf(CharacterDetailFragmentArgs.fromBundle(requireArguments()).characterID)
    })

    lateinit var adapter: EpisodeAdapter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupUI()
    }

    private fun setupUI() {
        val recyclerView = binding.episodeRecyclerView
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        adapter = EpisodeAdapter { episode -> getDetailEpisode(episode.id) }
        recyclerView.adapter = adapter
        binding.locationCharacterDetail.setOnClickListener {
            getDetailLocation()
        }
    }

    private fun setupObserver() {
        characterDetailViewModel.characterDetailLiveData.observe(viewLifecycleOwner) {
        }
        characterDetailViewModel.episodeListLiveData.observe(viewLifecycleOwner) { resources ->
            adapter.updateData(resources)
        }
    }

    private fun getDetailLocation() {
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

    private fun getDetailEpisode(episodeID: Int) {
        val action =
            CharacterDetailFragmentDirections.actionCharacterDetailFragmentToEpisodeDetailFragment(
                episodeID = episodeID)
        view?.let {
            Navigation.findNavController(it)
                .navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}