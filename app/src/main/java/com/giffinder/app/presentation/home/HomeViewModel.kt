package com.giffinder.app.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.giffinder.app.BuildConfig
import com.giffinder.app.core.presentation.BaseViewModel
import com.giffinder.app.core.presentation.livedata.SingleLiveEvent
import com.giffinder.app.data.remote.dto.request.GifParams
import com.giffinder.app.domain.common.Constants.DEFAULT_OFFSET
import com.giffinder.app.domain.common.Constants.PAGE_SIZE
import com.giffinder.app.domain.entity.GifData
import com.giffinder.app.domain.usecase.gif.GetGifListUseCase

private const val RATING = "g"
private const val LANGUAGE = "en"

class HomeViewModel(
    private val getGifListUseCase: GetGifListUseCase
) : BaseViewModel() {

    val search = MutableLiveData<String>()

    val showErrorPopup = SingleLiveEvent<String>()

    private val _imagesList = MutableLiveData<PagingData<GifData>>()

    fun getImagesList(): LiveData<PagingData<GifData>> {
        val params = GifParams(
            apiKey = BuildConfig.API_KEY,
            query = search.value.orEmpty(),
            limit = PAGE_SIZE,
            offset = DEFAULT_OFFSET,
            rating = RATING,
            lang = LANGUAGE
        )

        val response = getGifListUseCase(params).liveData.cachedIn(viewModelScope)
        _imagesList.value = response.value
        return response
    }
}
