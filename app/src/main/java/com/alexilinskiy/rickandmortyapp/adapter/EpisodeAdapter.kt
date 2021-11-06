package com.alexilinskiy.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexilinskiy.rickandmortyapp.BR
import com.alexilinskiy.rickandmortyapp.R
import com.alexilinskiy.rickandmortyapp.data.model.EpisodeDTO
import com.alexilinskiy.rickandmortyapp.ui.episode.EpisodeDiffCallback

class EpisodeAdapter(private val onClick: (item: EpisodeDTO) -> Unit = { _ -> }) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = mutableListOf<EpisodeDTO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BindingHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_episode_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataList[position]
        holder.itemView.setOnClickListener { onClick(item) }
        holder as BindingHolder
        holder.binding?.setVariable(BR.episode, item)
    }

    override fun getItemCount() = dataList.size

    fun updateData(newList: List<EpisodeDTO>) {
        val characterDiffCallback = EpisodeDiffCallback(dataList, newList)
        val characterDiffResult = DiffUtil.calculateDiff(characterDiffCallback)
        dataList.clear()
        dataList.addAll(newList)
        characterDiffResult.dispatchUpdatesTo(this)
    }
}