package com.giffinder.app

import android.content.Context
import com.giffinder.app.core.presentation.AndroidResourceReader
import com.giffinder.app.core.presentation.ResourceReader
import com.giffinder.app.data.di.DataModule
import com.giffinder.app.data.di.RetrofitModule
import com.giffinder.app.domain.di.DomainModule
import org.kodein.di.DI
import org.kodein.di.android.androidCoreModule
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton

object AppModule {

    fun module(application: App) = DI.Module("AppModule") {
        import(androidCoreModule(application))
        import(RetrofitModule.get())
        import(DataModule.get())
        import(DomainModule.get())

        bind<Context>() with provider { application.applicationContext }
        bind<String>(tag = "appPackageName") with singleton { application.packageName }
        bind<ResourceReader>() with provider {
            AndroidResourceReader(
                instance()
            )
        }
    }
}
