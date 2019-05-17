package com.yotalabs.koral.ui.adapters.viewholders

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.view.clicks
import com.trello.rxlifecycle2.LifecycleTransformer
import com.yotalabs.koral.R
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.data.storages.SignUpRepository
import com.yotalabs.koral.ui.adapters.DisplayableItem
import com.yotalabs.koral.ui.adapters.models.ServiceItem
import com.yotalabs.koral.utils.setGone
import kotlinx.android.synthetic.main.item_service.view.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ServicesViewHolder(view: View) : RecyclerView.ViewHolder(view), KoinComponent {

    val schedulers: Schedulers by inject()
    val signUpRepository: SignUpRepository by inject()

    companion object {
        fun create(parent: ViewGroup) = ServicesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_service,
                parent,
                false
            )
        )
    }

    fun bind(
        item: DisplayableItem?,
        position: Int,
        click: (DisplayableItem) -> Unit,
        dispose: () -> LifecycleTransformer<Any>,
        size: Int
    ) {

        (item as? ServiceItem)?.let {
            with(itemView) {
                this.clicks()
                    .compose(dispose.invoke())
                    .observeOn(schedulers.mainThread())
                    .subscribe { click.invoke(item) }

                item_service_tv_title.text = it.title

                signUpRepository.selectedService().subscribe { service ->
                    when (service.id) {
                        item.id -> item_service_tv_title.typeface = Typeface.DEFAULT_BOLD
                        else    -> item_service_tv_title.typeface = Typeface.DEFAULT
                    }
                }

                if (position == size - 1) {
                    item_service_view_stick.setGone()
                }

            }
        }
    }

}