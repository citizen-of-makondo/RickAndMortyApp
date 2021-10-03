package com.example.rickandmortyapp.ui.episode

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmortyapp.data.model.Episode

class EpisodeDiffCallback(
    var oldList: List<Episode>,
    var newList: List<Episode>,
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }
}