package com.example.rickandmortyapp.ui.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyapp.databinding.FragmentEpisodeBinding

class EpisodeFragment : Fragment() {

    private lateinit var episodeViewModel: EpisodeViewModel
    private lateinit var binding: FragmentEpisodeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        episodeViewModel =
            ViewModelProvider(this).get(EpisodeViewModel::class.java)

        binding = FragmentEpisodeBinding.inflate(inflater, container, false)

        initViews()

        return binding.root
    }

    private fun initViews() = with(binding) {
        val textView: TextView = binding.textEpisode
        episodeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
    }
}