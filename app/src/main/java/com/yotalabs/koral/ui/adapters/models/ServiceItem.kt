package com.yotalabs.koral.ui.adapters.models

import com.yotalabs.koral.ui.adapters.DisplayableItem

/**
 * @author SashaKhyzhun
 * Created on 2019-05-16.
 */
data class ServiceItem(
    val id: String,
    val title: String,
    var isSelected: Boolean = false
) : DisplayableItem