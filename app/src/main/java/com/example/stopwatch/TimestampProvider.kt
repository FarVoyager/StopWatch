package com.example.stopwatch

// Интерфейс для получения текущего времени (реализован в MainActivity)
interface TimestampProvider {
    fun getMillis(): Long
}

object TimestampProviderImpl : TimestampProvider {
    override fun getMillis(): Long = System.currentTimeMillis()
}