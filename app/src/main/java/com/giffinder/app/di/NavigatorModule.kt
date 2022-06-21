package com.giffinder.app.di

import com.giffinder.app.presentation.details.navigator.DetailsScreenCoordinator
import com.giffinder.app.presentation.details.navigator.DetailsScreenNavigator
import com.giffinder.app.presentation.home.navigator.HomeScreenCoordinator
import com.giffinder.app.presentation.home.navigator.HomeScreenNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NavigatorModule {

    @Binds
    abstract fun bindDetailsScreenNavigator(
        coordinator: DetailsScreenCoordinator
    ): DetailsScreenNavigator

    @Binds
    abstract fun bindHomeScreenNavigator(
        coordinator: HomeScreenCoordinator
    ): HomeScreenNavigator
}
