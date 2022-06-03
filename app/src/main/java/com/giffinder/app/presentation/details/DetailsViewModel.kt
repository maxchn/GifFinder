package com.giffinder.app.presentation.details

import com.giffinder.app.core.presentation.BaseViewModel
import com.giffinder.app.presentation.details.navigator.DetailsScreenNavigator

class DetailsViewModel(private val navigator: DetailsScreenNavigator) : BaseViewModel() {

    fun onClickBack() {
        navigator.pop()
    }
}
