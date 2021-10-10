package com.example.rickandmortyapp.ui.episode.episodeDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.adapter.CharacterAdapterForEpisodeAndLocation
import com.example.rickandmortyapp.data.model.CharacterDTO
import com.example.rickandmortyapp.databinding.FragmentEpisodeDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EpisodeDetailFragment : Fragment() {
    private val episodeDetailViewModel: EpisodeDetailViewModel by viewModel(parameters = {
        parametersOf(EpisodeDetailFragmentArgs.fromBundle(requireArguments()).episodeID)
    })

    lateinit var adapter: CharacterAdapterForEpisodeAndLocation
    private var _binding: FragmentEpisodeDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEpisodeDetailBinding.inflate(inflater, container, false)

        binding.viewmodel = episodeDetailViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupUI()
    }

    private fun setupUI() {
        val recyclerView = binding.charactersEpisodeDetailRecyclerView
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        adapter = CharacterAdapterForEpisodeAndLocation() { character: CharacterDTO -> getCharacterEpisode(character.id) }
        recyclerView.adapter = adapter
    }

    private fun getCharacterEpisode(id: Int) {
        val action =
            EpisodeDetailFragmentDirections.actionEpisodeDetailFragmentToCharacterDetailFragment(id)
        view?.let {
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun setupObserver() {
        episodeDetailViewModel.episodeDetailLiveData.observe(viewLifecycleOwner) {
        }
        episodeDetailViewModel.characterListLiveData.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}