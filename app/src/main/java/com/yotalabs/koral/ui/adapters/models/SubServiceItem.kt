package com.yotalabs.koral.ui.adapters.models

import com.yotalabs.koral.ui.adapters.DisplayableItem
import com.yotalabs.koral.utils.empty

data class SubServiceItem(
    val id: String,
    val title: String,
    var price: String = empty(),
    var duration: String = empty(),
    var isSelected: Boolean = false
) : DisplayableItem
