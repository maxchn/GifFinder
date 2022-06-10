package com.giffinder.app.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import androidx.paging.map
import com.giffinder.app.BuildConfig
import com.giffinder.app.core.presentation.BaseViewModel
import com.giffinder.app.core.presentation.livedata.SingleLiveEvent
import com.giffinder.app.domain.common.Constants.DEFAULT_OFFSET
import com.giffinder.app.domain.common.Constants.PAGE_SIZE
import com.giffinder.app.domain.common.mapper.toGif
import com.giffinder.app.domain.entity.GifData
import com.giffinder.app.domain.entity.GifParams
import com.giffinder.app.domain.usecase.gif.GetGifListUseCase
import com.giffinder.app.domain.usecase.gif.UpdateGifUseCase
import com.giffinder.app.presentation.home.navigator.HomeScreenNavigator
import kotlinx.coroutines.launch

private const val RATING = "g"
private const val LANGUAGE = "en"

class HomeViewModel(
    private val getGifListUseCase: GetGifListUseCase,
    private val updateGifUseCase: UpdateGifUseCase,
    private val homeScreenNavigator: HomeScreenNavigator
) : BaseViewModel() {

    val search = MutableLiveData<String>()

    val showErrorPopup = SingleLiveEvent<String>()

    fun getImagesList(): LiveData<PagingData<GifData>> {
        val params = GifParams(
            apiKey = BuildConfig.API_KEY,
            query = search.value.orEmpty(),
            limit = PAGE_SIZE,
            offset = DEFAULT_OFFSET,
            rating = RATING,
            lang = LANGUAGE
        )

        val response = getGifListUseCase(params).liveData.map { pagingData ->
            pagingData.map { it.toGif() }
        }.cachedIn(viewModelScope)

        return response
    }

    fun updateGif(updatedItem: GifData) {
        viewModelScope.launch(exceptionHandler) {
            updateGifUseCase(updatedItem)
        }
    }

    fun navigateToDetailsScreen(items: List<GifData>, idSelectedItem: String) {
        homeScreenNavigator.pushDetailsScreen(
            items = items.toTypedArray(),
            selectedItemIndex = items.indexOfFirst { it.id == idSelectedItem }
        )
    }
}
