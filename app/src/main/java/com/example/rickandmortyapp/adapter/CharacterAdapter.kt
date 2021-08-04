package com.example.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.CharacterDTO
import com.example.rickandmortyapp.ui.character.CharacterDiffCallback

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(characterDTO: CharacterDTO) {
            nameTextView.text = characterDTO.name
            genderTextView.text = characterDTO.gender
            specieTextView.text = characterDTO.species
            statusTextView.text = characterDTO.status
            Glide.with(imageItemCharacter.context)
                .load(characterDTO.image)
                .into(imageItemCharacter)
        }

        val imageItemCharacter = itemView.findViewById<ImageView>(R.id.imageItemCharacter)
        val nameTextView = itemView.findViewById(R.id.nameTextView) as TextView
        val genderTextView = itemView.findViewById(R.id.genderTextView) as TextView
        val statusTextView = itemView.findViewById(R.id.statusTextView) as TextView
        val specieTextView = itemView.findViewById(R.id.specieTextView) as TextView
    }

    val dataList = mutableListOf<CharacterDTO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_character_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    fun updateData(newList: List<CharacterDTO>) {
        val characterDiffCallback = CharacterDiffCallback(dataList, newList)
        val characterDiffResult = DiffUtil.calculateDiff(characterDiffCallback)
        dataList.clear()
        dataList.addAll(newList)
        characterDiffResult.dispatchUpdatesTo(this)
    }
}