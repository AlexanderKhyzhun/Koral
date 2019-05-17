package com.yotalabs.koral.ui.auth.choose

import com.arellomobile.mvp.InjectViewState
import com.yotalabs.koral.ui.mvp.BasePresenter
import org.koin.standalone.KoinComponent

/**
 * @author SashaKhyzhun
 * Created on 5/4/19.
 */
@InjectViewState
class ChooseAccountPresenter : BasePresenter<ChooseAccountView>(), KoinComponent {

    fun clickInfo() {
        viewState.onClickInfo()
    }

    fun clickCustomer() {
        viewState.onClickCustomer()
    }

    fun clickPersonalBusiness() {
        viewState.onClickPersonalBusiness()
    }

    fun clickCorporateBusiness() {
        viewState.onClickCorporateBusiness()
    }

    fun clickSkipAndExplore() {
        viewState.onClickSkipAndExplore()
    }


}