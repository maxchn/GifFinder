package com.giffinder.app.presentation.home.navigator

import com.giffinder.app.domain.entity.GifData

interface HomeScreenNavigator {
    fun pushDetailsScreen(items: Array<GifData>, selectedItemIndex: Int)
}
