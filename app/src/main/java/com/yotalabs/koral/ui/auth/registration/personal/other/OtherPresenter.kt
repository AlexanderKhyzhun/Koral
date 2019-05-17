package com.yotalabs.koral.ui.auth.registration.personal.other

import com.arellomobile.mvp.InjectViewState
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.domain.SignUpPersonalUseCase
import com.yotalabs.koral.ui.mvp.BasePresenter
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

@InjectViewState
class OtherPresenter : BasePresenter<OtherView>(), KoinComponent {

    val schedulers: Schedulers by inject()
    val useCase: SignUpPersonalUseCase by inject()

    init {

    }

    fun onClickSendRequest() {
        //...
    }

}
