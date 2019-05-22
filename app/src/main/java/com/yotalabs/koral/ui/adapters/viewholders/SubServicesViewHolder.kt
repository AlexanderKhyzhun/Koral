package com.yotalabs.koral.ui.adapters.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.view.clicks
import com.trello.rxlifecycle2.LifecycleTransformer
import com.yotalabs.koral.R
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.data.storages.SignUpRepository
import com.yotalabs.koral.ui.adapters.DisplayableItem
import com.yotalabs.koral.ui.adapters.models.SubServiceItem
import kotlinx.android.synthetic.main.item_sub_service.view.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber

class SubServicesViewHolder(view: View) : RecyclerView.ViewHolder(view), KoinComponent {

    val schedulers: Schedulers by inject()
    val signUpRepository: SignUpRepository by inject()

    companion object {
        fun create(parent: ViewGroup) = SubServicesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_sub_service,
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

        (item as? SubServiceItem)?.let {
            with(itemView) {
                item_sub_service_img_plus.clicks()
                    .compose(dispose.invoke())
                    .observeOn(schedulers.mainThread())
                    .subscribe { click.invoke(item) }

                item_sub_service_tv_title.text = it.title

                when (item.isSelected) {
                    true -> item_sub_service_img_plus.setBackgroundResource(R.drawable.ic_minus_circle_black)
                    false -> item_sub_service_img_plus.setBackgroundResource(R.drawable.ic_plus_circle_black)
                }

                signUpRepository.selectedSubServices().subscribe { subServices ->
                    Timber.d("ViewHolder | subscribe | subServices=$subServices")
                    subServices.forEach {
                        if (it.title == item.title) {
                            when (it.isSelected) {
                                true -> item_sub_service_img_plus.setBackgroundResource(R.drawable.ic_minus_circle_black)
                                false -> item_sub_service_img_plus.setBackgroundResource(R.drawable.ic_plus_circle_black)
                            }
                        }
                    }
                }

            }
        }
    }

}