package com.giffinder.app.presentation.home

import com.giffinder.app.presentation.home.navigator.HomeScreenCoordinator
import com.giffinder.app.presentation.home.navigator.HomeScreenNavigator
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

object HomeModule {

    fun get(fragment: HomeFragment) = DI.Module(HomeFragment::class.java.toString()) {
        bind<HomeScreenNavigator>() with provider {
            HomeScreenCoordinator(instance())
        }

        bind<HomeViewModel>() with provider {
            fragment.vm(
                HomeViewModelFactory(
                    instance(),
                    instance(),
                    instance()
                )
            )
        }
    }
}
