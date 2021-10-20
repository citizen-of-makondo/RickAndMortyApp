package com.alexilinskiy.rickandmortyapp.modules.koin

import com.alexilinskiy.rickandmortyapp.data.api.CharacterRetrofitBuilder
import com.alexilinskiy.rickandmortyapp.data.repository.MainRepository
import com.alexilinskiy.rickandmortyapp.model.CharacterService
import com.alexilinskiy.rickandmortyapp.ui.character.CharacterViewModel
import com.alexilinskiy.rickandmortyapp.ui.character.characterDetail.CharacterDetailViewModel
import com.alexilinskiy.rickandmortyapp.ui.episode.EpisodeViewModel
import com.alexilinskiy.rickandmortyapp.ui.episode.episodeDetail.EpisodeDetailViewModel
import com.alexilinskiy.rickandmortyapp.ui.location.LocationViewModel
import com.alexilinskiy.rickandmortyapp.ui.location.locationDetail.LocationDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single { CharacterRetrofitBuilder.getRetrofit() }
    single { get<Retrofit>().create(CharacterService::class.java) }
    single { MainRepository(get()) }
    viewModel { CharacterViewModel(get()) }
    viewModel { (characterID: Int) -> CharacterDetailViewModel(get(), characterID) }
    viewModel { EpisodeViewModel(get()) }
    viewModel { (episodeID: Int) -> EpisodeDetailViewModel(get(), episodeID) }
    viewModel { LocationViewModel(get()) }
    viewModel { (locationID: Int) -> LocationDetailViewModel(get(), locationID) }
}
