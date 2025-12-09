package com.example.myapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemSurveyBinding
import com.example.myapplication.model.TopicItem

class TopicAdapter(
    private val onSelectionChanged: (Int) -> Unit
) : RecyclerView.Adapter<TopicAdapter.ViewHolder>() {

    private val topics = mutableListOf(
        TopicItem("🕷️", "Marvel"),
        TopicItem("🦇", "DC"),
        TopicItem("📖", "Manga"),
        TopicItem("⚔️", "One Piece"),
        TopicItem("🎌", "Anime"),
        TopicItem("🌲", "Nature")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSurveyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(topics[position])
    }

    override fun getItemCount(): Int = topics.size

    fun getSelectedCount(): Int = topics.count { it.isSelected }

    inner class ViewHolder(
        private val binding: ItemSurveyBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(topic: TopicItem) = with(binding) {
            imgView.text = topic.img
            tvTopicName.text = topic.name
            cbSelect.isChecked = topic.isSelected

            root.setOnClickListener {
                topic.isSelected = !topic.isSelected
                cbSelect.isChecked = topic.isSelected
                onSelectionChanged(getSelectedCount())
            }

            cbSelect.setOnCheckedChangeListener { _, isChecked ->
                topic.isSelected = isChecked
                onSelectionChanged(getSelectedCount())
            }
        }
    }
}
