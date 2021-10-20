package com.alexilinskiy.rickandmortyapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.alexilinskiy.rickandmortyapp.databinding.FragmentAboutBinding

class AboutMeFragment : Fragment() {
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.btnBuyMeACoffee.setOnClickListener {
            var uri: Uri = Uri.parse("https://www.buymeacoffee.com/makondo")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        binding.btnGAds.setOnClickListener {
            val action = AboutMeFragmentDirections.actionNavigationAboutMeToAdMobFragment()
            view?.let { view ->
                Navigation.findNavController(view).navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}