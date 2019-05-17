package com.yotalabs.koral.ui.notifications

import com.arellomobile.mvp.InjectViewState
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.domain.ProfileUseCase
import com.yotalabs.koral.ui.mvp.BasePresenter
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

@InjectViewState
class NotificationsPresenter : BasePresenter<NotificationsView>(), KoinComponent {

    val schedulers: Schedulers by inject()

    private val useCase: ProfileUseCase by inject()

}
