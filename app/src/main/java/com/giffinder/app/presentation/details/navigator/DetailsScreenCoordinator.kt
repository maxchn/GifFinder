package com.giffinder.app.presentation.details.navigator

import com.giffinder.app.presentation.common.NavControllerProvider

class DetailsScreenCoordinator(
    private val navControllerProvider: NavControllerProvider
) : DetailsScreenNavigator {

    override fun pop() {
        navControllerProvider.get().navigateUp()
    }
}
