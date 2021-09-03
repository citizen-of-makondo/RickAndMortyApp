package com.example.rickandmortyapp.ui.character.characterDetail


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.GetCharacterDetailResponse
import com.example.rickandmortyapp.databinding.FragmentCharacterDetailBinding
import com.example.rickandmortyapp.model.LoadStatusEnum
import com.example.rickandmortyapp.ui.character.BUNDLE_CHARACTER_ID_KEY
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CharacterDetailFragment : Fragment() {
    private val characterDetailViewModel: CharacterDetailViewModel by viewModel(parameters = { parametersOf(arguments?.getInt(BUNDLE_CHARACTER_ID_KEY) as Int) })

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    private fun setupObserver() {
        characterDetailViewModel.characterDetailLiveData.observe(viewLifecycleOwner) { resources ->
            resources ?: return@observe
            when (resources.statusEnum) {
                LoadStatusEnum.SUCCESS -> {
                    resources.data?.let {
                        setupUI(it)
                    }
                }
                LoadStatusEnum.ERROR ->
                    Log.e("CharacterDetail", "setupObserver")
            }
        }
    }

    private fun setupUI(characterDetail: GetCharacterDetailResponse) = with(binding) {
        nameCharacterDetailTextView.text = "${getString(R.string.name)}: ${characterDetail.name}"
        statusCharacterDetailTextView.text =
            "${getString(R.string.status)}: ${characterDetail.status}"
        specieCharacterDetailTextView.text =
            "${getString(R.string.specie)}: ${characterDetail.species}"
        genderCharacterDetailTextView.text =
            "${getString(R.string.gender)}: ${characterDetail.gender}"
        typeCharacterDetailTextView.text =
            "${getString(R.string.type)}: ${characterDetail.type}"
        locationCharacterDetailTextView.text =
            "${getString(R.string.location)}: ${characterDetail.location.name}"
        originCharacterDetailTextView.text =
            "${getString(R.string.origin)}: ${characterDetail.origin.name}"
        Glide.with(imageView.context)
            .load(characterDetail.image)
            .into(imageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
