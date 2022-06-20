package com.giffinder.app.di

import com.giffinder.app.domain.usecase.gif.BlockGifUseCase
import com.giffinder.app.domain.usecase.gif.GetGifListUseCase
import com.giffinder.app.domain.usecase.gif.UpdateGifUseCase
import org.koin.dsl.module

val domainModule = module {
    // Use cases
    factory { GetGifListUseCase(repository = get()) }
    factory { UpdateGifUseCase(repository = get()) }
    factory { BlockGifUseCase(repository = get()) }
}
