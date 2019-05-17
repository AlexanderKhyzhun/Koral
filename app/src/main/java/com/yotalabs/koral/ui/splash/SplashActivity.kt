package com.yotalabs.koral.ui.splash

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.yotalabs.koral.R
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.ui.MainActivity
import com.yotalabs.koral.ui.auth.AuthActivity
import com.yotalabs.koral.ui.mvp.BaseActivity
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject

/**
 * @author SashaKhyzhun
 * Created on 4/26/19.
 */
class SplashActivity : BaseActivity(), SplashView {

    val schedulers: Schedulers by inject()

    @InjectPresenter
    lateinit var presenter: SplashPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        CoroutineScope(Dispatchers.Main).launch {
            delay(1500L)
            presenter.handleUserAuthStatus()
        }
    }

    override fun onRedirectToNextPage() {
        startActivity(MainActivity.getIntent(this))
    }

    override fun onRedirectToLoginPage() {
        startActivity(AuthActivity.getIntent(this))
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
        const val TAG = "SplashActivity"
        const val PAGER_POSITION = 0
        fun newInstance() = SplashActivity()
    }

}