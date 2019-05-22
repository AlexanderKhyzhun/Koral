package com.yotalabs.koral.ui.auth.photo

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.domain.SignUpPersonalUseCase
import com.yotalabs.koral.ui.mvp.BasePresenter
import io.reactivex.rxkotlin.Observables
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber

/**
 * @author SashaKhyzhun
 * Created on 2019-05-22.
 */
@InjectViewState
@SuppressLint("CheckResult")
class PhotoPresenter : BasePresenter<PhotoView>(), KoinComponent {

    val schedulers: Schedulers by inject()
    val useCase: SignUpPersonalUseCase by inject()

    init {
        fetchUserName()

        useCase.photo().subscribe {
            viewState.renderImage(it)
        }
    }


    private fun fetchUserName() {
        Timber.d("fetchUserName")

        Observables
            .zip(useCase.firstName(), useCase.lastName()) { first, last -> "$first $last" }
            .subscribe { viewState.renderName(it) }
    }

    fun onClickTakePhoto() {
        viewState.onClickedTakePhoto()
    }

    fun onClickImport() {
        viewState.onClickedImport()
    }

    fun onClickSave() {
        viewState.onClickedSave()
    }


}