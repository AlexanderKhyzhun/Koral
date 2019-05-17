package com.yotalabs.koral.ui.search

import com.arellomobile.mvp.InjectViewState
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.domain.ProfileUseCase
import com.yotalabs.koral.ui.mvp.BasePresenter
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

@InjectViewState
class SearchPresenter : BasePresenter<SearchView>(), KoinComponent {

    val schedulers: Schedulers by inject()

    private val useCase: ProfileUseCase by inject()

}
