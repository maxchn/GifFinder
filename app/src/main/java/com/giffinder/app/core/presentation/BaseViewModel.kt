package com.giffinder.app.core.presentation

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    protected open val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e("CoroutineExceptionHandler got: $exception}")
    }
}
