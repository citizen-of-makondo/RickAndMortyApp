package com.example.rickandmortyapp.modules.koin

import com.example.rickandmortyapp.ui.character.CharacterViewModel
import com.example.rickandmortyapp.ui.episode.EpisodeViewModel
import com.example.rickandmortyapp.ui.location.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { CharacterViewModel() }
    viewModel { EpisodeViewModel() }
    viewModel { LocationViewModel() }
}
