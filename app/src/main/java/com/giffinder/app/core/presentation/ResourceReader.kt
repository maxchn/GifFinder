package com.giffinder.app.core.presentation

import androidx.annotation.ArrayRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import java.io.InputStream

interface ResourceReader {
    fun getString(@StringRes resId: Int): String
    fun getString(@StringRes resId: Int, vararg args: Any): String
    fun getQuantityString(
        @PluralsRes pluralResId: Int,
        quantity: Int,
        vararg formatArgs: Any
    ): String

    fun getStringArray(@ArrayRes resId: Int): Array<String>

    @ColorInt
    fun getColor(@ColorRes resId: Int): Int

    fun getAsset(path: String): InputStream
}
