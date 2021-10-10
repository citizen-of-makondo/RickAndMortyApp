package com.example.rickandmortyapp.ui.episode

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.adapter.EpisodeAdapter
import com.example.rickandmortyapp.databinding.FragmentEpisodeBinding
import com.example.rickandmortyapp.model.LoadStatusEnum
import com.example.rickandmortyapp.modules.koin.PaginationScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment : Fragment() {
    private val episodeViewModel: EpisodeViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
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
        recyclerView = binding.episodeList
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        adapter = EpisodeAdapter { episode -> detailEpisodeNavigation(episode.id) }
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_menu -> {
                filterLocationNavigation()
                true
            }
            R.id.search_menu -> {
                searchLocation(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun searchLocation(item: MenuItem) {
        TODO("Сделать поиск по локации")
    }

    private fun filterLocationNavigation() {
        TODO("Сделать фильтр по локации")
    }

    private fun detailEpisodeNavigation(episodeID: Int) {
        val action = EpisodeFragmentDirections.actionNavigationEpisodeToEpisodeDetailFragment(
            episodeID)
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