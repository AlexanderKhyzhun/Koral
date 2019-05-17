package com.yotalabs.koral.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.yotalabs.koral.R
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.ui.mvp.BaseFragment
import org.koin.android.ext.android.inject

class CalendarFragment : BaseFragment(), CalendarView {

    val schedulers: Schedulers by inject()

    @InjectPresenter
    lateinit var presenter: CalendarPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
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
        const val TAG = "CalendarFragment"
        const val PAGER_POSITION = 2
        fun newInstance() = CalendarFragment()
    }

}
