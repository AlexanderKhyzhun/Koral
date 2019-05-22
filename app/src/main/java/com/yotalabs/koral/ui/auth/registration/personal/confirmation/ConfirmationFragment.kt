package com.yotalabs.koral.ui.auth.registration.personal.confirmation

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
import com.yotalabs.koral.utils.empty
import com.yotalabs.koral.utils.setVisible
import kotlinx.android.synthetic.main.fragment_confirmation.*
import kotlinx.android.synthetic.main.item_toolbar_purple.*
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * @author SashaKhyzhun
 * Created on 5/9/19.
 */
class ConfirmationFragment : BaseFragment(), ConfirmationView {

    interface Callback {
        fun fromConfirmationToServices()
        fun fromConfirmationToPhoto()
    }

    val schedulers: Schedulers by inject()

    @InjectPresenter
    lateinit var presenter: ConfirmationPresenter

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
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        item_toolbar_title.text = TITLE

        item_toolbar_right_button.setVisible()

        item_toolbar_right_button.text = TOOLBAR_BUTTON

        item_toolbar_right_button.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { Timber.d("Info clicked") }

        item_toolbar_back_button.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { callback?.fromConfirmationToServices() }



        fragment_confirmation_button_apply.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe {
                presenter.onClickApply(
                    fragment_confirmation_cb_for_all.isChecked,
                    fragment_confirmation_cb_for_new.isChecked,
                    fragment_confirmation_cb_under_three_star.isChecked,
                    fragment_confirmation_cb_without.isChecked
                )
            }


        context?.createAlertDialog(
            schedulers,
            empty(),
            "Please choose the most suitable confirmation method for your bookings. " +
                    "You can read more about the option in the 'info' section",
            "Continue",
            empty(),
            { Timber.d("negative") },
            { Timber.d("positive") }
        )?.show()

    }


    override fun onClickedApply() {
        callback?.fromConfirmationToPhoto()
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    override fun renderError(throwable: Throwable) {

    }

    override fun renderMessage(text: String) {

    }

    companion object {
        const val TITLE = "Confirmation"
        const val TOOLBAR_BUTTON = "Info"
        const val TAG = "ConfirmationFragment"
        fun newInstance() = ConfirmationFragment()
    }
}