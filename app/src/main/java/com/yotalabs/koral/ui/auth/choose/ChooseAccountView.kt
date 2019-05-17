package com.yotalabs.koral.ui.auth.choose

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.yotalabs.koral.ui.mvp.ErrorView
import com.yotalabs.koral.ui.mvp.LoadingView

interface ChooseAccountView : MvpView, ErrorView, LoadingView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onClickInfo()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onClickCustomer()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onClickPersonalBusiness()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onClickCorporateBusiness()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onClickSkipAndExplore()

}
