package com.example.stopwatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stopwatch.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val stopWatchListController: StopWatchListController
): ViewModel() {

    private val liveData: MutableLiveData<String> = MutableLiveData()

    fun getData() {
        val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
        scope.launch {
            stopWatchListController.ticker.collect {
                liveData.postValue(it)
            }
        }
    }

    fun subscribe(): LiveData<String> {
        return liveData
    }

    fun start() {
        stopWatchListController.start()
    }

    fun pause() {
        stopWatchListController.pause()
    }

    fun stop() {
        stopWatchListController.stop()
    }
}