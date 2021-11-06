package com.alexilinskiy.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexilinskiy.rickandmortyapp.BR
import com.alexilinskiy.rickandmortyapp.R
import com.alexilinskiy.rickandmortyapp.data.model.CharacterDTO
import com.alexilinskiy.rickandmortyapp.ui.character.CharacterDiffCallback

class CharacterAdapter(var onClick: (item: CharacterDTO) -> Unit = { _ -> }) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = mutableListOf<CharacterDTO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BindingHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_character_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataList[position]
        holder.itemView.setOnClickListener { onClick(item) }
        holder as BindingHolder
        holder.binding?.setVariable(BR.character, item)
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