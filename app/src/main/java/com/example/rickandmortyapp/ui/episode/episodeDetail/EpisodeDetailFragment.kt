package com.example.rickandmortyapp.ui.episode.episodeDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rickandmortyapp.databinding.FragmentEpisodeDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EpisodeDetailFragment : Fragment() {
    private val episodeDetailViewModel: EpisodeDetailViewModel by viewModel(parameters = {
        parametersOf(EpisodeDetailFragmentArgs.fromBundle(requireArguments()).episodeID)
    })

    private var _binding: FragmentEpisodeDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEpisodeDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    private fun setupObserver() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}