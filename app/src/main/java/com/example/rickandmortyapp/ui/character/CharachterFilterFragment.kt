package com.example.rickandmortyapp.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.rickandmortyapp.R

class CharachterFilterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViews(view)
        return inflater.inflate(R.layout.fragment_charachter_filter, container, false)
    }

    private fun initViews(view: View?) {
        val useFilterButton = view?.findViewById<Button>(R.id.use_filter)
        useFilterButton?.setOnClickListener {

        }
    }
}