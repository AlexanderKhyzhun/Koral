package com.yotalabs.koral.ui.mvp

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface LoadingView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showLoader()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun hideLoader()

}