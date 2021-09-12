package com.example.rickandmortyapp.ui.character.characterDetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.rickandmortyapp.databinding.FragmentCharacterDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CharacterDetailFragment : Fragment() {
    private val characterDetailViewModel: CharacterDetailViewModel by viewModel(parameters = {
        parametersOf(CharacterDetailFragmentArgs.fromBundle(requireArguments()).characterID)
    })

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
        binding.locationCharacterDetail.setOnClickListener {
            detailLocationDirection()
        }
    }

    private fun setupObserver() {
        characterDetailViewModel.characterDetailLiveData.observe(viewLifecycleOwner) { resources ->
            resources ?: return@observe
        }
    }

    private fun detailLocationDirection() {
        val locationID =
            characterDetailViewModel.characterDetailLiveData.value?.data?.location?.url?.split("/".toRegex())
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