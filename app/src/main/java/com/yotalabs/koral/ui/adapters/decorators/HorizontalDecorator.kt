package com.yotalabs.koral.ui.adapters.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalDecorator(
    private val recyclerStart: Int,
    private val recyclerEnd: Int,
    private val itemSide: Int,
    private val top: Int,
    private val bottom: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        super.getItemOffsets(outRect, view, parent, state)

        when {
            parent.getChildAdapterPosition(view) == 0 -> {
                outRect.top = top
                outRect.left = recyclerStart
                outRect.right = itemSide
                outRect.bottom = bottom
            }
            parent.getChildAdapterPosition(view) == state.itemCount - 1 -> {
                outRect.top = top
                outRect.left = itemSide
                outRect.right = recyclerEnd
                outRect.bottom = bottom
            }
            else -> {
                outRect.top = top
                outRect.left = itemSide
                outRect.right = itemSide
                outRect.bottom = bottom
            }
        }
    }
}
