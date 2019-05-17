package com.yotalabs.koral.ui.auth.choose

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
import kotlinx.android.synthetic.main.fragment_create_account.*
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * @author SashaKhyzhun
 * Created on 5/4/19.
 */
class ChooseAccountFragment : BaseFragment(), ChooseAccountView {

    interface Callback {
        fun selectedInfoPage()
        fun selectedCustomer()
        fun selectedPersonalBusiness()
        fun selectedCorporateBusiness()
        fun selectedSkipAndExplore()
    }

    val schedulers: Schedulers by inject()

    @InjectPresenter
    lateinit var presenter: ChooseAccountPresenter


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
        return inflater.inflate(R.layout.fragment_create_account, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated")

        fragment_create_account_iv_info.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe {
                presenter.clickInfo()
            }

        fragment_create_account_button_customer.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe {
                presenter.clickCustomer()
            }

        fragment_create_account_button_personal.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe {
                presenter.clickPersonalBusiness()
            }

        fragment_create_account_button_corporate.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe {
                presenter.clickCorporateBusiness()
            }

        fragment_create_account_button_skip.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe {
                presenter.clickSkipAndExplore()
            }
    }

    override fun onClickInfo() {
        callback?.selectedInfoPage()
    }

    override fun onClickCustomer() {
        callback?.selectedCustomer()
    }

    override fun onClickPersonalBusiness() {
        callback?.selectedPersonalBusiness()
    }

    override fun onClickCorporateBusiness() {
        callback?.selectedCorporateBusiness()
    }

    override fun onClickSkipAndExplore() {
        callback?.selectedSkipAndExplore()
    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }


    override fun renderError(throwable: Throwable) {

    }

    override fun renderMessage(text: String) {

    }

    companion object {
        const val TAG = "ChooseAccountFragment"
        fun newInstance() = ChooseAccountFragment()
    }
}