package com.yotalabs.koral.ui.adapters.models

import com.yotalabs.koral.ui.adapters.DisplayableItem

data class SubServiceItem(
    val id: String,
    val title: String,
    var selected: Boolean = false
) : DisplayableItem
