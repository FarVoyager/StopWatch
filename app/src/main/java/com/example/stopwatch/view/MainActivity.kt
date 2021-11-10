package com.example.stopwatch.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stopwatch.databinding.ActivityMainBinding
import com.example.stopwatch.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), MainView {

    private val viewmodel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //подписываемся на изменения от двух источников, каждый по отдельному секундомеру
        viewmodel.subscribeInitial().observe(this, { renderData(it) })
        viewmodel.subscribeSecond().observe(this, { renderDataSecond(it) })
        viewmodel.initScope()

        binding.buttonStart.setOnClickListener { viewmodel.start() }
        binding.buttonPause.setOnClickListener { viewmodel.pause() }
        binding.buttonStop.setOnClickListener { viewmodel.stop() }

        binding.buttonStart2.setOnClickListener { viewmodel.startSecond() }
        binding.buttonPause2.setOnClickListener { viewmodel.pauseSecond() }
        binding.buttonStop2.setOnClickListener { viewmodel.stopSecond() }
    }

    override fun renderData(data: String) {
        binding.textTime.text = data
    }

    override fun renderDataSecond(data: String) {
        binding.textTime2.text = data
    }


}