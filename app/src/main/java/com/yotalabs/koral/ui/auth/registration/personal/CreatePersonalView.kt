package com.yotalabs.koral.ui.auth.registration.personal

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.yotalabs.koral.domain.models.ValidationView
import com.yotalabs.koral.ui.mvp.ErrorView
import com.yotalabs.koral.ui.mvp.LoadingView

interface CreatePersonalView : MvpView, ErrorView, LoadingView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun renderView(viewState: ValidationView.Form)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun nextButtonState(ready: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onPersonalAccountCreated()

}
