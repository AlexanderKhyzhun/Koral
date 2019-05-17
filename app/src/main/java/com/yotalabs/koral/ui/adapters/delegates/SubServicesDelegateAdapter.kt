package com.yotalabs.koral.ui.adapters.delegates

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.trello.rxlifecycle2.LifecycleTransformer
import com.yotalabs.koral.ui.adapters.DisplayableItem
import com.yotalabs.koral.ui.adapters.models.SubServiceItem
import com.yotalabs.koral.ui.adapters.viewholders.SubServicesViewHolder

class SubServicesDelegateAdapter(
    private val click: (DisplayableItem) -> Unit,
    private val dispose: () -> LifecycleTransformer<Any>
) : AdapterDelegate<List<DisplayableItem>>() {

    override fun isForViewType(items: List<DisplayableItem>, position: Int) =
        items[position] is SubServiceItem

    override fun onBindViewHolder(
        items: List<DisplayableItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as SubServicesViewHolder).bind(items[position], position, click, dispose)
    }

    override fun onCreateViewHolder(parent: ViewGroup) = SubServicesViewHolder.create(parent)

}