package com.giffinder.app.presentation.home

import com.giffinder.app.presentation.home.navigator.HomeScreenCoordinator
import com.giffinder.app.presentation.home.navigator.HomeScreenNavigator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeScreenModule = module {

    factory<HomeScreenNavigator> { HomeScreenCoordinator() }

    viewModel {
        HomeViewModel(
            getGifListUseCase = get(),
            updateGifUseCase = get(),
            homeScreenNavigator = get()
        )
    }
}
