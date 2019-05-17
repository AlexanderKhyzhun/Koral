package com.yotalabs.koral.ui.auth.registration.personal.services

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.yotalabs.koral.ui.adapters.models.ServiceItem
import com.yotalabs.koral.ui.adapters.models.SubServiceItem
import com.yotalabs.koral.ui.mvp.ErrorView
import com.yotalabs.koral.ui.mvp.LoadingView

/**
 * @author SashaKhyzhun
 * Created on 2019-05-15.
 */
interface ServicesView : MvpView, ErrorView, LoadingView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onApplyClicked()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun renderServices(categories: List<ServiceItem>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun renderSubServices(subCategories: List<SubServiceItem>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun hideTextAddFewServices()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showSubCategoriesRecycler()

}