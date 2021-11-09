package com.example.stopwatch.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stopwatch.*
import com.example.stopwatch.databinding.ActivityMainBinding
import com.example.stopwatch.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewmodel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewmodel.getData()
        viewmodel.subscribe().observe(this, { renderData(it) })

        binding.buttonStart.setOnClickListener { viewmodel.start() }
        binding.buttonPause.setOnClickListener { viewmodel.pause() }
        binding.buttonStop.setOnClickListener { viewmodel.stop() }
    }

    private fun renderData(data: String) {
        binding.textTime.text = data
    }
}