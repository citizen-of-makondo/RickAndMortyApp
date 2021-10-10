package com.example.rickandmortyapp.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.adapter.LocationAdapter
import com.example.rickandmortyapp.databinding.FragmentLocationBinding
import com.example.rickandmortyapp.model.LoadStatusEnum
import com.example.rickandmortyapp.modules.koin.PaginationScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationFragment : Fragment() {
    private val locationViewModel: LocationViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LocationAdapter

    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLocationBinding.inflate(inflater, container, false)

        binding.viewmodel = locationViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupObservers()
    }

    private fun setupObservers() {
        locationViewModel.locationsLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource.statusEnum) {
                LoadStatusEnum.SUCCESS -> {
                    resource.data?.let {
                        adapter.updateData(it)
                        /*
                            if (it.isNullOrEmpty()) {
                                binding.characterList.visibility = View.GONE
                                binding.noResultsTextView.visibility = View.VISIBLE
                            }*/
                    }
                }
                LoadStatusEnum.ERROR -> {
                    //binding.noResultsTextView.setText(getString(R.string.error))
                }
            }
        }
    }

    private fun setupUI() {
        recyclerView = binding.locationList
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        adapter = LocationAdapter { location -> detailLocationNavigation(location.id) }
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLoading(): Boolean {
                return locationViewModel.loadingLiveData.value ?: return false
            }

            override fun loadMoreItems() {
                getMoreItems()
            }

            private fun getMoreItems() {
                locationViewModel.getLocationList()
            }
        })
    }

    private fun detailLocationNavigation(location: Int) {
        val action =
            LocationFragmentDirections.actionNavigationLocationToLocationDetailFragment(location)
        view?.let {
            Navigation.findNavController(it).navigate(action)
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}