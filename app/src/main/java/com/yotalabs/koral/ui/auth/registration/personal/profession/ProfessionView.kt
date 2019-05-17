package com.yotalabs.koral.ui.auth.registration.personal.profession

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.yotalabs.koral.ui.adapters.DisplayableItem
import com.yotalabs.koral.ui.mvp.ErrorView
import com.yotalabs.koral.ui.mvp.LoadingView

interface ProfessionView : MvpView, ErrorView, LoadingView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun renderView(values: List<DisplayableItem>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onProfessionChosen()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onOtherChosen()

}
