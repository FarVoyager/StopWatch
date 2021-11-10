package com.example.stopwatch

// Класс для расчета интервала времени между стартом и паузой
class ElapsedTimeCalculator(private val timestampProvider: TimestampProvider) {
    fun calculate(state: StopWatchState.Running): Long {
        val currentTimestamp = timestampProvider.getMillis()
        val timePassedSinceLastStart = if (currentTimestamp > state.startTime) {
            currentTimestamp - state.startTime
        } else {
            0
        }
        return timePassedSinceLastStart + state.elapsedTime
    }
}