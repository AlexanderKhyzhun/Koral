package com.yotalabs.koral.ui.auth.registration.corporate

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
import com.yotalabs.koral.ui.auth.login.LoginFragment
import com.yotalabs.koral.ui.mvp.BaseActivity
import com.yotalabs.koral.ui.mvp.BaseFragment
import kotlinx.android.synthetic.main.item_toolbar_white.*
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

/**
 * @author SashaKhyzhun
 * Created on 5/5/19.
 */
class CreateCorporateFragment : BaseFragment(), CreateCorporateView {

    interface Callback {

    }

    val schedulers: Schedulers by inject()

    @InjectPresenter
    lateinit var presenter: CreateCorporatePresenter

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
        return inflater.inflate(R.layout.fragment_create_corporate, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        item_toolbar_title.text = LoginFragment.TITLE
        item_toolbar_back_button.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { activity?.finish() }


    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    override fun renderView() {

    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    override fun renderError(throwable: Throwable) {
        showSnack(throwable.message)
    }

    override fun renderMessage(text: String) {
        showSnack(text)
    }

    companion object {
        const val TITLE = "Create account"
        const val TAG = "CreateCorporateFragment"
        fun newInstance() = CreateCorporateFragment()
    }

}