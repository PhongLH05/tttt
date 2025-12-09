package com.example.myapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemObPageBinding
import com.example.myapplication.model.PageData

class OBPagerAdapter(
    private val pages: List<PageData>
) : RecyclerView.Adapter<OBPagerAdapter.PageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val binding = ItemObPageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(pages[position])
    }

    override fun getItemCount(): Int = pages.size

    inner class PageViewHolder(
        private val binding: ItemObPageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(page: PageData) {
            val contentView = LayoutInflater.from(binding.root.context)
                .inflate(R.layout.page_ob_content, binding.frameContent, false)
            
            binding.frameContent.removeAllViews()
            
            binding.frameContent.addView(contentView)

            val imgView = contentView.findViewById<android.widget.ImageView>(R.id.img_content)
            imgView?.setImageResource(page.imageRes)
        }
    }
}

