package com.example.catfacts.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.catfacts.R
import com.example.catfacts.databinding.ActivityInsertFactBinding

class InsertFactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInsertFactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInsertFactBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners(){
        binding.btnBackToFacts.setOnClickListener {
            finish()
        }
    }
}