package com.example.rickandmortyapp.ui.character

import android.app.SearchManager
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.adapter.CharacterAdapter
import com.example.rickandmortyapp.data.model.CharacterDTO
import com.example.rickandmortyapp.databinding.FragmentCharacterBinding
import com.example.rickandmortyapp.model.LoadStatus
import com.example.rickandmortyapp.modules.koin.PaginationScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterFragment : Fragment() {
    var isLoading = false

    val characterViewModel: CharacterViewModel by viewModel()

    private lateinit var adapter: CharacterAdapter
    private var _binding: FragmentCharacterBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupObservers()
    }

    private fun setupObservers() = with(binding) {
        characterViewModel.charactersLiveData.observe(viewLifecycleOwner) { resource ->
            resource ?: return@observe
            when (resource.status) {
                LoadStatus.SUCCESS -> {
                    resource.data?.let {
                        val characterListData: MutableList<CharacterDTO> = mutableListOf()
                        characterListData.addAll(it)
                        adapter.updateData(characterListData)
                        isLoading = false
                    }
                }
                LoadStatus.ERROR -> {
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                    isLoading = false
                }
            }
        }

        characterViewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            when (isLoading) {
                true -> {
                    progressBar.visibility = View.VISIBLE
                    characterList.visibility = View.GONE
                }
                false -> {
                    characterList.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun setupUI() {
        val recyclerView = binding.characterList
        val layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = layoutManager
        adapter = CharacterAdapter()
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                getMoreItems()
            }

            private fun getMoreItems() {
                characterViewModel.getUsers(characterViewModel.countPages)
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
                filterCharacterNavigation(item)
                true
            }
            R.id.search_menu -> {
                searchCharacter(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun searchCharacter(item: MenuItem) {
        val manager =
            requireActivity().getSystemService(AppCompatActivity.SEARCH_SERVICE) as SearchManager
        (item.actionView as SearchView).apply {
            setSearchableInfo(manager.getSearchableInfo(requireActivity().componentName))
            queryHint = "Search"

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
    }

    fun filterCharacterNavigation(item: MenuItem) {
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main)
            .navigate(CharacterFragmentDirections.actionNavigationCharacterToCharacterFilterFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}