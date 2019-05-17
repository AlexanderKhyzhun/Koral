package com.yotalabs.koral.ui.adapters.diffs

import androidx.recyclerview.widget.DiffUtil
import com.yotalabs.koral.ui.adapters.DisplayableItem
import com.yotalabs.koral.ui.adapters.models.ProfessionItem
import com.yotalabs.koral.ui.adapters.models.ServiceItem

class ServiceItemDiffUtilsCallback(
    private val oldItems: List<DisplayableItem>,
    private val newItems: List<DisplayableItem>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = true

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        ((oldItems[oldItemPosition] as ServiceItem).id ==
                (newItems[newItemPosition] as ServiceItem).id) &&
                    ((oldItems[oldItemPosition] as ServiceItem).title ==
                            (newItems[newItemPosition] as ServiceItem).title) &&
                                ((oldItems[oldItemPosition] as ServiceItem).isSelected ==
                                        (newItems[newItemPosition] as ServiceItem).isSelected)

}