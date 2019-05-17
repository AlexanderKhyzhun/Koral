package com.yotalabs.koral.ui.adapters.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.view.clicks
import com.trello.rxlifecycle2.LifecycleTransformer
import com.yotalabs.koral.R
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.ui.adapters.DisplayableItem
import com.yotalabs.koral.ui.adapters.models.ProfessionItem
import kotlinx.android.synthetic.main.item_profession.view.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ProfessionViewHolder(view: View) : RecyclerView.ViewHolder(view), KoinComponent {

    val schedulers: Schedulers by inject()

    companion object {
        fun create(parent: ViewGroup) = ProfessionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_profession,
                parent,
                false
            )
        )
    }

    fun bind(
        item: DisplayableItem?,
        position: Int,
        click: (DisplayableItem) -> Unit,
        dispose: () -> LifecycleTransformer<Any>
    ) {
        (item as? ProfessionItem)?.let {
            with(itemView) {
                this.clicks()
                    .compose(dispose.invoke())
                    .observeOn(schedulers.mainThread())
                    .subscribe { click.invoke(item) }

                item_profession_title.text = it.title
            }
        }
    }
}