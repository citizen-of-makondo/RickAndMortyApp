package com.example.rickandmortyapp.ui.character

import android.app.SearchManager
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.adapter.CharacterAdapter
import com.example.rickandmortyapp.databinding.FragmentCharacterBinding
import com.example.rickandmortyapp.model.LoadStatusEnum
import com.example.rickandmortyapp.modules.koin.PaginationScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

const val BUNDLE_FILTER_KEY = "bundleFromFilterToViewKey"
const val BUNDLE_CHARACTER_KEY = "bundleFromViewToFilterKey"

class CharacterFragment : Fragment() {
    private val characterViewModel: CharacterViewModel by viewModel()

    private lateinit var adapter: CharacterAdapter
    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(REQUEST_FILTER_KEY) { _, bundle ->
            characterViewModel.filterList =
                bundle.getSerializable(BUNDLE_FILTER_KEY) as ArrayList<Filter>
            characterViewModel.setPageAndGetData()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        binding.viewmodel = characterViewModel
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
        characterViewModel.charactersLiveData.observe(viewLifecycleOwner) { resource ->
            resource ?: return@observe
            when (resource.statusEnum) {
                LoadStatusEnum.SUCCESS -> {
                    resource.data?.let {
                        adapter.updateData(it)
                        if (it.isNullOrEmpty()) {
                            binding.characterList.visibility = View.GONE
                            binding.noResultsTextView.visibility = View.VISIBLE
                        }
                    }
                }
                LoadStatusEnum.ERROR -> {
                    binding.noResultsTextView.setText(getString(R.string.error))
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
                return characterViewModel.loadingLiveData.value ?: return false
            }

            override fun loadMoreItems() {
                getMoreItems()
            }

            private fun getMoreItems() {
                characterViewModel.getCharacterList()
            }
        })

        binding.resetFAB.setOnClickListener {
            characterViewModel.filterList.clear()
            characterViewModel.setPageAndGetData()
            layoutManager.scrollToPosition(0)
        }
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
                    newText?.let {
                        characterViewModel.searchCharactersByName(newText)
                    }
                    return true
                }
            })

            if (this.visibility == View.GONE) {
                characterViewModel.filterList.clear()
                characterViewModel.setPageAndGetData()
            }
        }
    }

    private fun filterCharacterNavigation() {
        val bundle = Bundle()
        bundle.putSerializable(BUNDLE_CHARACTER_KEY, characterViewModel.filterList)
        setFragmentResult(REQUEST_CHARACTER_KEY, bundle)
        view?.let {
            Navigation.findNavController(it)
                .navigate(CharacterFragmentDirections.actionNavigationCharacterToCharacterFilterFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}