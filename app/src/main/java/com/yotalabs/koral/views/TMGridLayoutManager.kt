package com.yotalabs.koral.views

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

/**
 * @author SashaKhyzhun
 * Created on 1/30/19.
 */
class TMGridLayoutManager(
    context: Context,
    columnCounter: Int
) : GridLayoutManager(context, columnCounter) {

    private var isScrollEnabled = true

    fun setScrollEnabled(flag: Boolean) {
        this.isScrollEnabled = flag
    }

    fun getScrollEnabled(): Boolean = isScrollEnabled

    override fun canScrollVertically(): Boolean {
        return isScrollEnabled && super.canScrollVertically()
    }


}
