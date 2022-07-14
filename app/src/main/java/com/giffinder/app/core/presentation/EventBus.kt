package com.giffinder.app.core.presentation

import android.os.Bundle
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class EventBus {
    private val _events = MutableSharedFlow<AppEvent>()
    val events = _events.asSharedFlow()

    suspend fun invokeEvent(event: AppEvent) = _events.emit(event)

    class AppEvent(
        val type: AppEventType,
        val options: Bundle
    )

    enum class AppEventType {
        REMOVE_GIF
    }
}
