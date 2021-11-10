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
    private val stopWatchListController: StopWatchListController,
    private val stopWatchListControllerSecond: StopWatchListController
): ViewModel() {

    private val liveDataInitial: MutableLiveData<String> = MutableLiveData()
    private val liveDataSecond: MutableLiveData<String> = MutableLiveData()

    fun initScope() {
        val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
        //две джобы для двух секундомеров
        scope.launch {
            stopWatchListController.ticker.collect {
                liveDataInitial.postValue(it)
            }
        }
        scope.launch {
            stopWatchListControllerSecond.ticker.collect {
                liveDataSecond.postValue(it)
            }
        }
    }

    fun subscribeInitial(): LiveData<String> {
        return liveDataInitial
    }
    fun subscribeSecond(): LiveData<String> {
        return liveDataSecond
    }

    fun start() { stopWatchListController.start() }
    fun pause() { stopWatchListController.pause() }
    fun stop() { stopWatchListController.stop() }

    fun stopSecond() { stopWatchListControllerSecond.stop() }
    fun startSecond() { stopWatchListControllerSecond.start() }
    fun pauseSecond() { stopWatchListControllerSecond.pause() }


}