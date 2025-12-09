package com.example.myapplication.Firstopen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityObactivityBinding

class OBActivity : AppCompatActivity() {
    private lateinit var binding: ActivityObactivityBinding
    private var currentPage = 0

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
        updatePaginationDots()
    }

    private fun setupViews() {
        // Hiển thị item_ads.xml vào FrameLayout
        val adsView = layoutInflater.inflate(R.layout.item_ads, binding.frameAds, false)
        binding.frameAds.addView(adsView)

        binding.btnNext.setOnClickListener {
            currentPage = (currentPage + 1) % 3
            updatePaginationDots()
            
            if (currentPage == 0) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun updatePaginationDots() {
        binding.dot1.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                if (currentPage == 0) R.drawable.pagination_dot_active else R.drawable.pagination_dot_inactive
            )
        )
        
        binding.dot2.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                if (currentPage == 1) R.drawable.pagination_dot_active else R.drawable.pagination_dot_inactive
            )
        )
        
        binding.dot3.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                if (currentPage == 2) R.drawable.pagination_dot_active else R.drawable.pagination_dot_inactive
            )
        )
    }
}