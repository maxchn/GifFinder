package com.giffinder.app.core.data.remote

import androidx.lifecycle.MutableLiveData

class NetworkManager {
    val processing = MutableLiveData<Boolean>().apply {
        postValue(false)
    }

    fun startProcessing() {
        processing.postValue(true)
    }

    fun stopProcessing() {
        processing.postValue(false)
    }
}
