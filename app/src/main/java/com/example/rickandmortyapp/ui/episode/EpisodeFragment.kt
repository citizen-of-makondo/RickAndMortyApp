package com.example.rickandmortyapp.ui.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortyapp.databinding.FragmentEpisodeBinding
import com.example.rickandmortyapp.model.LoadStatusEnum
import com.example.rickandmortyapp.modules.koin.PaginationScrollListener
import com.example.rickandmortyapp.ui.character.CharacterFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment : Fragment() {
    private val episodeViewModel: EpisodeViewModel by viewModel()
    private lateinit var adapter: EpisodeAdapter
    private var _binding: FragmentEpisodeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEpisodeBinding.inflate(inflater, container, false)

        binding.episodeViewModel = episodeViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupObserver()
    }

    private fun setupObserver() {
        episodeViewModel.episodeLiveData.observe(viewLifecycleOwner) { resource ->
            resource ?: return@observe
            when (resource.statusEnum) {
                LoadStatusEnum.SUCCESS -> {
                    resource.data?.let {
                        adapter.updateData(it)
                    }
                }
            }
        }
    }

    private fun setupUI() {
        val recyclerView = binding.episodeList
        val layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = layoutManager
        adapter = EpisodeAdapter { episode, _ -> detailEpisodeNavigation(episode.id) }
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLoading(): Boolean {
                return episodeViewModel.loadingLiveData.value ?: return false
            }

            override fun loadMoreItems() {
                getMoreItems()
            }

            private fun getMoreItems() {
                episodeViewModel.getEpisodeList()
            }
        })
    }

    private fun detailEpisodeNavigation(character: Int) {
        val action = CharacterFragmentDirections.actionNavigationCharacterToCharacterDetailFragment(
            characterID = character)
        view?.let {
            Navigation.findNavController(it)
                .navigate(action)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}