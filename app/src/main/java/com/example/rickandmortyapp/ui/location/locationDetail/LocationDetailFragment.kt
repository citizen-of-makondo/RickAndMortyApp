package com.example.rickandmortyapp.ui.location.locationDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rickandmortyapp.databinding.FragmentLocationDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LocationDetailFragment : Fragment() {
    private val locationDetailViewModel: LocationDetailViewModel by viewModel(parameters = {
        parametersOf(LocationDetailFragmentArgs.fromBundle(requireArguments()).locationID)
    })

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
    }

    private fun setupObserver() {
        locationDetailViewModel.locationDetailLiveData.observe(viewLifecycleOwner) { resources ->
            resources ?: return@observe
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}