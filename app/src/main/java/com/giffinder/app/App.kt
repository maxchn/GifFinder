package com.giffinder.app

import android.app.Application
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.DITrigger
import timber.log.Timber

class App : Application(), DIAware {
    override val di = DI.lazy {
        import(AppModule.module(this@App))
    }

    override val diTrigger = DITrigger()

    override fun onCreate() {
        super.onCreate()

        diTrigger.trigger()

        Timber.plant(Timber.DebugTree())
    }
}
