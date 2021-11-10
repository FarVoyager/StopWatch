package com.example.stopwatch

class StopWatchStateHolder(
    private val stopWatchStateCalculator: StopWatchStateCalculator,
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
    private val timestampMillisFormatter: TimestampMillisFormatter
) {
    var currentState: StopWatchState = StopWatchState.Paused(0)

    fun start() {
        currentState = stopWatchStateCalculator.calculateRunningState(currentState)
    }

    fun pause() {
        currentState = stopWatchStateCalculator.calculatePausedState(currentState)
    }

    fun stop() {
        currentState = StopWatchState.Paused(0)
    }

    fun getStringTimeRepresentation(): String {
        val elapsedTime = when (val currentState = currentState) {
            is StopWatchState.Paused -> currentState.elapsedTime
            is StopWatchState.Running -> elapsedTimeCalculator.calculate(currentState)
        }
        return timestampMillisFormatter.format(elapsedTime)
    }
}