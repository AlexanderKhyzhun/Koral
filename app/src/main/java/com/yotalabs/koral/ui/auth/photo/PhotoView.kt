package com.yotalabs.koral.ui.auth.photo

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.yotalabs.koral.ui.mvp.ErrorView
import com.yotalabs.koral.ui.mvp.LoadingView

interface PhotoView : MvpView, ErrorView, LoadingView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun renderName(userName: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun renderImage(image: String)

}
