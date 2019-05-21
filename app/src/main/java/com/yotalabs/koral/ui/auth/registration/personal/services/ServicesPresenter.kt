package com.yotalabs.koral.ui.auth.registration.personal.services

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.domain.impl.ServicesUseCaseImpl
import com.yotalabs.koral.ui.adapters.DisplayableItem
import com.yotalabs.koral.ui.adapters.models.ServiceItem
import com.yotalabs.koral.ui.adapters.models.SubServiceItem
import com.yotalabs.koral.ui.mvp.BasePresenter
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

    private lateinit var services: List<ServiceItem>

    private val priceSubj: BehaviorSubject<Int> = BehaviorSubject.create<Int>()
    private val durationSubj: BehaviorSubject<Int> = BehaviorSubject.create<Int>()


    init {
        fetchCategories()
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

        //useCase.addSubServiceToSelected(item)
        viewState.showSlideUpMenu(item.title)
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


    fun onClickApply(price: Int, duration: Int) {
        Timber.d("onClickApply | price: $price, duration: $duration")

        // add saved value to some subject where would be stored all sub services
        // useCase.addSubServiceToSelected(title, price, duration)

        //priceSubj.onNext()

        viewState.onApplyClicked()
    }

    fun onClickClose() {
        viewState.onCloseClicked()
    }


}