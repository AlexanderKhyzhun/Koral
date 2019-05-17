package com.yotalabs.koral.ui.mvp

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface ErrorView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun renderError(throwable: Throwable)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun renderMessage(text: String)

}