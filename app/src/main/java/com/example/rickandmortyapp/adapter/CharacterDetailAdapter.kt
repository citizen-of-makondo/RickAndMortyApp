package com.example.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.GetCharacterDetailResponse

class CharacterDetailAdapter : RecyclerView.Adapter<CharacterDetailAdapter.ViewHolder>() {
    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bind(data: GetCharacterDetailResponse) {
            nameTextView.text = data.name
            genderTextView.text = data.gender
            statusTextView.text = data.status
            specieTextView.text = data.species
            Glide.with(imageItemCharacter.context)
                .load(data.image)
                .into(imageItemCharacter)
        }

        private val imageItemCharacter = itemView.findViewById(R.id.imageView) as ImageView
        private val nameTextView =
            itemView.findViewById(R.id.nameCharacterDetailTextView) as TextView
        private val genderTextView =
            itemView.findViewById(R.id.genderCharacterDetailTextView) as TextView
        private val statusTextView =
            itemView.findViewById(R.id.statusCharacterDetailTextView) as TextView
        private val specieTextView =
            itemView.findViewById(R.id.specieCharacterDetailTextView) as TextView
    }

    lateinit var data: GetCharacterDetailResponse

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_character_detail, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data)
    }

    override fun getItemCount() = 1
}