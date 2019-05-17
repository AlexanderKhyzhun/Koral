package com.yotalabs.koral.ui.auth

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.yotalabs.koral.ui.mvp.ErrorView
import com.yotalabs.koral.ui.mvp.LoadingView

/**
 * @author SashaKhyzhun
 * Created on 5/3/19.
 */
interface AuthView : MvpView, ErrorView, LoadingView {



}