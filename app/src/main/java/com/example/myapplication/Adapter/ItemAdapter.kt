package com.example.myapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemCheckboxBinding
import com.example.myapplication.model.LanguageItem

class ItemAdapter(
    private val onSelectionChanged: (Boolean) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private val items = mutableListOf(
        LanguageItem("English", "🇺🇸", false),
        LanguageItem("Hindi", "🇮🇳", false),
        LanguageItem("Bahasa Indonesia", "🇮🇩", false),
        LanguageItem("فارسی (Farsi)", "🇮🇷", false),
        LanguageItem("Português", "🇧🇷", false),
        LanguageItem("Français", "🇫🇷", false),
        LanguageItem("Español", "🇪🇸", false),
        LanguageItem("Bahasa Melayu", "🇲🇾", false)
    )

    inner class ViewHolder(
        val binding: ItemCheckboxBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LanguageItem) = with(binding) {
            tvItemName.text = item.title
            tvFlag.text = item.flag
            cbSelect.isChecked = item.isCheck

            root.setOnClickListener {
                item.isCheck = !item.isCheck
                cbSelect.isChecked = item.isCheck
                onSelectionChanged(items.any { it.isCheck })
            }

            cbSelect.setOnClickListener {
                item.isCheck = cbSelect.isChecked
                onSelectionChanged(items.any { it.isCheck })
            }

            card.setOnClickListener {
                item.isCheck = cbSelect.isChecked
                onSelectionChanged(items.any { it.isCheck })
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = ItemCheckboxBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
