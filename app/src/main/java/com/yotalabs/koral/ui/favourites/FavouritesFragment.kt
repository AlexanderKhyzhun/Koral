package com.yotalabs.koral.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.yotalabs.koral.R
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.ui.mvp.BaseFragment
import org.koin.android.ext.android.inject

class FavouritesFragment : BaseFragment(), FavouritesView {

    val schedulers: Schedulers by inject()

    @InjectPresenter
    lateinit var presenter: FavouritesPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun renderView() {

    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    override fun renderMessage(text: String) {

    }

    override fun renderError(throwable: Throwable) {

    }

    companion object {
        const val TAG = "FavouritesFragment"
        const val PAGER_POSITION = 1
        fun newInstance() = FavouritesFragment()
    }
}
