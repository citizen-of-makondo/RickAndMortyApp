package com.example.rickandmortyapp.ui.character

import android.app.SearchManager
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.adapter.CharacterAdapter
import com.example.rickandmortyapp.databinding.FragmentCharacterBinding
import com.example.rickandmortyapp.modules.koin.PaginationScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterFragment : Fragment() {
    private val characterViewModel: CharacterViewModel by viewModel()
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

        val recyclerView = binding.characterList
        val layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = layoutManager
        val adapter = CharacterAdapter()
        recyclerView.adapter = adapter

        characterViewModel.listCharacter.observe(viewLifecycleOwner, Observer {
            adapter.updateData(it)
        })

        var isLoading = false

        recyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                getMoreItems()
            }

            private fun getMoreItems() {
                isLoading = false
                characterViewModel.getMoreData()
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