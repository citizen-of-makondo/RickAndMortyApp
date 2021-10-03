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

class CharacterAdapter(private val onItemClicked: (position: Int) -> Unit) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, private val onItemClicked: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(character: Character) {
           nameTextView.text = character.name
            genderTextView.text = character.gender
            specieTextView.text = character.species
            statusTextView.text = character.status
            Glide.with(imageItemCharacter.context)
                .load(character.image)
                .into(imageItemCharacter)
        }

        private val imageItemCharacter = itemView.findViewById(R.id.imageItemCharacter) as ImageView
        private val nameTextView = itemView.findViewById(R.id.nameTextView) as TextView
        private val genderTextView = itemView.findViewById(R.id.genderTextView) as TextView
        private val statusTextView = itemView.findViewById(R.id.statusTextView) as TextView
        private val specieTextView = itemView.findViewById(R.id.specieTextView) as TextView
        override fun onClick(v: View?) {
            val position = adapterPosition
            onItemClicked(position + 1)
        }
    }

    private val dataList = mutableListOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_character_item, parent, false)
        return ViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
        View.OnClickListener {
            dataList[position].image
        }
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