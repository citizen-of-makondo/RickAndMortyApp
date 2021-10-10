package com.example.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.BR
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.LocationDTO
import com.example.rickandmortyapp.ui.location.LocationDiffCallback

class LocationAdapter(private val onClick: (item: LocationDTO) -> Unit = { _ -> }) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = mutableListOf<LocationDTO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BindingHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_location_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataList[position]
        holder.itemView.setOnClickListener { onClick(item) }
        holder as BindingHolder
        holder.binding?.setVariable(BR.location, item)
    }

    override fun getItemCount() = dataList.size

    fun updateData(newList: List<LocationDTO>) {
        val locationDiffCallback = LocationDiffCallback(dataList, newList)
        val locationDiffResult = DiffUtil.calculateDiff(locationDiffCallback)
        dataList.clear()
        dataList.addAll(newList)
        locationDiffResult.dispatchUpdatesTo(this)
    }
}