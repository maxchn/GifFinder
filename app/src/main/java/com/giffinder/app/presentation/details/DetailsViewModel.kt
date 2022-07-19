package com.giffinder.app.presentation.details

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.giffinder.app.core.presentation.BaseViewModel
import com.giffinder.app.core.presentation.EventBus
import com.giffinder.app.domain.entity.GifData
import com.giffinder.app.domain.usecase.gif.BlockGifUseCase
import com.giffinder.app.presentation.common.Constants.Companion.ARG_GIF_DATA
import com.giffinder.app.presentation.details.navigator.DetailsScreenNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val navigator: DetailsScreenNavigator,
    private val blockGifUseCase: BlockGifUseCase,
    private val eventBus: EventBus
) : BaseViewModel() {

    fun onClickBack() {
        navigator.pop()
    }

    fun onClickBlockGif(item: GifData) {
        viewModelScope.launch(exceptionHandler) {
            blockGifUseCase(item)

            eventBus.invokeEvent(
                EventBus.AppEvent(
                    type = EventBus.AppEventType.REMOVE_GIF,
                    options = Bundle().apply {
                        putParcelable(ARG_GIF_DATA, item)
                    }
                )
            )
        }
    }
}
