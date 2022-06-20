package com.giffinder.app.presentation.details.navigator

import com.giffinder.app.presentation.common.NavControllerProvider

class DetailsScreenCoordinator : DetailsScreenNavigator {

    override fun pop() {
        NavControllerProvider.get().navigateUp()
    }
}
