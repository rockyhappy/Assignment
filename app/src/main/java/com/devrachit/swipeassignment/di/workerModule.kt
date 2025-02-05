package com.devrachit.swipeassignment.di

import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.dsl.module
import com.devrachit.swipeassignment.domain.workers.BackGroundWorker
val workModule = module {
    workerOf(::BackGroundWorker)
}