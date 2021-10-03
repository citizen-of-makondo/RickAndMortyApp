package com.example.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.Episode

class CharacterDetailAdapter : RecyclerView.Adapter<CharacterDetailAdapter.ViewHolder>() {

    val dataList = mutableListOf<Episode>()
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Episode) {
            nameTextView.text = data.name
            airDateTextView.text = data.airDate
        }

        private val nameTextView = itemView.findViewById(R.id.nameEpisodeItem) as TextView
        private val airDateTextView = itemView.findViewById(R.id.airDateEpisodeItem) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_episode_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size
}