package com.yotalabs.koral.ui.adapters.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class LinearDecorator(
    private val top: Int,
    private val bottom: Int,
    private val itemsBottom: Int,
    private val sideOffset: Int
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
                outRect.left = sideOffset
                outRect.right = sideOffset
                outRect.bottom = itemsBottom
            }

            parent.getChildAdapterPosition(view) == state.itemCount - 1 -> {
                outRect.top = 0
                outRect.left = sideOffset
                outRect.right = sideOffset
                outRect.bottom = bottom
            }

            else -> {
                outRect.top = 0
                outRect.left = sideOffset
                outRect.right = sideOffset
                outRect.bottom = itemsBottom
            }
        }
    }
}