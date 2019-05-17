package com.yotalabs.koral.ui.auth.registration.personal.services

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.domain.impl.ServicesUseCaseImpl
import com.yotalabs.koral.ui.adapters.DisplayableItem
import com.yotalabs.koral.ui.adapters.models.ServiceItem
import com.yotalabs.koral.ui.adapters.models.SubServiceItem
import com.yotalabs.koral.ui.mvp.BasePresenter
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber

/**
 * @author SashaKhyzhun
 * Created on 2019-05-15.
 */
@InjectViewState
@SuppressLint("CheckResult")
class ServicesPresenter : BasePresenter<ServicesView>(), KoinComponent {

    val schedulers: Schedulers by inject()
    val useCase: ServicesUseCaseImpl by inject()

    private lateinit var services: List<ServiceItem>

    init {
        fetchCategories()
    }


    fun onClickOnSubService(item: DisplayableItem) {
        item as SubServiceItem
        item.selected = item.selected.not()

        useCase.addSubServiceToSelected(item)
        Timber.d("item=$item")
    }


    fun onClickOnService(item: DisplayableItem) {
        item as ServiceItem
        Timber.d("item=$item")

        useCase.changeSelectedService(item)
        viewState.hideTextAddFewServices()
        viewState.showSubCategoriesRecycler()

        fetchSubCategories(item)
    }


    private fun fetchCategories() {
        useCase.fetchCategories()
            .compose(bindUntilDestroy())
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.mainThread())
            .doOnError { viewState.hideLoader() }
            .doOnSubscribe { viewState.showLoader() }
            .subscribe({
                services = it
                viewState.renderServices(services)
            }, viewState::renderError)

    }


    private fun fetchSubCategories(service: ServiceItem) {
        useCase.fetchSubCategoryByCategory(service.title)
            .compose(bindUntilDestroy())
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.mainThread())
            .doOnError { viewState.hideLoader() }
            .doOnSubscribe { viewState.showLoader() }
            .subscribe({
                viewState.renderSubServices(it)
            }, viewState::renderError)
    }


    fun onClickApply(price: Int, duration: Int) {
        Timber.d("price: $price, duration: $duration")

        // add saved value to some subject where would be stored all sub services
        // useCase.addSubServiceToSelected(title, price, duration)

        viewState.onApplyClicked()
    }


}