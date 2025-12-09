package com.example.myapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemSurveyBinding
import com.example.myapplication.model.TopicItem

class TopicAdapter(
    private val onSelectionChanged: (Int) -> Unit
) : RecyclerView.Adapter<TopicAdapter.ViewHolder>() {

    private val topics = mutableListOf(
        TopicItem(R.drawable.a3, "Marvel"),
        TopicItem(R.drawable.a4, "DC"),
        TopicItem(R.drawable.a5, "Manga"),
        TopicItem(R.drawable.a6, "One Piece"),
        TopicItem(R.drawable.a7, "Anime"),
        TopicItem(R.drawable.a9, "Nature")
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
            imgView.setImageResource(topic.img)
            tvTopicName.text = topic.name
            cbSelect.isChecked = topic.isSelected

            cardContainer.background = ContextCompat.getDrawable(
                root.context,
                if (topic.isSelected) R.drawable.card_border_selected else R.drawable.card_border_unselected
            )

            root.setOnClickListener {
                topic.isSelected = !topic.isSelected
                cbSelect.isChecked = topic.isSelected

                onSelectionChanged(getSelectedCount())
            }

            cardContainer.setOnClickListener {
                topic.isSelected = !topic.isSelected
                cbSelect.isChecked = topic.isSelected
                onSelectionChanged(getSelectedCount())
            }

            cbSelect.setOnCheckedChangeListener(null)
        }
    }
}
