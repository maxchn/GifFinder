package com.giffinder.app.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.giffinder.app.presentation.details.navigator.DetailsScreenNavigator

class DetailsViewModelFactory(
    private val navigator: DetailsScreenNavigator
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(navigator) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
