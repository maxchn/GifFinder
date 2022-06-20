package com.giffinder.app

import android.app.Application
import com.giffinder.app.di.appModule
import com.giffinder.app.di.dataModule
import com.giffinder.app.di.domainModule
import com.giffinder.app.di.retrofitModule
import com.giffinder.app.presentation.details.detailsScreenModule
import com.giffinder.app.presentation.home.homeScreenModule
import com.giffinder.app.presentation.main.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    retrofitModule,
                    dataModule,
                    domainModule,
                    mainModule,
                    homeScreenModule,
                    detailsScreenModule
                )
            )
        }

        Timber.plant(Timber.DebugTree())
    }
}
