package com.devrachit.swipeassignment

import android.app.Application
import com.devrachit.swipeassignment.di.appModule
import com.devrachit.swipeassignment.di.initializerModule
import com.devrachit.swipeassignment.di.useCaseModule
import com.devrachit.swipeassignment.di.viewmodelModule
import com.devrachit.swipeassignment.di.workModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // create koin dependencies.
            androidContext(this@Application)
            modules( useCaseModule, initializerModule,appModule, viewmodelModule, workModule)
            this.printLogger(level = org.koin.core.logger.Level.DEBUG)
            workManagerFactory()
        }

    }
}
