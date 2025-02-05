package com.devrachit.swipeassignment

import android.app.Application
import android.content.Context
import com.devrachit.swipeassignment.di.appModule
import com.devrachit.swipeassignment.di.initializerModule
import com.devrachit.swipeassignment.di.useCaseModule
import com.devrachit.swipeassignment.di.viewmodelModule
import com.devrachit.swipeassignment.di.workModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import java.lang.ref.WeakReference

class Application : Application() {
    companion object {
        var context: WeakReference<Context>? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = WeakReference(applicationContext)
        startKoin {
            // create koin dependencies.
            androidContext(this@Application)
            modules( useCaseModule, initializerModule,appModule, viewmodelModule, workModule)
            this.printLogger(level = org.koin.core.logger.Level.DEBUG)
            workManagerFactory()
        }

    }
}
