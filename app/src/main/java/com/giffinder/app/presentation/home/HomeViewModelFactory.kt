package com.giffinder.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.giffinder.app.domain.usecase.gif.GetGifListUseCase

class HomeViewModelFactory(
    private val getGifListUseCase: GetGifListUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(getGifListUseCase) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
