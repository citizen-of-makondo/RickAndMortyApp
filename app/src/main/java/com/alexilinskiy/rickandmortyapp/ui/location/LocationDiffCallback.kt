package com.alexilinskiy.rickandmortyapp.ui.location

import androidx.recyclerview.widget.DiffUtil
import com.alexilinskiy.rickandmortyapp.data.model.LocationDTO

class LocationDiffCallback(var oldList: List<LocationDTO>, var newList: List<LocationDTO>) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

}
