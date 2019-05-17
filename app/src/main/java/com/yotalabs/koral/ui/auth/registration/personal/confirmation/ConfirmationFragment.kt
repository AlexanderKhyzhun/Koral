package com.yotalabs.koral.ui.auth.registration.personal.confirmation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yotalabs.koral.ui.mvp.BaseFragment

/**
 * @author SashaKhyzhun
 * Created on 5/9/19.
 */
class ConfirmationFragment : BaseFragment(), ConfirmationView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    override fun renderError(throwable: Throwable) {

    }

    override fun renderMessage(text: String) {

    }
}