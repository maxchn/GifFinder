package com.giffinder.app.presentation.details

import com.giffinder.app.presentation.details.navigator.DetailsScreenCoordinator
import com.giffinder.app.presentation.details.navigator.DetailsScreenNavigator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsScreenModule = module {
    factory<DetailsScreenNavigator> {
        DetailsScreenCoordinator()
    }

    viewModel {
        DetailsViewModel(
            navigator = get(),
            blockGifUseCase = get()
        )
    }
}
