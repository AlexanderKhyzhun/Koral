package com.yotalabs.koral.ui.auth.congratulations

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.data.storages.SignUpRepository
import com.yotalabs.koral.domain.SignUpPersonalUseCase
import com.yotalabs.koral.ui.mvp.BasePresenter
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * @author SashaKhyzhun
 * Created on 2019-05-22.
 */
@InjectViewState
class CongratulationsPresenter : BasePresenter<CongratulationsView>(), KoinComponent {

    val schedulers: Schedulers by inject()
    val useCase: SignUpRepository by inject()

    init {
        fetchAccountType()
    }

    @SuppressLint("CheckResult")
    private fun fetchAccountType() {
        useCase.accountType().subscribe {
            viewState.renderAccountType(it.toString())
        }
    }

    fun onClickContinue() {
        viewState.onClickedContinue()
    }

}