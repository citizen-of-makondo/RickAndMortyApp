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
import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.ui.character.CharacterDiffCallback

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(character: Character) {
            nameTextView.text = character.name
            genderTextView.text = character.gender
            specieTextView.text = character.species
            statusTextView.text = character.status
            Glide.with(imageItemCharacter.context)
                .load(character.image)
                .into(imageItemCharacter)
        }

        val imageItemCharacter = itemView.findViewById(R.id.imageItemCharacter) as ImageView
        val nameTextView = itemView.findViewById(R.id.nameTextView) as TextView
        val genderTextView = itemView.findViewById(R.id.genderTextView) as TextView
        val statusTextView = itemView.findViewById(R.id.statusTextView) as TextView
        val specieTextView = itemView.findViewById(R.id.specieTextView) as TextView
    }

    val dataList = mutableListOf<Character>()

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

    fun updateData(newList: List<Character>) {
        val characterDiffCallback = CharacterDiffCallback(dataList, newList)
        val characterDiffResult = DiffUtil.calculateDiff(characterDiffCallback)
        dataList.clear()
        dataList.addAll(newList)
        characterDiffResult.dispatchUpdatesTo(this)
    }
}