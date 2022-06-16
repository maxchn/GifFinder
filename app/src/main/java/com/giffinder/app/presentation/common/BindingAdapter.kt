package com.giffinder.app.presentation.common

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.giffinder.app.presentation.common.decoration.GridSpacingItemDecoration

@BindingAdapter("android:itemDecoration")
fun setDecoration(view: RecyclerView, space: Float) {
    view.addItemDecoration(
        GridSpacingItemDecoration(
            spanCount = 3,
            itemSpacing = space.toInt(),
            includeEdge = true
        )
    )
}
