package com.example.stopwatch.di

import com.example.stopwatch.*
import com.example.stopwatch.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object KoinDI {
    fun getControllerModule() = module {

        factory<TimestampProvider> { TimestampProviderImpl }
        factory { TimestampMillisFormatter() }
        factory { ElapsedTimeCalculator(timestampProvider = get()) }

        factory { StopWatchStateCalculator(timestampProvider = get(), elapsedTimeCalculator = get()) }
        factory { CoroutineScope(Dispatchers.Main + SupervisorJob()) }
        factory { StopWatchStateHolder(stopWatchStateCalculator = get(),
            elapsedTimeCalculator = get(), timestampMillisFormatter = get()
        ) }
        factory { StopWatchListController(stopWatchStateHolder = get(), scope = get()) }


        viewModel { MainViewModel(stopWatchListController = get(),
            stopWatchListControllerSecond = get()
        ) }
    }

}