package com.example.rickandmortyapp.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyapp.databinding.FragmentLocationBinding

class LocationFragment : Fragment() {

    private lateinit var locationViewModel: LocationViewModel
    private var binding: FragmentLocationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        locationViewModel =
            ViewModelProvider(this).get(LocationViewModel::class.java)

        binding = FragmentLocationBinding.inflate(inflater, container, false)

        initViews()

        return binding!!.root
    }

    private fun initViews() {
        val textView = binding?.textLocation
        locationViewModel.text.observe(viewLifecycleOwner, Observer {
            textView?.run {
                textView.text = it
            }
        })
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}