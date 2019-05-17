package com.yotalabs.koral.ui.adapters.diffs

import androidx.recyclerview.widget.DiffUtil
import com.yotalabs.koral.ui.adapters.DisplayableItem
import com.yotalabs.koral.ui.adapters.models.ProfessionItem
import com.yotalabs.koral.ui.adapters.models.ServiceItem
import com.yotalabs.koral.ui.adapters.models.SubServiceItem

class SubServiceItemDiffUtilsCallback(
    private val oldItems: List<DisplayableItem>,
    private val newItems: List<DisplayableItem>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = true

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        ((oldItems[oldItemPosition] as SubServiceItem).id ==
                (newItems[newItemPosition] as SubServiceItem).id) &&
                    ((oldItems[oldItemPosition] as SubServiceItem).title ==
                            (newItems[newItemPosition] as SubServiceItem).title)

}