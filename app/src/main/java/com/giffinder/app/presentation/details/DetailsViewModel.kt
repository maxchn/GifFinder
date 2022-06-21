package com.giffinder.app.presentation.details

import androidx.lifecycle.viewModelScope
import com.giffinder.app.core.presentation.BaseViewModel
import com.giffinder.app.core.presentation.livedata.SingleLiveEvent
import com.giffinder.app.domain.entity.GifData
import com.giffinder.app.domain.usecase.gif.BlockGifUseCase
import com.giffinder.app.presentation.details.navigator.DetailsScreenNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val navigator: DetailsScreenNavigator,
    private val blockGifUseCase: BlockGifUseCase
) : BaseViewModel() {

    val blockGifFile = SingleLiveEvent<GifData>()

    fun onClickBack() {
        navigator.pop()
    }

    fun onClickBlockGif(item: GifData) {
        viewModelScope.launch(exceptionHandler) {
            blockGifUseCase(item)

            blockGifFile.value = item
        }
    }
}
