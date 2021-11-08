package com.example.stopwatch

// Класс описывает состояния секундомера
sealed class StopWatchState {

    data class Paused(val elapsedTime: Long): StopWatchState()
    data class Running(val startTime: Long, val elapsedTime: Long): StopWatchState()
}
