package com.yotalabs.koral.views

import android.content.Context

import androidx.recyclerview.widget.LinearLayoutManager

/**
 * @author SashaKhyzhun
 * Created on 3/1/19.
 */
class TMLinearLayoutManager(context: Context) : LinearLayoutManager(context) {

    private var isScrollEnabled = true

    fun setScrollEnabled(flag: Boolean) {
        this.isScrollEnabled = flag
    }

    override fun canScrollVertically(): Boolean {
        return isScrollEnabled && super.canScrollVertically()
    }

}
