package com.alexilinskiy.rickandmortyapp.ui.character

import android.app.SearchManager
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexilinskiy.rickandmortyapp.R
import com.alexilinskiy.rickandmortyapp.adapter.CharacterAdapter
import com.alexilinskiy.rickandmortyapp.databinding.FragmentCharacterBinding
import com.alexilinskiy.rickandmortyapp.model.LoadStatusEnum
import com.alexilinskiy.rickandmortyapp.modules.koin.PaginationScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

const val BUNDLE_FILTER_KEY = "bundleFromFilterToViewKey"

class CharacterFragment : Fragment() {
    private val viewModel: CharacterViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: CharacterAdapter
    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(REQUEST_FILTER_KEY) { _, bundle ->
            viewModel.characterFilterList =
                bundle.getSerializable(BUNDLE_FILTER_KEY) as ArrayList<CharacterFilter>
            viewModel.setPageAndGetData()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.charactersLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource.statusEnum) {
                LoadStatusEnum.SUCCESS -> {
                    adapter.updateData(resource.data!!)
                }
                LoadStatusEnum.ERROR -> {
                    binding.characterList.visibility = View.GONE
                    binding.noResultsTextView.visibility = View.VISIBLE
                    binding.noResultsTextView.text = getString(R.string.error)
                }
            }
        }
    }

    private fun setupUI() {
        recyclerView = binding.characterList
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        adapter = CharacterAdapter { character -> detailCharacterNavigation(character.id) }
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLoading(): Boolean {
                return viewModel.loadingLiveData.value ?: return false
            }

            override fun loadMoreItems() {
                getMoreItems()
            }

            private fun getMoreItems() {
                viewModel.getCharacterList()
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
                filterCharacterNavigation()
                true
            }
            R.id.search_menu -> {
                searchCharacter(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun searchCharacter(item: MenuItem) {
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
                    if (newText.isNullOrEmpty()) {
                        viewModel.characterFilterList.clear()
                        viewModel.setPageAndGetData()
                    } else {
                        viewModel.searchCharactersByName(newText)
                    }
                    return true
                }
            })

            setOnCloseListener {
                viewModel.characterFilterList.clear()
                viewModel.setPageAndGetData()
                false
            }
        }
    }

    private fun filterCharacterNavigation() {
        view?.let {
            val filterList = CharacterFilter.FilterList()
            filterList.addAll(viewModel.characterFilterList)
            val action =
                CharacterFragmentDirections.actionNavigationCharacterToCharacterFilterFragment(
                    filter = filterList)
            Navigation.findNavController(it)
                .navigate(action)
        }
    }

    private fun detailCharacterNavigation(character: Int) {
        val action = CharacterFragmentDirections.actionNavigationCharacterToCharacterDetailFragment(
            characterID = character)
        view?.let {
            Navigation.findNavController(it)
                .navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}