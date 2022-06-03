package com.giffinder.app.presentation.common.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.ceil

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val itemSpacing: Int,
    private val includeEdge: Boolean
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if (includeEdge) {
            when (column) {
                0 -> {
                    outRect.left = itemSpacing
                    outRect.right = (column + 1) * itemSpacing / spanCount
                }
                spanCount - 1 -> {
                    outRect.left = itemSpacing - column * itemSpacing / spanCount
                    outRect.right = itemSpacing
                }
                else -> {
                    outRect.left = itemSpacing - column * itemSpacing / spanCount
                    outRect.right = (column + 1) * itemSpacing / spanCount
                }
            }

            if (position < spanCount) {
                outRect.top = itemSpacing
            } else {
                outRect.top = itemSpacing
            }

            val itemsCount = parent.adapter?.itemCount ?: 0
            val maxRow = ceil(itemsCount / spanCount.toDouble())
            val currentRow = ceil((position + 1) / spanCount.toDouble())

            if (currentRow == maxRow) {
                outRect.bottom = itemSpacing
            }
        } else {
            outRect.left = column * itemSpacing / spanCount
            outRect.right = itemSpacing - (column + 1) * itemSpacing / spanCount

            if (position >= spanCount) {
                outRect.top = itemSpacing
            }
        }
    }
}
