package com.example.rickandmortyapp.ui.location.locationDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.adapter.CharacterAdapterForEpisodeAndLocation
import com.example.rickandmortyapp.databinding.FragmentLocationDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LocationDetailFragment : Fragment() {
    private val locationDetailViewModel: LocationDetailViewModel by viewModel(parameters = {
        parametersOf(LocationDetailFragmentArgs.fromBundle(requireArguments()).locationID)
    })

    lateinit var adapter: CharacterAdapterForEpisodeAndLocation
    lateinit var recyclerView: RecyclerView

    private var _binding: FragmentLocationDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLocationDetailBinding.inflate(inflater, container, false)

        binding.viewmodel = locationDetailViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupUI()
    }

    private fun setupUI() {
        recyclerView = binding.characterList
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        adapter =
            CharacterAdapterForEpisodeAndLocation { character -> getDetailCharacter(character.id) }
        recyclerView.adapter = adapter
    }

    private fun getDetailCharacter(characterId: Int) {
        val action =
            LocationDetailFragmentDirections.actionLocationDetailFragmentToCharacterDetailFragment(
                characterId)
        view?.let {
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun setupObserver() {
        locationDetailViewModel.locationDetailLiveData.observe(viewLifecycleOwner) { }
        locationDetailViewModel.characterListLiveData.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}