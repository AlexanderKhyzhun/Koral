package com.yotalabs.koral.ui.adapters.delegates

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.trello.rxlifecycle2.LifecycleTransformer
import com.yotalabs.koral.ui.adapters.DisplayableItem
import com.yotalabs.koral.ui.adapters.models.ProfessionItem
import com.yotalabs.koral.ui.adapters.models.ServiceItem
import com.yotalabs.koral.ui.adapters.viewholders.ProfessionViewHolder
import com.yotalabs.koral.ui.adapters.viewholders.ServicesViewHolder

class ServicesDelegateAdapter(
    private val click: (DisplayableItem) -> Unit,
    private val dispose: () -> LifecycleTransformer<Any>
) : AdapterDelegate<List<DisplayableItem>>() {

    override fun isForViewType(items: List<DisplayableItem>, position: Int) =
        items[position] is ServiceItem

    override fun onBindViewHolder(
        items: List<DisplayableItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ServicesViewHolder).bind(items[position], position, click, dispose, items.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup) = ServicesViewHolder.create(parent)

}