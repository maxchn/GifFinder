package com.giffinder.app.presentation.details.navigator

import com.giffinder.app.presentation.common.NavControllerProvider
import javax.inject.Inject

class DetailsScreenCoordinator @Inject constructor() : DetailsScreenNavigator {

    override fun pop() {
        NavControllerProvider.get().navigateUp()
    }
}
