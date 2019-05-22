package com.yotalabs.koral.ui.auth.registration.personal.confirmation

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.domain.ConfirmationUseCase
import com.yotalabs.koral.domain.SignUpPersonalUseCase
import com.yotalabs.koral.ui.mvp.BasePresenter
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber

@SuppressLint("CheckResult")
@InjectViewState
class ConfirmationPresenter : BasePresenter<ConfirmationView>(), KoinComponent {

    val schedulers: Schedulers by inject()
    val useCase: ConfirmationUseCase by inject()

    init {

    }


    fun onClickApply(forAll: Boolean, forNew: Boolean, underThree: Boolean, without: Boolean) {
        Timber.d("forAll=$forAll, forNew=$forNew, underThree=$underThree, without=$without")

        useCase.uploadConfirmationStatuses(forAll, forNew, underThree, without)
            .compose(bindUntilDestroy())
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.mainThread())
            .doOnComplete { viewState.hideLoader() }
            .doOnError { viewState.hideLoader() }
            .doOnSubscribe { viewState.showLoader() }
            .subscribe({

                viewState.onClickedApply()

            }, viewState::renderError)

    }


}
