package com.example.rickandmortyapp.ui.character

import android.app.SearchManager
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.adapter.CharacterAdapter
import com.example.rickandmortyapp.databinding.FragmentCharacterBinding
import com.example.rickandmortyapp.model.Character
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterFragment : Fragment() {
    private lateinit var navController: NavController

    private var recyclerView: RecyclerView? = null
    private var adapter: RecyclerView.Adapter<*>? = null
    var listOfusers: ArrayList<Character> = ArrayList()

    private val characterViewModel: CharacterViewModel by viewModel()
    private var _binding: FragmentCharacterBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        navController =
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main)
        setHasOptionsMenu(true)

        initViews(view)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //adding items in list
        for (i in 0..4) {
            val user = Character(i, "Персонаж $i")
            listOfusers.add(user)
        }
        recyclerView = view.findViewById(R.id.character_list)
        val layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView!!.layoutManager = layoutManager
        adapter = CharacterAdapter(listOfusers)
        recyclerView!!.adapter = adapter
    }

    private fun initViews(view: View?) = with(binding) {
        var characterList = view?.findViewById<RecyclerView>(R.id.character_list)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter_menu -> filterCharacter()
            R.id.search_menu -> searchCharacter(item)
        }
        return true
    }

    private fun searchCharacter(item: MenuItem) {
        val manager = requireActivity().getSystemService(AppCompatActivity.SEARCH_SERVICE) as SearchManager
        val searchView = item.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(requireActivity().componentName))

        searchView.queryHint = "Search"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun filterCharacter() {
        navController.navigate(CharacterFragmentDirections.actionNavigationCharacterToCharacterFilterFragment3())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}