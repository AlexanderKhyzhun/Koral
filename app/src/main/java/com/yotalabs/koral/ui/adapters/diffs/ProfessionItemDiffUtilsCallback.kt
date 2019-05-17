package com.yotalabs.koral.ui.adapters.diffs

import androidx.recyclerview.widget.DiffUtil
import com.yotalabs.koral.ui.adapters.DisplayableItem
import com.yotalabs.koral.ui.adapters.models.ProfessionItem

class ProfessionItemDiffUtilsCallback(
    private val oldItems: List<DisplayableItem>,
    private val newItems: List<DisplayableItem>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = true

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        ((oldItems[oldItemPosition] as ProfessionItem).id ==
                (newItems[newItemPosition] as ProfessionItem).id)
                &&
                ((oldItems[oldItemPosition] as ProfessionItem).title ==
                        (newItems[newItemPosition] as ProfessionItem).title)


}