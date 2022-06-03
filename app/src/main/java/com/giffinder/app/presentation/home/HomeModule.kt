package com.giffinder.app.presentation.home

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

object HomeModule {

    fun get(fragment: HomeFragment) = DI.Module(HomeFragment::class.java.toString()) {

        bind<HomeViewModel>() with provider { fragment.vm(HomeViewModelFactory(instance())) }
    }
}
