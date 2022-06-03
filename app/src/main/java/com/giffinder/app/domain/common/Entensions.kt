package com.giffinder.app.domain.common

fun Int?.orDefault(): Int {
    return this ?: 0
}
