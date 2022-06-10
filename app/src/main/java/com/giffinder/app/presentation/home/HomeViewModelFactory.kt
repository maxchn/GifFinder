package com.giffinder.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.giffinder.app.domain.usecase.gif.GetGifListUseCase
import com.giffinder.app.domain.usecase.gif.UpdateGifUseCase
import com.giffinder.app.presentation.home.navigator.HomeScreenNavigator

class HomeViewModelFactory(
    private val getGifListUseCase: GetGifListUseCase,
    private val updateGifUseCase: UpdateGifUseCase,
    private val homeScreenNavigator: HomeScreenNavigator
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                getGifListUseCase = getGifListUseCase,
                updateGifUseCase = updateGifUseCase,
                homeScreenNavigator = homeScreenNavigator
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
