package com.example.stopwatch.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stopwatch.*
import com.example.stopwatch.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val timestampProvider = object : TimestampProvider {
        override fun getMillis(): Long = System.currentTimeMillis()
    }

    private val stopWatchListController = StopWatchListController(
        StopWatchStateHolder(
            StopWatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisFormatter()
        ),
        CoroutineScope(
            Dispatchers.Main
                    + SupervisorJob()
        )
    )

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
        scope.launch {
            stopWatchListController.ticker.collect {
                binding.textTime.text = it
            }
        }

        binding.buttonStart.setOnClickListener {
            stopWatchListController.start()
        }
        binding.buttonPause.setOnClickListener {
            stopWatchListController.pause()
        }
        binding.buttonStop.setOnClickListener {
            stopWatchListController.stop()
        }
    }
}