package com.example.catfacts.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.catfacts.core.load
import com.example.catfacts.databinding.ActivityMainBinding
import com.example.catfacts.ui.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        mainViewModel.onCreate()

        mainViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })

        mainViewModel.factModel.observe(this, Observer {
            binding.txtFact.text = it.fact
        })

        mainViewModel.randomPhoto.observe(this, Observer {
            binding.imgGato.setImageResource(it)
        })
        /*
        mainViewModel.randomUrl.observe(this, Observer {
            binding.imgGato.load(it)
        })*/

        setListeners()
    }

    private fun setListeners(){
        binding.viewContainer.setOnClickListener {
            mainViewModel.randomFact()
            mainViewModel.randomPhoto()}

        binding.viewContainerScrollView.setOnClickListener {
            mainViewModel.randomFact()
            mainViewModel.randomPhoto()}

        binding.btnGoToInsertFacts.setOnClickListener {
            goToInsertFactActivity()
        }
    }

    private fun goToInsertFactActivity(){
        val intent = Intent(this, InsertFactActivity::class.java)
        startActivity(intent)
    }

}