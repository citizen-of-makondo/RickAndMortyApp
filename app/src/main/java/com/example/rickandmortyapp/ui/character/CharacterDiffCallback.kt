package com.example.rickandmortyapp.ui.character

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmortyapp.data.model.Character

class CharacterDiffCallback(
    var oldList: List<Character>,
    var newList: List<Character>
): DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }
}