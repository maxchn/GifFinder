package com.giffinder.app.domain.di

import com.giffinder.app.domain.usecase.gif.GetGifListUseCase
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

object DomainModule {

    fun get() = DI.Module("DomainModule") {
        applyUseCaseModule()
    }

    private fun DI.Builder.applyUseCaseModule() {
        bind<GetGifListUseCase>() with provider {
            GetGifListUseCase(instance())
        }
    }
}
