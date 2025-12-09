package com.example.myapplication.Firstopen

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.ItemAdapter
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityFirstopenBinding

class FirstopenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstopenBinding
    private val adapter = ItemAdapter { hasSelection ->
        if (hasSelection) {
            binding.btnNext.setBackgroundColor(Color.parseColor("#22B9FF"))
            binding.btnNext.isEnabled = true
            binding.btnNext.setTextColor(ContextCompat.getColor(this, R.color.white))
        } else {
            binding.btnNext.setBackgroundColor(Color.parseColor("#CCCCCC"))
            binding.btnNext.isEnabled = false
            binding.btnNext.setTextColor(ContextCompat.getColor(this, R.color.white))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFirstopenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupViews()
    }

    private fun setupViews() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        val adsView = layoutInflater.inflate(R.layout.item_ads, binding.frameAds, false)
        binding.frameAds.addView(adsView)

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, SurveyActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}