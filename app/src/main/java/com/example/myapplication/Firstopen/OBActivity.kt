package com.example.myapplication.Firstopen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.Adapter.OBPagerAdapter
import com.example.myapplication.model.PageData
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityObactivityBinding

class OBActivity : AppCompatActivity() {
    private lateinit var binding: ActivityObactivityBinding
    private var currentPage = 0

    val pages = listOf(
        PageData(
            "Diverse Styles",
            "Diverse styles offer unique designs, catering to various tastes and preferences for every occasion.",
            R.drawable.a9
        ),
        PageData(
            "Pin Lock Screen",
            "Pin Lock Screen offers secure access with customizable designs for enhanced privacy and style.",
            R.drawable.a6
        ),
        PageData(
            "Magic Zipper Interface",
            "The Zipper effect is not only beautiful but also interactive",
            R.drawable.a5
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityObactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupViews()
        updatePageContent(0)
        updatePaginationDots(0)
    }

    private fun setupViews() {
        val adapter = OBPagerAdapter(pages)
        binding.viewPager.adapter = adapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPage = position
                updatePageContent(position)
                updatePaginationDots(position)
            }
        })

        val adsView = layoutInflater.inflate(R.layout.item_ads, binding.frameAds, false)
        binding.frameAds.addView(adsView)

        binding.btnNext.setOnClickListener {
            if (currentPage < pages.size - 1) {
                binding.viewPager.currentItem = currentPage + 1
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun updatePageContent(position: Int) {
        val page = pages[position]
        binding.txTitle.text = page.title
        binding.tvDes.text = page.description
    }

    private fun updatePaginationDots(position: Int) {
        binding.dot1.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                if (position == 0) R.drawable.pagination_dot_active else R.drawable.pagination_dot_inactive
            )
        )
        
        binding.dot2.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                if (position == 1) R.drawable.pagination_dot_active else R.drawable.pagination_dot_inactive
            )
        )
        
        binding.dot3.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                if (position == 2) R.drawable.pagination_dot_active else R.drawable.pagination_dot_inactive
            )
        )
    }
}