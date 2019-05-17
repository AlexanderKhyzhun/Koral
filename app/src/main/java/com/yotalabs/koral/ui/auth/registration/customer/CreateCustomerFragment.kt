package com.yotalabs.koral.ui.auth.registration.customer

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.jakewharton.rxbinding2.view.clicks
import com.yotalabs.koral.R
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.ui.mvp.BaseActivity
import com.yotalabs.koral.ui.mvp.BaseFragment
import kotlinx.android.synthetic.main.fragment_create_customer.*
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

/**
 * @author SashaKhyzhun
 * Created on 5/5/19.
 */
class CreateCustomerFragment : BaseFragment(), CreateCustomerView {

    interface Callback {

    }

    val schedulers: Schedulers by inject()

    @InjectPresenter
    lateinit var presenter: CreateCustomerPresenter

    private var callback: Callback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Callback) {
            callback = context
        } else {
            throw RuntimeException("$context must implement Callback")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_customer, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_create_customer_btn_next.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { toast("next") }
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    override fun renderView() {

    }

    override fun renderError(throwable: Throwable) {
        showSnack(throwable.message)
    }

    override fun renderMessage(text: String) {
        showSnack(text)
    }

    companion object {
        const val TAG = "CreateCustomerFragment"
        fun newInstance() = CreateCustomerFragment()
    }

}