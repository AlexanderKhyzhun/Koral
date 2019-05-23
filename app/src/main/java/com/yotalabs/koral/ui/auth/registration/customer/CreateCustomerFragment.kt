package com.yotalabs.koral.ui.auth.registration.customer

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.view.focusChanges
import com.jakewharton.rxbinding2.widget.checkedChanges
import com.jakewharton.rxbinding2.widget.textChanges
import com.yotalabs.koral.R
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.domain.models.ValidationView
import com.yotalabs.koral.ui.mvp.BaseActivity
import com.yotalabs.koral.ui.mvp.BaseFragment
import kotlinx.android.synthetic.main.fragment_create_customer.*
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * @author SashaKhyzhun
 * Created on 5/5/19.
 */
class CreateCustomerFragment : BaseFragment(), CreateCustomerView {

    interface Callback {
        fun onSignedUpCustomerAccount()
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


        fragment_create_customer_et_first_name.textChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onFirstNameTextChanges)

        fragment_create_customer_et_first_name.focusChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onFirstNameFocusChanges)


        fragment_create_customer_et_last_name.textChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onLastNameTextChanges)

        fragment_create_customer_et_last_name.focusChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onLastNameFocusChanges)



        fragment_create_customer_et_phone_number.textChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onPhoneNumberTextChanges)

        fragment_create_customer_et_phone_number.focusChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onPhoneNumberFocusChanges)



        fragment_create_customer_et_email.textChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onEmailTextChanges)

        fragment_create_customer_et_email.focusChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onEmailFocusChanges)



        fragment_create_customer_et_password.textChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onPasswordTextChanges)

        fragment_create_customer_et_password.focusChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onPasswordFocusChanges)

        fragment_create_customer_checked_tv.checkedChanges()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onTermCheckChanges)

        fragment_create_customer_btn_next.clicks()
            .filter { presenter.buttonStatus }
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe {
                Timber.d("button clicked")
                presenter.onClickSignUp()
            }

        setupMultipleClickableLinks()
    }

    /**
     * Text should be:
     * By continuing you are indicating that you have read and
     * agree to the [Terms of Service] and [Privacy Policy]
     */
    private fun setupMultipleClickableLinks() {
        val spanTxt = SpannableStringBuilder(
            "By continuing you are indicating that you have read and agree to the "
        )
        spanTxt.append("Terms of Service")
        spanTxt.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                toast("Terms of services Clicked")
            }
        }, spanTxt.length - "Terms of Service".length, spanTxt.length, 0)

        spanTxt.append(" and ")
        spanTxt.append("Privacy Policy")
        spanTxt.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                toast("Privacy Policy Clicked")
            }
        }, spanTxt.length - "Privacy Policy".length, spanTxt.length, 0)

        fragment_create_customer_tv_terms_and_privacy.movementMethod = LinkMovementMethod.getInstance()
        fragment_create_customer_tv_terms_and_privacy.setText(spanTxt, TextView.BufferType.SPANNABLE)
    }

    override fun renderView(viewState: ValidationView.Form) {
        fragment_create_customer_et_first_name.error = when (viewState.firstNameError) {
            true -> resources.getString(R.string.error_first_name)
            false -> null
        }

        fragment_create_customer_et_last_name.error = when (viewState.lastNameError) {
            true -> resources.getString(R.string.error_last_name)
            false -> null
        }

        fragment_create_customer_et_phone_number.error = when (viewState.phoneNumberError) {
            true -> resources.getString(R.string.error_phone_number)
            false -> null
        }

        fragment_create_customer_et_email.error = when (viewState.emailError) {
            true -> resources.getString(R.string.error_email)
            false -> null
        }

        fragment_create_customer_et_password.error = when (viewState.passwordError) {
            true -> resources.getString(R.string.error_password)
            false -> null
        }

        fragment_create_customer_tv_terms_error.visibility = when (viewState.termsError) {
            true -> View.VISIBLE
            false -> View.INVISIBLE
        }


        presenter.onNextButtonReady(
            viewState.firstNameValid
                    && viewState.lastNameValid
                    && viewState.phoneNumberValid
                    && viewState.emailValid
                    && viewState.passwordValid
                    && viewState.termsValid
        )
    }

    override fun nextButtonState(ready: Boolean) {
        when (ready) {
            true -> {
                fragment_create_customer_btn_next.setBackgroundColor(
                    ContextCompat.getColor(
                        context!!,
                        R.color.primary
                    )
                )
            }
            false -> {
                fragment_create_customer_btn_next.setBackgroundColor(
                    ContextCompat.getColor(
                        context!!,
                        R.color.inactive
                    )
                )
            }
        }
    }

    override fun onCustomerAccountCreated() {
        callback?.onSignedUpCustomerAccount()
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