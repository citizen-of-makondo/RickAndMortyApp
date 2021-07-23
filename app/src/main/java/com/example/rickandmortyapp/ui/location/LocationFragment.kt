package com.example.rickandmortyapp.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.rickandmortyapp.databinding.FragmentLocationBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationFragment : Fragment() {
    private val locationViewModel: LocationViewModel by viewModel()
    private var _binding: FragmentLocationBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationBinding.inflate(inflater, container, false)

        initViews()

        return binding.root
    }

    private fun initViews() {
        val textView = binding.textLocation
        locationViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}