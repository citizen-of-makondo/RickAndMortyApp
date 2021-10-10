package com.example.rickandmortyapp.ui.episode

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.Episode

class EpisodeAdapter(private val onClick: (item: Episode, position: Int) -> Unit = { _, _ -> }) :
    RecyclerView.Adapter<EpisodeAdapter.ViewHolder>() {

    private val dataList = mutableListOf<Episode>()

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(episode: Episode) {
            nameTextView.text = episode.name
            airDateTextView.text = episode.airDate
            numberTextView.text = episode.episode
        }

        private val nameTextView = itemView.findViewById<TextView>(R.id.nameEpisodeItem)
        private val airDateTextView = itemView.findViewById<TextView>(R.id.airDateEpisodeItem)
        private val numberTextView = itemView.findViewById<TextView>(R.id.numberEpisodeItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_episode_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(dataList[position])
        holder.itemView.setOnClickListener { onClick(item, position) }
    }

    override fun getItemCount() = dataList.size

    fun updateData(newList: List<Episode>) {
        val characterDiffCallback = EpisodeDiffCallback(dataList, newList)
        val characterDiffResult = DiffUtil.calculateDiff(characterDiffCallback)
        dataList.clear()
        dataList.addAll(newList)
        characterDiffResult.dispatchUpdatesTo(this)
    }
}