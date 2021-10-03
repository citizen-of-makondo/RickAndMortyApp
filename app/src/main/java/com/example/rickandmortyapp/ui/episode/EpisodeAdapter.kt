package com.example.rickandmortyapp.ui.episode

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.Episode

class EpisodeAdapter(private val onItemClicked: (position: Int) -> Unit) :
    RecyclerView.Adapter<EpisodeAdapter.ViewHolder>() {

    private val dataList = mutableListOf<Episode>()

    class ViewHolder(itemView: View, private val onItemClicked: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(episode: Episode) {
            nameTextView.text = episode.name
            airDateTextView.text = episode.airDate
            numberTextView.text = episode.episode
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            onItemClicked(position + 1)
        }

        private val nameTextView = itemView.findViewById<TextView>(R.id.nameEpisodeItem)
        private val airDateTextView = itemView.findViewById<TextView>(R.id.airDateEpisodeItem)
        private val numberTextView = itemView.findViewById<TextView>(R.id.numberEpisodeItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_episode_item, parent, false)
        return ViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
        View.OnClickListener {
            dataList[position].name
        }
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