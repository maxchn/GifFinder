package com.giffinder.app.presentation.details

import com.giffinder.app.presentation.details.navigator.DetailsScreenCoordinator
import com.giffinder.app.presentation.details.navigator.DetailsScreenNavigator
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

object DetailsModule {
    fun get(fragment: DetailsFragment) = DI.Module(DetailsFragment::class.java.toString()) {
        bind<DetailsScreenNavigator>() with provider {
            DetailsScreenCoordinator(instance())
        }

        bind<DetailsViewModel>() with provider {
            fragment.vm(DetailsViewModelFactory(instance(), instance(), instance()))
        }
    }
}
