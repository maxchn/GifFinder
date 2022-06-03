package com.giffinder.app.presentation.home.navigator

import com.giffinder.app.domain.entity.GifData
import com.giffinder.app.presentation.common.NavControllerProvider
import com.giffinder.app.presentation.home.HomeFragmentDirections

class HomeScreenCoordinator(
    private val navControllerProvider: NavControllerProvider
) : HomeScreenNavigator {

    override fun pushDetailsScreen(items: Array<GifData>, selectedItemIndex: Int) {
        navControllerProvider.get().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                items = items,
                selectedItemIndex = selectedItemIndex
            )
        )
    }
}
