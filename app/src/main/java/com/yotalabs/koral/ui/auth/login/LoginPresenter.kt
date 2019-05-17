package com.yotalabs.koral.ui.auth.login

import com.arellomobile.mvp.InjectViewState
import com.yotalabs.koral.ui.mvp.BasePresenter
import org.koin.standalone.KoinComponent

/**
 * @author SashaKhyzhun
 * Created on 5/4/19.
 */
@InjectViewState
class LoginPresenter : BasePresenter<LoginView>(), KoinComponent {

    fun clickCreateAccount() {
        viewState.redirectToChooseAccountPage()
    }

}