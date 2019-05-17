package com.yotalabs.koral.ui.splash

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.yotalabs.koral.data.storages.AuthRepository
import com.yotalabs.koral.ui.mvp.BasePresenter
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * @author SashaKhyzhun
 * Created on 4/26/19.
 */

@InjectViewState
@SuppressLint("CheckResult")
class SplashPresenter : BasePresenter<SplashView>(), KoinComponent {

    private val auth: AuthRepository by inject()

    fun handleUserAuthStatus() {
        if (auth.isUserAuthorized()) {
            redirectToNextPage()
        } else {
            redirectToLoginPage()
        }
    }

    private fun redirectToNextPage() {
        viewState.onRedirectToNextPage()
    }

    private fun redirectToLoginPage() {
        viewState.onRedirectToLoginPage()
    }

}