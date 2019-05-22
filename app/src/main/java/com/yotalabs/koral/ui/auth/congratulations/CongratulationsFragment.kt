package com.yotalabs.koral.ui.auth.congratulations

import android.annotation.SuppressLint
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
import kotlinx.android.synthetic.main.fragment_congratulations.*
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * @author SashaKhyzhun
 * Created on 2019-05-22.
 */
class CongratulationsFragment : BaseFragment(), CongratulationsView {

    val schedulers: Schedulers by inject()

    @InjectPresenter
    lateinit var presenter: CongratulationsPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_congratulations, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_congratulations_btn_continue.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { presenter.onClickContinue() }

    }

    override fun onClickedContinue() {
        Timber.d("Clicked continue")
    }

    override fun renderAccountType(type: String) {
        // use data binding here maybe?!
        fragment_congratulations_btn_continue.text = type
    }

    override fun renderError(throwable: Throwable) {

    }

    override fun renderMessage(text: String) {

    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }


    companion object {
        const val TITLE = "Add Profile Photo"
        const val TAG = "CongratulationsFragment"

        fun newInstance() = CongratulationsFragment()
    }
}