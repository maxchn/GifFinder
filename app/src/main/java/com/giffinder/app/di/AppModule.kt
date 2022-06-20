package com.giffinder.app.di

import com.giffinder.app.core.presentation.AndroidResourceReader
import com.giffinder.app.core.presentation.ResourceReader
import org.koin.dsl.module

val appModule = module {
    factory<ResourceReader> { AndroidResourceReader(get()) }
}
