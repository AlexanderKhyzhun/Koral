package com.yotalabs.koral.ui.auth.registration.personal.services

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.domain.impl.ServicesUseCaseImpl
import com.yotalabs.koral.ui.adapters.DisplayableItem
import com.yotalabs.koral.ui.adapters.models.ServiceItem
import com.yotalabs.koral.ui.adapters.models.SubServiceItem
import com.yotalabs.koral.ui.mvp.BasePresenter
import com.yotalabs.koral.utils.empty
import io.reactivex.subjects.BehaviorSubject
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

    private val subServices = mutableListOf<SubServiceItem>()
    private var lastSelected: SubServiceItem? = null

    init {
        fetchCategories()
    }


    private fun fetchCategories() {
        useCase.fetchCategories()
            .compose(bindUntilDestroy())
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.mainThread())
            .doOnError { viewState.hideLoader() }
            .doOnSubscribe { viewState.showLoader() }
            .subscribe({ viewState.renderServices(it) }, viewState::renderError)
    }

    private fun fetchSubCategories(service: ServiceItem) {
        useCase.fetchSubCategoryByCategory(service.title)
            .compose(bindUntilDestroy())
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.mainThread())
            .doOnError { viewState.hideLoader() }
            .doOnSubscribe { viewState.showLoader() }
            .subscribe(viewState::renderSubServices, viewState::renderError)
    }



    fun onClickOnService(item: DisplayableItem) {
        item as ServiceItem
        Timber.d("item=$item")

        useCase.changeSelectedService(item)

        viewState.hideTextAddFewServices()
        viewState.showSubCategoriesRecycler()

        fetchSubCategories(item)
    }

    fun onClickOnSubService(item: DisplayableItem) {
        item as SubServiceItem
        Timber.d("item=$item")

        when (item.isSelected) {
            true -> onClickOnMinus(item)
            false -> onClickOnPlus(item)
        }
    }



    fun onClickOnPlus(item: SubServiceItem) {
        lastSelected = item
        viewState.showSlideUpMenu(item)
    }

    fun onClickOnMinus(item: SubServiceItem) {
        Timber.d("onClickOnMinus | before | $subServices")
        lastSelected = item

        subServices.forEach {
            if (it.id == item.id) {
                //subServices.remove(it)
                it.isSelected = false
            }
        }

        Timber.d("onClickOnMinus | after | $subServices")
        useCase.selectedSubServices().onNext(subServices)
    }

    fun onClickApply(price: String, duration: String) {
        Timber.d("onClickApply | before | $subServices")

        lastSelected?.let {
            it.apply {
                this.price = price
                this.duration = duration
                this.isSelected = true
            }

            subServices.add(it)
        }

        Timber.d("onClickApply | after | $subServices")

        useCase.selectedSubServices().onNext(subServices)
        viewState.onApplyClicked()
    }

    fun onClickClose() {
        viewState.onCloseClicked()
    }


}