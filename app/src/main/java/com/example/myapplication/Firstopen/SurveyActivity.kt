package com.example.myapplication.Firstopen

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.Adapter.TopicAdapter
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySurveyBinding

class SurveyActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySurveyBinding
    private val adapter = TopicAdapter { selectedCount ->
        val hasEnoughSelection = selectedCount >= 2
        if (hasEnoughSelection) {
            binding.btnNext.setBackgroundColor(Color.parseColor("#22B9FF"))
            binding.btnNext.isEnabled = true
            binding.btnNext.setTextColor(ContextCompat.getColor(this, R.color.white))
        } else {
            binding.btnNext.setBackgroundColor(Color.parseColor("#0000002B"))
            binding.btnNext.isEnabled = false
            binding.btnNext.setTextColor(ContextCompat.getColor(this, R.color.white))
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupViews()
    }

    private fun setupViews() {
        binding.rvTopics.layoutManager = GridLayoutManager(this, 2)
        binding.rvTopics.adapter = adapter

        val adsView = layoutInflater.inflate(R.layout.item_survey_ads, binding.frameAds, false)
        binding.frameAds.addView(adsView)

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, OBActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}