package com.yotalabs.koral.ui.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import org.jetbrains.anko.runOnUiThread

class DelegateAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val delegatesManager: AdapterDelegatesManager<List<T>> = AdapterDelegatesManager()

    var items: MutableList<T> = mutableListOf()

    var onLoadMoreListener: OnLoadMoreListener? = null

    var isLoading: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        delegatesManager.onCreateViewHolder(parent, viewType)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        delegatesManager.onBindViewHolder(items, position, holder)

    override fun getItemViewType(position: Int) = delegatesManager.getItemViewType(items, position)


    fun setLoadMoreListener(mOnLoadMoreListener: OnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener
    }

    fun addAll(items: List<T>) {
        val size = this.items.size
        this.items.addAll(items)
        notifyItemRangeInserted(size, items.size)
    }

    fun set(position: Int, item: T) {
        items[position] = item
        notifyItemChanged(position)
    }

    fun set(newItems: List<T>, diffUtilsCallbackProducer: (List<T>, List<T>) -> DiffUtil.Callback) {
        val diffResult =
            DiffUtil.calculateDiff(diffUtilsCallbackProducer.invoke(this.items, newItems))
        this.items = newItems.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }

    fun set(newItems: List<T>) {
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        this.items.removeAt(position)
        notifyDataSetChanged()
    }

    fun onLoadMore(ctx: Context, data: List<T>) {
        ctx.runOnUiThread {
            items.addAll(data)
            notifyDataSetChanged()
            isLoading = false
        }
    }


}