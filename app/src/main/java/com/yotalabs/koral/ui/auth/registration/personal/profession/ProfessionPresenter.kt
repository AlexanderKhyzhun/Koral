package com.yotalabs.koral.ui.auth.registration.personal.profession

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.domain.impl.ProfessionUseCaseImpl
import com.yotalabs.koral.ui.adapters.DisplayableItem
import com.yotalabs.koral.ui.adapters.models.ProfessionItem
import com.yotalabs.koral.ui.mvp.BasePresenter
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber

@InjectViewState
@SuppressLint("CheckResult")
class ProfessionPresenter : BasePresenter<ProfessionView>(), KoinComponent {

    // inject api?
    // create useCase and inside inject api?
    // or inject userRepository with needed method?
    val schedulers: Schedulers by inject()
    val useCase: ProfessionUseCaseImpl by inject()


    init {
        fetchProfessions()
    }

    private fun fetchProfessions() {
        useCase.fetchProfessions()
            .compose(bindUntilDestroy())
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.mainThread())
            .doOnComplete { viewState.hideLoader() }
            .doOnError { viewState.hideLoader() }
            .doOnSubscribe { viewState.showLoader() }
            .subscribe(viewState::renderView, viewState::renderError)
    }


    fun onClickProfession(item: DisplayableItem) {
        Timber.d("onClickProfession | item=$item")
        useCase.onProfessionChosen(item as ProfessionItem)

        Timber.d("is other = ${item.id == ProfessionUseCaseImpl.OTHER}")

        when (item.id) {
            ProfessionUseCaseImpl.OTHER -> viewState.onOtherChosen()
            else -> viewState.onProfessionChosen()
        }

    }


}
